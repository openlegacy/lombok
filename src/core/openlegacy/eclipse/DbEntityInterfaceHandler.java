/*******************************************************************************
 * Copyright (c) 2017 OpenLegacy Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     OpenLegacy Inc. - initial API and implementation
 *
 * Copyright (C) 2009-2016 The Project Lombok Authors.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.

 *******************************************************************************/
package openlegacy.eclipse;

import lombok.AccessLevel;
import lombok.core.AST;
import lombok.eclipse.EclipseNode;
import lombok.eclipse.handlers.EclipseHandlerUtil;
import lombok.eclipse.handlers.HandleGetter;
import lombok.eclipse.handlers.HandleImplements;
import lombok.eclipse.handlers.HandleSetter;
import openlegacy.utils.EclipseAstUtil;
import openlegacy.utils.EclipseImportsUtil;
import openlegacy.utils.StringUtil;
import org.eclipse.jdt.internal.compiler.ast.*;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFileConstants;
import org.openlegacy.db.definitions.DbActionDefinition;

import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static lombok.eclipse.handlers.EclipseHandlerUtil.createListOfNonExistentFields;
import static lombok.eclipse.handlers.EclipseHandlerUtil.hasAnnotation;

/**
 * @author Matvey Mitnitsky
 * @since 3.6.0-SNAPSHOT
 */
public class DbEntityInterfaceHandler {

    private static java.util.List<EclipseNode> idNodes = null;

    public static void handle(EclipseNode typeNode, EclipseNode annotationNode) {
        TypeDeclaration typeDecl = HandleImplements.checkAnnotation(typeNode, annotationNode);
        if (typeDecl == null) {
            return;
        }

        createNonSuperEntityFields(typeNode);

        //TODO add if exists condition
        if (hasMultipleId(typeNode)) {
            createCompositeKey(typeNode);
        }
    }

    private static void createCompositeKey(EclipseNode typeNode) {
        TypeDeclaration typeDeclaration = (TypeDeclaration) typeNode.get();
        String className = new String(typeDeclaration.name);
        String idClassNameWithSuffix = className + "CompositeKey";
        String qualifiedName = className + "." + idClassNameWithSuffix;
        //List of definitions inside the new nested class (serialVersionUID is added)
        List<FieldDeclaration> variables = new ArrayList();
        variables.add(createSerialVersionUID());

        /*
         * Remove @Id annotation in the loop
         */
        for (EclipseNode idNode : idNodes) {
            for (EclipseNode idChild : idNode.down()) {
                if (idChild.getKind() == AST.Kind.ANNOTATION) {
                    Annotation annotation = (Annotation) idChild.get();
                    String typeName = annotation.type.toString();
                    if (typeName.equals("javax.persistence.Id") || typeName.equals("Id")){
                        FieldDeclaration originalDecl = (FieldDeclaration) idNode.get();
                        Annotation[] annotations = originalDecl.annotations;
                        Annotation [] copyAnnotations = copyAnnotaitonsAndRemoveId(annotations);
                        FieldDeclaration declCopy = new FieldDeclaration();
                        declCopy.modifiers = originalDecl.modifiers;
                        declCopy.initialization = originalDecl.initialization;
                        declCopy.type = originalDecl.type;
                        declCopy.annotations = copyAnnotations;
                        declCopy.name = originalDecl.name;
                        variables.add(declCopy);
                    }
                }
            }
        }

        //Declaration of the new nested class
        TypeDeclaration idClassTypeDecl = new TypeDeclaration(typeDeclaration.compilationResult);
        idClassTypeDecl.modifiers = idClassTypeDecl.modifiers | ClassFileConstants.AccPublic | ClassFileConstants.AccStatic;
        idClassTypeDecl.enclosingType = typeDeclaration;
        idClassTypeDecl.name = idClassNameWithSuffix.toCharArray();
        FieldDeclaration [] vars = getFieldDeclarationArr(variables);
        idClassTypeDecl.fields = vars;

        // inject new Type inside enclosing entity
        EclipseNode idClassTypeNode = EclipseHandlerUtil.injectType(typeNode, idClassTypeDecl);
//
        //generate getters and setters for nested class
        new HandleGetter().generateGetterForType(idClassTypeNode, idClassTypeNode.up(), AccessLevel.PUBLIC, true);
        new HandleSetter().generateSetterForType(idClassTypeNode, idClassTypeNode.up(), AccessLevel.PUBLIC, true);

        createIdClassAnnotaion(typeDeclaration, qualifiedName);
    }

    private static void createIdClassAnnotaion(TypeDeclaration typeDeclaration, String idClassQualifiedName) {
        NormalAnnotation annotation = new NormalAnnotation(EclipseAstUtil.createTypeReference(IdClass.class.getName()), 0);
        MemberValuePair[] pairs = annotation.memberValuePairs;
        ClassLiteralAccess classLiteral = new ClassLiteralAccess(0, EclipseAstUtil.createTypeReference(idClassQualifiedName));
        MemberValuePair pair = new MemberValuePair("value".toCharArray(), 0,0, classLiteral);
        annotation.memberValuePairs = new MemberValuePair[] {pair};
        typeDeclaration.annotations = appendAnnotation(annotation, typeDeclaration.annotations);
    }

    private static Annotation[] appendAnnotation(Annotation newAnnotation, Annotation[] annotations) {
        Annotation [] output = Arrays.copyOf(annotations, annotations.length+1);
        output[output.length-1] = newAnnotation;
        return output;
    }

    private static Annotation[] copyAnnotaitonsAndRemoveId(Annotation[] input) {

        if(input == null || input.length <= 1) {
            return new Annotation[0];
        }

        Annotation [] output = new Annotation[input.length-1];

        int outIter = 0;
        for(int i = 0; i < input.length; i++){
            if(input[i].type.toString().equals(Id.class.getName()))
                continue;
            if(outIter > output.length)
            	break;
            output[outIter++] = input[i];
        }
        
        return output;
    }

    private static FieldDeclaration[] getFieldDeclarationArr(List<FieldDeclaration> variables) {
        FieldDeclaration [] fieldDeclarations = new FieldDeclaration[variables.size()];
        int i = 0;
        for(FieldDeclaration fd : variables)
            fieldDeclarations[i++] = fd;
        return fieldDeclarations;
    }


    private static void createNonSuperEntityFields(EclipseNode typeNode) {
        TypeDeclaration typeDecl = (TypeDeclaration) typeNode.get();

        if (!fieldExist(typeDecl.fields, StringUtil.getVariableName("actions"))) {
            FieldDeclaration decl = new FieldDeclaration("actions".toCharArray(), 0, 0);
            decl.modifiers = decl.modifiers | ClassFileConstants.AccPrivate;
            TypeReference typeArg = EclipseAstUtil.createTypeReference(DbActionDefinition.class.getName());
            //return type
            String list = EclipseImportsUtil.getTypeName(typeNode.getAst(), java.util.List.class);
            decl.type = EclipseAstUtil.createParametrizedTypeReference(list, typeArg, 1);
            //initializer
            AllocationExpression allocationExpression = new AllocationExpression();
            allocationExpression.type = EclipseAstUtil.createParametrizedTypeReference(
                    EclipseImportsUtil.getTypeName(typeNode.getAst(), ArrayList.class), typeArg, 1);
            decl.initialization = allocationExpression;

            //to hide setter need to add @Setter(value = AccessLevel.NONE)
//			EclipseAstUtil.addLombokSetterOnField(decl, AccessLevel.NONE);

            EclipseHandlerUtil.injectField(typeNode, decl);
        }

        if (!fieldExist(typeDecl.fields, StringUtil.getVariableName("serialVersionUID"))) {
            FieldDeclaration serialVersion = createSerialVersionUID();
            EclipseHandlerUtil.injectField(typeNode, serialVersion);
        }

    }

    private static boolean hasMultipleId(EclipseNode typeNode) {
        findIdFields(typeNode);
        if (idNodes.size() > 1) return true;
        return false;
    }

    //return List of JavacNodes which have @Id annotation
    private static void  findIdFields(EclipseNode typeNode) {
        idNodes = new ArrayList	();
        for (EclipseNode child : typeNode.down()) {
            if (child.getKind() != AST.Kind.FIELD || !hasAnnotation(Id.class, child)) {
                continue;
            }

            // TODO add filterField
            // if (filterField(fieldDecl)) {
            // continue;
            // }

            idNodes.add(child);
        }
    }

    private static FieldDeclaration createSerialVersionUID(){
        FieldDeclaration serialDecl = new FieldDeclaration("serialVersionUID".toCharArray(), 0,0);
        serialDecl.modifiers = serialDecl.modifiers | ClassFileConstants.AccPrivate | ClassFileConstants.AccFinal | ClassFileConstants.AccStatic;
        serialDecl.type = EclipseAstUtil.createTypeReference("long");
        serialDecl.initialization = LongLiteral.buildLongLiteral("1L".toCharArray(),0,0);
        return serialDecl;
    }

    private static boolean fieldExist(FieldDeclaration[] fields, String fieldName) {
        if (fields == null || fields.length == 0 || fieldName == null || fieldName.trim().isEmpty()) {
            return false;
        }
        for (FieldDeclaration declaration : fields) {
            if (fieldName.equals(declaration.name)) {
                return true;
            }
        }
        return false;
    }

}
