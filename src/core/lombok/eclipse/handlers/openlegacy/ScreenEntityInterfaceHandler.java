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

package lombok.eclipse.handlers.openlegacy;

import lombok.AccessLevel;
import lombok.core.AST.Kind;
import lombok.core.AnnotationValues;
import lombok.core.handlers.HandlerUtil;
import lombok.eclipse.Eclipse;
import lombok.eclipse.EclipseNode;
import lombok.eclipse.handlers.EclipseHandlerUtil;
import lombok.eclipse.handlers.EclipseHandlerUtil.*;
import lombok.eclipse.handlers.builders.FieldDeclBuilderImpl;
import lombok.experimental.Accessors;
import openlegacy.utils.EclipseAstUtil;
import openlegacy.utils.EclipseImportsUtil;
import openlegacy.utils.StringUtil;
import org.eclipse.jdt.internal.compiler.ast.AllocationExpression;
import org.eclipse.jdt.internal.compiler.ast.Argument;
import org.eclipse.jdt.internal.compiler.ast.FieldDeclaration;
import org.eclipse.jdt.internal.compiler.ast.FieldReference;
import org.eclipse.jdt.internal.compiler.ast.MethodDeclaration;
import org.eclipse.jdt.internal.compiler.ast.ReturnStatement;
import org.eclipse.jdt.internal.compiler.ast.Statement;
import org.eclipse.jdt.internal.compiler.ast.ThisReference;
import org.eclipse.jdt.internal.compiler.ast.TypeDeclaration;
import org.eclipse.jdt.internal.compiler.ast.TypeReference;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFileConstants;
import org.openlegacy.annotations.screen.ScreenDescriptionField;
import org.openlegacy.annotations.screen.ScreenField;
import org.openlegacy.annotations.screen.ScreenFieldValues;
import org.openlegacy.terminal.TerminalField;
import org.openlegacy.terminal.TerminalSnapshot;
import org.openlegacy.terminal.definitions.TerminalActionDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static lombok.eclipse.Eclipse.*;
import static lombok.eclipse.handlers.EclipseHandlerUtil.*;
import static lombok.eclipse.handlers.openlegacy.EclipseHandlerUtil.*;

/**
 * @author Ivan Bort
 * @since 3.6.0-SNAPSHOT
 */
public class ScreenEntityInterfaceHandler {

    private static String terminalField;
    private static String terminalSnapshot;
    private static String terminalActionDefinition;

    /**
     * Main entry point, which will generate all required fields
     */

    public static void handle(EclipseNode typeNode, boolean supportTerminalData) {

        if (supportTerminalData) {
            // get names to be used in the CU
            terminalField = EclipseImportsUtil.getTypeName(typeNode.getAst(), TerminalField.class);
            terminalSnapshot = EclipseImportsUtil.getTypeName(typeNode.getAst(), TerminalSnapshot.class);
        }
        List<FieldDeclaration> newFields = new ArrayList();
        addImplements(typeNode, org.openlegacy.terminal.ScreenEntity.class);
        createNonSuperEntityFields(typeNode, newFields, supportTerminalData);
        createFieldBasedFields(typeNode, newFields, supportTerminalData);
        // add new fields into the type declaration
        injectFields(typeNode, newFields);
    }

    /**
     * Creates predefined fields for not super entity class
     */
    private static void createNonSuperEntityFields(EclipseNode typeNode, List<FieldDeclaration> newFields, boolean supportTerminalData) {
        TypeDeclaration typeDecl = (TypeDeclaration) typeNode.get();
        //create terminalSnapshot field
        if (supportTerminalData && !fieldExist(typeDecl.fields, StringUtil.getVariableName(terminalSnapshot))) {
            FieldDeclaration decl = new FieldDeclBuilderImpl(terminalSnapshot)
                    .withModifiers(EclipseModifier.PRIVATE)
                    .withType(TerminalSnapshot.class)
                    .build();
//            FieldDeclaration decl = new FieldDeclaration(StringUtil.getVariableName(terminalSnapshot).toCharArray(), 0, 0);
//            decl.modifiers = decl.modifiers | ClassFileConstants.AccPrivate;
//            decl.type = EclipseAstUtil.createTypeReference(terminalSnapshot);
            //to hide setter need to add @Setter(value = AccessLevel.NONE)
//			EclipseAstUtil.addLombokSetterOnField(decl, AccessLevel.NONE);
            newFields.add(decl);
        }
        //create focusField field
        if (!fieldExist(typeDecl.fields, StringUtil.getVariableName("focusField"))) {
            FieldDeclaration decl = new FieldDeclBuilderImpl("focusField")
                    .withModifiers(EclipseModifier.PRIVATE)
                    .withType(String.class)
                    .build();
//            FieldDeclaration decl = new FieldDeclaration("focusField".toCharArray(), 0, 0);
//            decl.modifiers = decl.modifiers | ClassFileConstants.AccPrivate;
//            decl.type = EclipseAstUtil.createTypeReference(String.class.getSimpleName());
            newFields.add(decl);
        }
        // create pcCommand field
        if (!fieldExist(typeDecl.fields, StringUtil.getVariableName("pcCommand"))) {
            FieldDeclaration decl = new FieldDeclBuilderImpl("pcCommand")
                    .withModifiers(EclipseModifier.PRIVATE)
                    .withType(String.class)
                    .build();
//            FieldDeclaration decl = new FieldDeclaration("pcCommand".toCharArray(), 0, 0);
//            decl.modifiers = decl.modifiers | ClassFileConstants.AccPrivate;
//            decl.type = EclipseAstUtil.createTypeReference(String.class.getSimpleName());
            newFields.add(decl);
        }
        //create actions field
        if (!fieldExist(typeDecl.fields, StringUtil.getVariableName("actions"))) {
            FieldDeclaration decl = new FieldDeclBuilderImpl("actions")
                    .withModifiers(EclipseModifier.PRIVATE)
                    .withType(List.class)
                    .withDiamondsType(TerminalActionDefinition.class)
                    .withInitialization(ArrayList.class)
                    .build();
//            FieldDeclaration decl = new FieldDeclaration("actions".toCharArray(), 0, 0);
//            decl.modifiers = decl.modifiers | ClassFileConstants.AccPrivate;
//            TypeReference typeArg = EclipseAstUtil.createTypeReference(terminalActionDefinition);
//            //return type
//            String list = EclipseImportsUtil.getTypeName(typeNode.getAst(), List.class);
//            decl.type = EclipseAstUtil.createParametrizedTypeReference(list, typeArg, 1);
//            //initializer
//            AllocationExpression allocationExpression = new AllocationExpression();
//            allocationExpression.type = EclipseAstUtil.createParametrizedTypeReference(
//                    EclipseImportsUtil.getTypeName(typeNode.getAst(), ArrayList.class), typeArg, 1);
//            decl.initialization = allocationExpression;
            //to hide setter need to add @Setter(value = AccessLevel.NONE)
//			EclipseAstUtil.addLombokSetterOnField(decl, AccessLevel.NONE);
            newFields.add(decl);
        }
    }

    /**
     * Creates fields based on declared fields inside the entity class. Possible suffixes: *Field, *Values, *Description, *Actions
     */
    private static void createFieldBasedFields(EclipseNode typeNode, List<FieldDeclaration> newFields, boolean supportTerminalData) {
        TypeDeclaration typeDecl = (TypeDeclaration) typeNode.get();
        // walk through all fields annotated with @ScreenField
        for (EclipseNode fieldNode : findAllScreenFields(typeNode)) {
            FieldDeclaration field = (FieldDeclaration) fieldNode.get();
            //******
            //try to create "private TerminalField *Field" if getter for it doesn't exist & satisfy other conditions
            String nameWithFieldSuffix = fieldNode.getName() + "Field";
            TypeReference fieldType = copyType(field.type, field);
            boolean isBoolean = isBoolean(fieldType);
            String getterName = HandlerUtil.toGetterName(fieldNode.getAst(), AnnotationValues.of(Accessors.class, fieldNode),
                    nameWithFieldSuffix, isBoolean);
            boolean primitive = isPrimitive(fieldType);
            if (MemberExistsResult.NOT_EXISTS.equals(EclipseHandlerUtil.methodExists(getterName, typeNode, -1))
                    && supportTerminalData && primitive && !fieldExist(typeDecl.fields, nameWithFieldSuffix)) {
                FieldDeclaration decl = new FieldDeclaration(nameWithFieldSuffix.toCharArray(), 0, 0);
                decl.modifiers = decl.modifiers | ClassFileConstants.AccPrivate;
                decl.type = EclipseAstUtil.createTypeReference(terminalField);
                //to hide setter need to add @Setter(value = AccessLevel.NONE)
//				EclipseAstUtil.addLombokSetterOnField(decl, AccessLevel.NONE);
                newFields.add(decl);
            }
            //*****
            //try to create "private Map<Object,Object> *Values"
            if (EclipseHandlerUtil.hasAnnotation(ScreenFieldValues.class, fieldNode)) {
                String nameWithValuesSuffix = fieldNode.getName() + "Values";
                TypeReference typeArg = EclipseAstUtil.createTypeReference(Object.class.getName());
                FieldDeclaration decl = new FieldDeclaration(nameWithValuesSuffix.toCharArray(), 0, 0);
                decl.modifiers = decl.modifiers | ClassFileConstants.AccPrivate;
                decl.type = EclipseAstUtil.createParametrizedTypeReference(
                        EclipseImportsUtil.getTypeName(typeNode.getAst(), Map.class), typeArg, 2);
                //to hide setter need to add @Setter(value = AccessLevel.NONE)
                EclipseAstUtil.addLombokSetterOnField(decl, AccessLevel.NONE);
                newFields.add(decl);
                //TODO generate
                /*method public Map<Object,Object> ${className}.get${field.name?cap_first}Values(String text) {
                *return ${field.name}Values;
				*}*/
                int pS = decl.sourceStart, pE = decl.sourceEnd;
                long p = (long) pS << 32 | pE;
                FieldReference ref = new FieldReference(decl.name, p);
                ref.receiver = new ThisReference(pS, pE);
                setGeneratedBy(ref, decl);
                setGeneratedBy(ref.receiver, decl);
                ReturnStatement returnStatement = new ReturnStatement(ref, decl.sourceStart, decl.sourceEnd);

                Argument arg = new Argument("text".toCharArray(), 0,
                        EclipseAstUtil.createTypeReference(String.class.getSimpleName()), 0);
                MethodDeclaration method = new MethodDeclaration(typeDecl.compilationResult);
                method.modifiers = ClassFileConstants.AccPublic;
                method.returnType = EclipseHandlerUtil.copyType(decl.type, decl);
                method.annotations = null;
                method.arguments = new Argument[]{arg};
                method.selector = HandlerUtil.toGetterName(fieldNode.getAst(), AnnotationValues.of(Accessors.class, fieldNode),
                        nameWithValuesSuffix, isBoolean).toCharArray();
                method.binding = null;
                method.thrownExceptions = null;
                method.typeParameters = null;
                method.bits |= ECLIPSE_DO_NOT_TOUCH_FLAG;
                method.bodyStart = method.declarationSourceStart = method.sourceStart = decl.sourceStart;
                method.bodyEnd = method.declarationSourceEnd = method.sourceEnd = decl.sourceEnd;
                method.statements = new Statement[]{returnStatement};
                EclipseHandlerUtil.injectMethod(typeNode, method);
            }
            //*****
            //try to create "private String *Description"
            if (EclipseHandlerUtil.hasAnnotation(ScreenDescriptionField.class, fieldNode)) {
                String nameWithDescriptionSuffix = fieldNode.getName() + "Description";
                FieldDeclaration decl = new FieldDeclaration(nameWithDescriptionSuffix.toCharArray(), 0, 0);
                decl.modifiers = decl.modifiers | ClassFileConstants.AccPrivate;
                decl.type = EclipseAstUtil.createTypeReference(String.class.getSimpleName());
                //to hide setter need to add @Setter(value = AccessLevel.NONE)
                EclipseAstUtil.addLombokSetterOnField(decl, AccessLevel.NONE);
                newFields.add(decl);
            }
            //***** ??
            //try to create "private List<TerminalActionDefinition> *Actions = new ArrayList<TerminalActionDefinition>()"
            //if field type is List
            char[][] typeChar = fieldType.getTypeName();
            String typeName = new String(typeChar[typeChar.length - 1]);
            if (typeName.equals("List")) {
                String nameWithActionsSuffix = fieldNode.getName() + "Actions";
                FieldDeclaration decl = new FieldDeclaration(nameWithActionsSuffix.toCharArray(), 0, 0);
                decl.modifiers = decl.modifiers | ClassFileConstants.AccPrivate;
                TypeReference typeArg = EclipseAstUtil.createTypeReference(terminalActionDefinition);
                //return type
                String list = EclipseImportsUtil.getTypeName(typeNode.getAst(), List.class);
                decl.type = EclipseAstUtil.createParametrizedTypeReference(list, typeArg, 1);
                //initializer
                AllocationExpression allocationExpression = new AllocationExpression();
                allocationExpression.type = EclipseAstUtil.createParametrizedTypeReference(
                        EclipseImportsUtil.getTypeName(typeNode.getAst(), ArrayList.class), typeArg, 1);
                decl.initialization = allocationExpression;
                //to hide setter need to add @Setter(value = AccessLevel.NONE)
//				EclipseAstUtil.addLombokSetterOnField(decl, AccessLevel.NONE);
                newFields.add(decl);
            }
        }
    }

    private static List<EclipseNode> findAllScreenFields(EclipseNode typeNode) {
        List<EclipseNode> fields = new ArrayList<EclipseNode>();
        for (EclipseNode child : typeNode.down()) {
            if (child.getKind() != Kind.FIELD) {
                continue;
            }
            FieldDeclaration fieldDecl = (FieldDeclaration) child.get();
            if (!EclipseHandlerUtil.hasAnnotation(ScreenField.class, child)) {
                continue;
            }
            if (!filterField(fieldDecl)) {
                continue;
            }

            fields.add(child);
        }
        return fields;
    }

    private static void injectFields(EclipseNode typeNode, List<FieldDeclaration> fields) {
        for (FieldDeclaration field : fields) {
            EclipseHandlerUtil.injectField(typeNode, field);
        }
    }


    private static boolean fieldExist(FieldDeclaration[] fields, String fieldName) {
        if (fields == null || fields.length == 0 || fieldName == null || fieldName.trim().isEmpty()) {
            return false;
        }
        for (FieldDeclaration declaration : fields) {
            if (fieldName.equals(new String(declaration.name))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPrimitive(TypeReference typeReference) {
        if (typeReference.dimensions() != 0) {
            return false;
        }

        if (Eclipse.nameEquals(typeReference.getTypeName(), "int")) {
            return true;
        }
        if (Eclipse.nameEquals(typeReference.getTypeName(), "String")) {
            return true;
        }
        if (Eclipse.nameEquals(typeReference.getTypeName(), "BigInteger")) {
            return true;
        }
        if (Eclipse.nameEquals(typeReference.getTypeName(), "Integer")) {
            return true;
        }
        if (Eclipse.nameEquals(typeReference.getTypeName(), "Boolean")
                || Eclipse.nameEquals(typeReference.getTypeName(), "boolean")) {
            return true;
        }
        if (Eclipse.nameEquals(typeReference.getTypeName(), "Date")) {
            return true;
        }
        if (Eclipse.nameEquals(typeReference.getTypeName(), "Double")) {
            return true;
        }
        return false;
    }
}
