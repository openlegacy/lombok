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
package openlegacy.javac;

import com.sun.tools.javac.util.*;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.JCAnnotation;
import com.sun.tools.javac.tree.JCTree.JCExpression;
import com.sun.tools.javac.tree.JCTree.JCFieldAccess;
import com.sun.tools.javac.tree.JCTree.JCIdent;
import com.sun.tools.javac.tree.JCTree.JCLiteral;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import com.sun.tools.javac.tree.JCTree.JCClassDecl;
import com.sun.tools.javac.tree.JCTree.JCAssign;
import com.sun.tools.javac.code.Flags;

import com.sun.tools.javac.util.List;
import lombok.OLData;
import lombok.core.AST;
import lombok.eclipse.Eclipse;
import lombok.javac.JavacNode;
import lombok.javac.JavacTreeMaker;
import lombok.javac.handlers.HandleOLData;
import lombok.javac.handlers.JavacHandlerUtil;

import openlegacy.utils.JavacOLUtil;
import openlegacy.utils.OLJavacHandlerUtil;
import openlegacy.utils.StringUtil;
import org.apache.tools.ant.taskdefs.Java;
import org.openlegacy.annotations.screen.ScreenDescriptionField;
import org.openlegacy.annotations.screen.ScreenEntity;
import org.openlegacy.annotations.screen.ScreenEntitySuperClass;

import org.openlegacy.annotations.screen.ScreenField;
import org.openlegacy.terminal.TerminalField;
import org.openlegacy.terminal.TerminalSnapshot;
import org.openlegacy.terminal.definitions.TerminalActionDefinition;

import java.util.*;

import static openlegacy.utils.OLJavacHandlerUtil.*;

/**
 * @author Matvey Mitnitsky
 * @since 3.6.0-SNAPSHOT
 */
public class ScreenEntitiyInterfaceHandler {

    private String terminalField;
    private String terminalSnapshot;

    public void handle(JavacNode typeNode, JavacNode source) {
        JCClassDecl typeDecl = checkAnnotation(typeNode, source);
        if (typeDecl == null) {
            return;
        }

        boolean superClassAnnotation = JavacHandlerUtil.hasAnnotation(ScreenEntitySuperClass.class, typeNode);
        List<JCAnnotation> annotations = typeDecl.getModifiers().annotations;
        boolean supportTerminalData = supportTerminalData(typeNode, annotations);

        if (supportTerminalData) {
            // get names to be used in the CU
            terminalField = JavacOLUtil.getFullyQualifiedName(TerminalField.class);
            terminalSnapshot = JavacOLUtil.getFullyQualifiedName(TerminalSnapshot.class);
        }
//        if (!superClassAnnotation) {
//            // get names to be used in the CU
//            terminalActionDefinition = JavacOLUtil.getFullyQualifiedName(TerminalActionDefinition.class);
//        }

        java.util.List<JCVariableDecl> newFields = new ArrayList<JCVariableDecl>();
        Object objects = null;
        if (!superClassAnnotation) {
            createNonSuperEntityFields(typeNode, newFields, supportTerminalData);
        }

        //TODO implement createFieldBasedFields method
        createFieldBasedFields(typeNode, newFields, supportTerminalData);
        // add new fields into the type declaration
        JavacOLUtil.injectFields(typeNode, newFields);
    }

    private void createNonSuperEntityFields(JavacNode typeNode, java.util.List<JCVariableDecl> newFields, boolean supportTerminalData) {
        JCClassDecl typeDecl = (JCClassDecl) typeNode.get();
        JavacTreeMaker jcMaker = typeNode.getTreeMaker();
        //create terminalSnapshot field
        if (supportTerminalData && !fieldExist(typeDecl, StringUtil.getVariableName(terminalSnapshot))) {
            JCVariableDecl terminalDataDecl = jcMaker.VarDef(
                    jcMaker.Modifiers(Flags.PRIVATE),
                    typeNode.toName(StringUtil.getVariableName(terminalSnapshot)),
                    JavacOLUtil.getJCExpresssionForType(typeNode, terminalSnapshot),
                    null);
            //to hide setter need to add @Setter(value = AccessLevel.NONE) ??
            newFields.add(terminalDataDecl);
        }

        if(!fieldExist(typeDecl, StringUtil.getVariableName("actions"))) {
            JCExpression listTypeRef = jcMaker.TypeApply(
                    JavacOLUtil.getJCExpresssionForType(typeNode, java.util.List.class),
                    List.<JCExpression>nil().append(JavacOLUtil.getJCExpresssionForType(typeNode, TerminalActionDefinition.class)));

            JCExpression listInit = jcMaker.NewClass(
                    null, List.<JCExpression>nil(),
                    JavacOLUtil.getJCExpresssionForType(typeNode, ArrayList.class),
                    List.<JCExpression>nil(),
                    null);

            JCVariableDecl actionsDecl = jcMaker.VarDef(
                    jcMaker.Modifiers(Flags.PRIVATE),
                    typeNode.toName("actions"),
                    listTypeRef,
                    listInit);

            newFields.add(actionsDecl);
        }

        if(!fieldExist(typeDecl, StringUtil.getVariableName("focusField"))) {
            JCVariableDecl focusDecl = jcMaker.VarDef(
                    jcMaker.Modifiers(Flags.PRIVATE),
                    typeNode.toName("focusField"),
                    JavacOLUtil.getJCExpresssionForJavaLangType(typeNode, "String"),
                    null);

            newFields.add(focusDecl);
        }

        if(!fieldExist(typeDecl, StringUtil.getVariableName("pcCommand"))) {
            JCVariableDecl pcCommandDecl = jcMaker.VarDef(
                    jcMaker.Modifiers(Flags.PRIVATE),
                    typeNode.toName("pcCommand"),
                    JavacOLUtil.getJCExpresssionForJavaLangType(typeNode, "String"),
                    null);
            newFields.add(pcCommandDecl);
        }
     }

    private void createFieldBasedFields(JavacNode typeNode, java.util.List<JCVariableDecl> newFields, boolean supportTerminalData){
        JCClassDecl typeDecl = (JCClassDecl) typeNode.get();
        JavacTreeMaker treeMaker = typeNode.getTreeMaker();

        for(JavacNode fieldNode : findAllScreenFields(typeNode)){
            JCVariableDecl field = (JCVariableDecl) fieldNode.get();

            String nameWithFieldSuffix = fieldNode.getName() + "Field";
            boolean isBoolean = JavacHandlerUtil.isBoolean(fieldNode);
            String getterName = JavacOLUtil.toGetterName(nameWithFieldSuffix);
            boolean isPrimitive = JavacOLUtil.isPrimitive(fieldNode);
//            JCExpression varTypeDecl = (JCExpression) ((JCVariableDecl) fieldNode.get()).getType();
            JCExpression varTypeDecl = JavacOLUtil.getJCExpresssionForType(typeNode, terminalField);
            if(JavacHandlerUtil.MemberExistsResult.NOT_EXISTS.equals(
                    JavacHandlerUtil.methodExists(getterName, fieldNode, isBoolean,0))
                    && supportTerminalData && isPrimitive && !fieldExist(typeDecl, nameWithFieldSuffix)) {

                JCVariableDecl terminalField = treeMaker.VarDef(
                        treeMaker.Modifiers(Flags.PRIVATE),
                        typeNode.toName(nameWithFieldSuffix),
                        varTypeDecl,
                        null
                );

                newFields.add(terminalField);
            }

            String varType = ((JCVariableDecl) fieldNode.get()).getType().toString();
            if(varType.contains("List<")){
                String nameWithActionsSuffix = fieldNode.getName() + "Actions";
                if(OLJavacHandlerUtil.fieldExist(typeNode, nameWithActionsSuffix) || fieldNode.getName().equals("actions"))
                    continue;

                java.util.List<Class<?>> typeArgs = new ArrayList();
                typeArgs.add(TerminalActionDefinition.class);
                JCExpression actionsFieldDecl = createVariableDeclarationForGenericClass(
                        typeNode,
                        java.util.List.class,
                        typeArgs);

                JCExpression actionsInit = createSimpleVariableInitialization(typeNode, ArrayList.class, null);
                JCVariableDecl actionsField = createVariableDefinition(
                        typeNode,
                        nameWithActionsSuffix,
                        actionsFieldDecl,
                        actionsInit
                );

                newFields.add(actionsField);
            }

            if(JavacHandlerUtil.hasAnnotation(ScreenDescriptionField.class, fieldNode)){
                String nameWithDescriptionSuffix = fieldNode.getName() + "Description";
                if(fieldExist(typeDecl, nameWithDescriptionSuffix))
                    continue;

                JCVariableDecl descriptionDecl =
                        createSimpleVariableDeclaration(typeNode, String.class, nameWithDescriptionSuffix);

                newFields.add(descriptionDecl);
            }

        }
    }



    //    private static boolean fieldExist(List<JCVariableDecl> fields, String fieldName) {
    private static boolean fieldExist(JCClassDecl typeDecl, String fieldName) {
        if (typeDecl == null || fieldName == null || fieldName.trim().isEmpty())
            return false;
        List<JCTree> defs = typeDecl.getMembers();
        for(JCTree def : defs)
            if(JCVariableDecl.class.isInstance(def)
                && (((JCVariableDecl) def).getName().toString()).equals(fieldName)){
            		return true;
            }
        return false;
    }

    private static java.util.List<JavacNode> findAllScreenFields(JavacNode typeNode) {
        java.util.List<JavacNode> fields = new java.util.ArrayList<JavacNode>();
        for (JavacNode child : typeNode.down()) {
            if (child.getKind() != AST.Kind.FIELD) {
                continue;
            }
//            JCVariableDecl fieldDecl = (JCVariableDecl) child.get();
            if (!JavacHandlerUtil.hasAnnotation(ScreenField.class, child)) {
                continue;
            }

            //TODO add filterField
//            if (filterField(fieldDecl)) {
//                continue;
//            }

            fields.add(child);
        }
        return fields;
    }

    /*
     * get supportTerminalData attribute value
     */
    private boolean supportTerminalData(JavacNode typeNode, List<JCAnnotation> annotations) {
        JCAnnotation ann = null;
        boolean fullyQualifiedAnn = false;

        for (JCAnnotation annotation : annotations) {
        	String singleString = null;
            JavacNode annotationNode = typeNode.getNodeFor(annotation);
//            String annotationName = annotationNode.get().getName().toString();
//            String annotaitonKind = annotation.getKind().name();
//            String annElementName = annotationNode.getElement().getSimpleName().toString();
            JCTree annTree = annotation.getAnnotationType();
        	
//        	if(annTree instanceof JCFieldAccess)
//        		singleString = ((JCFieldAccess) annTree).name.toString();
            /*
             * different Annotations has different JC class representation
             * for example JCFieldAccess class represents fully qualified annotation
             * and JCIndent represents simple named annotation
             */
            if(annTree instanceof JCFieldAccess) {
                singleString = ((JCFieldAccess) annTree).name.toString();
                fullyQualifiedAnn = true;
            }
        	if(annTree instanceof JCIdent)
        		singleString = ((JCIdent) annTree).name.toString();
        	if(singleString == null)
        		continue;
//            String screenEntityName = !singleString.contains("\\.") ? ScreenEntity.class.getSimpleName()
//                    : ScreenEntity.class.getName();
            String screenEntityName = !singleString.contains("\\.") ? ScreenEntity.class.getSimpleName()
                    : ScreenEntity.class.getName();
            if (screenEntityName.equals(singleString)) {
                ann = annotation;
                break;
            }
        }

        /*
         * retrieving supportTerminalData attribute
         */

        if (ann != null) {
            String name = ((JCIdent) ((JCAssign) ann.args.get(0)).getVariable()).getName().toString();
//            String expressionClassName = ((JCLiteral) ((JCAssign) ann.args.get(0)).getExpression()).getValue();
            boolean supportTerminalData = false;
            // TODO need to consult with Tom about casting to requiered class instead of instanceof
            /*
             * The following casting sequence was performed for attribute value:
             * JCExpression -> JCAssign -> JCLiteral -> Boolean (boolean attribute value);
             *
             * and for attribute identifier:
             * JCExpression -> JCAssign -> JCIdent (identifier name was retrieved from this class object);
             */
            for(JCExpression jcArgument : ann.args){
                JCAssign assign = null;

                if(!JCAssign.class.isInstance(jcArgument))
                    continue;
                assign = (JCAssign) jcArgument;

                if(!JCIdent.class.isInstance(assign.getVariable()))
                    continue;
                JCIdent identifier = (JCIdent) (assign).getVariable();

                if(!(identifier.getName().toString().equals("supportTerminalData") &&
                        JCLiteral.class.isInstance(assign.getExpression())))
                    continue;

                supportTerminalData = (Boolean) ((JCLiteral) assign.getExpression()).getValue();
            }

            if (supportTerminalData)
                return true;

        }

        return false;
    }
}
