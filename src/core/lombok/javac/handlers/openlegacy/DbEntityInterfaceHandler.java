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
package lombok.javac.handlers.openlegacy;

import static lombok.javac.handlers.OLJavacHandlerUtil.*;

import java.util.ArrayList;

import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Transient;

import com.sun.tools.javac.code.Type;
import lombok.AccessLevel;
import lombok.javac.handlers.HandleGetter;
import lombok.javac.handlers.HandleSetter;
import org.openlegacy.db.definitions.DbActionDefinition;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.JCAnnotation;
import com.sun.tools.javac.tree.JCTree.JCClassDecl;
import com.sun.tools.javac.tree.JCTree.JCExpression;
import com.sun.tools.javac.tree.JCTree.JCFieldAccess;
import com.sun.tools.javac.tree.JCTree.JCIdent;
import com.sun.tools.javac.tree.JCTree.JCTypeParameter;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import com.sun.tools.javac.util.List;

import lombok.core.AST;
import lombok.javac.Javac;
import lombok.javac.JavacNode;
import lombok.javac.JavacTreeMaker;
import lombok.javac.handlers.JavacHandlerUtil;
import lombok.javac.handlers.JavacOLUtil;
import openlegacy.utils.StringUtil;

/**
 * @author Matvey Mitnitsky
 * @since 3.6.0-SNAPSHOT
 */
public class DbEntityInterfaceHandler {

    private static java.util.List<JavacNode> idNodes = null;

    public static void handle(JavacNode typeNode, JavacNode annotationNode) {
        JCClassDecl typeDecl = checkAnnotation(typeNode, annotationNode);
        if (typeDecl == null) {
            return;
        }

        createNonSuperEntityFields(typeNode);

        //TODO add if exists condition
        if (hasMultipleId(typeNode)) {
            createCompositeKey(typeNode);
        }
    }

    private static void createCompositeKey(JavacNode typeNode) {
        JavacTreeMaker treeMaker = typeNode.getTreeMaker();

        String className = ((JCClassDecl) typeNode.get()).name.toString();
        String idClassNameWithSuffix = className + "CompositeKey";

        //List of definitions inside the new nested class (serialVersionUID is added)
        List<JCTree> classDefs = List.<JCTree>nil().append(createSerialVersionUID(typeNode));

        /*
         * Remove @Id annotation in the loop
         */
        for (JavacNode idNode : idNodes) {
            for (JavacNode idChild : idNode.down()) {
                if (idChild.getKind() == AST.Kind.ANNOTATION) {
                    Type annType = ((JCAnnotation) idChild.get()).type;
                    String annNameFromType = annType.tsym.toString();

                    if (annNameFromType.trim().isEmpty())
                        continue;

                    if (annNameFromType.equals("javax.persistence.Id")) {
                        JCVariableDecl idNodeDecl = ((JCVariableDecl) idNode.get());
                        JCAnnotation annotationToBeRemoved = (JCAnnotation) idChild.get();
                        java.util.List<JCAnnotation> annUtilList = new ArrayList(idNodeDecl.mods.annotations);
                        annUtilList.remove(annotationToBeRemoved);

                        // Create copy of Variable declaration to avoid influence on the enclosing entity class fields
                        JCVariableDecl idNodeCopy = treeMaker.VarDef(
                                treeMaker.Modifiers(Flags.PRIVATE),
                                idNodeDecl.name,
                                idNodeDecl.vartype,
                                idNodeDecl.init
                        );
                        idNodeCopy.mods.annotations = getJavacListFromJavaUtilList(annUtilList);
                        classDefs = classDefs.append(idNodeCopy);
                    }
                }
            }
        }

        //Declaration of the new nested class
        JCClassDecl idClassDecl = treeMaker.ClassDef(
                treeMaker.Modifiers(Flags.PUBLIC | Flags.STATIC),
                typeNode.toName(idClassNameWithSuffix),
                List.<JCTypeParameter>nil(),
                null,
                List.<JCExpression>nil(),
                classDefs);
        List<JCAnnotation> idClassAnn = idClassDecl.mods.annotations;


        // inject new Type inside enclosing entity
        JavacNode idClassTypeNode = JavacHandlerUtil.injectType(typeNode, idClassDecl);

        //generate getters and setters for nested class
        new HandleGetter().generateGetterForType(idClassTypeNode, idClassTypeNode.up(), AccessLevel.PUBLIC, true);
        new HandleSetter().generateSetterForType(idClassTypeNode, idClassTypeNode.up(), AccessLevel.PUBLIC, true);
        String qualifiedName = className + "." + idClassNameWithSuffix;

        JCExpression argument = treeMaker.Assign(
                treeMaker.Ident(typeNode.toName("value")),
                treeMaker.Select(JavacOLUtil.getJCExpresssionForType(typeNode, qualifiedName), typeNode.toName("class")));

        List<JCAnnotation> annotations = ((JCClassDecl) typeNode.get()).getModifiers().annotations;
        annotations = annotations.append(
                treeMaker.Annotation(JavacOLUtil.getJCExpresssionForType(typeNode, IdClass.class),
                        List.<JCExpression>nil().append(argument)
                )
        );
        ((JCClassDecl) typeNode.get()).getModifiers().annotations = annotations;
    }

    //checks for more then one Id in the entity class
    private static boolean hasMultipleId(JavacNode typeNode) {
        idNodes = findIdFields(typeNode);
        if (idNodes.size() > 1) return true;
        return false;
    }

    //return List of JavacNodes which have @Id annotation
    private static java.util.List<JavacNode> findIdFields(JavacNode typeNode) {
        java.util.List<JavacNode> fields = new java.util.ArrayList<JavacNode>();
        for (JavacNode child : typeNode.down()) {
            if (child.getKind() != AST.Kind.FIELD || !JavacHandlerUtil.hasAnnotation(Id.class, child)) {
                continue;
            }

            // TODO add filterField
            // if (filterField(fieldDecl)) {
            // continue;
            // }

            fields.add(child);
        }
        return fields;
    }

    private static void createNonSuperEntityFields(JavacNode typeNode) {
        JavacTreeMaker jcMaker = typeNode.getTreeMaker();

        if (!fieldExist(typeNode, StringUtil.getVariableName("actions"))) {
            JCExpression listTypeRef = jcMaker.TypeApply(JavacOLUtil.getJCExpresssionForType(typeNode, java.util.List.class), List.<JCExpression>nil().append(JavacOLUtil.getJCExpresssionForType(typeNode, DbActionDefinition.class)));

            JCExpression listInit = jcMaker.NewClass(null, List.<JCExpression>nil(), JavacOLUtil.getJCExpresssionForType(typeNode, ArrayList.class), List.<JCExpression>nil(), null);

            JCVariableDecl actionsDecl = jcMaker.VarDef(jcMaker.Modifiers(Flags.PRIVATE), typeNode.toName("actions"), listTypeRef, listInit);

            actionsDecl.getModifiers().annotations = List.<JCAnnotation>nil().append(jcMaker.Annotation(JavacOLUtil.getJCExpresssionForType(typeNode, Transient.class), List.<JCExpression>nil())).append(jcMaker.Annotation(JavacOLUtil.getJCExpresssionForType(typeNode, JsonIgnore.class), List.<JCExpression>nil()));

            JavacHandlerUtil.injectField(typeNode, actionsDecl);
        }

        if (!fieldExist(typeNode, StringUtil.getVariableName("serialVersionUID"))) {

            JCVariableDecl serialVersion = createSerialVersionUID(typeNode);

            JavacHandlerUtil.injectField(typeNode, serialVersion);

        }

    }

    private static JCVariableDecl createSerialVersionUID(JavacNode typeNode){
        JavacTreeMaker treeMaker = typeNode.getTreeMaker();

        JCExpression serialInit = treeMaker.Literal(1L);

        JCExpression serialDecl = treeMaker.TypeIdent(Javac.CTC_LONG);

        JCVariableDecl serialDef = treeMaker.VarDef(treeMaker.Modifiers(Flags.PRIVATE | Flags.FINAL | Flags.STATIC), typeNode.toName("serialVersionUID"), serialDecl, serialInit);

        return serialDef;
    }

}
