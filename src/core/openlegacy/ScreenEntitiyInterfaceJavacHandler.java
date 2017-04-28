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
package openlegacy;

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

import lombok.javac.JavacNode;
import lombok.javac.JavacTreeMaker;
import lombok.javac.handlers.HandleOLData;
import lombok.javac.handlers.JavacHandlerUtil;

import openlegacy.utils.JavacOLUtil;
import openlegacy.utils.StringUtil;
import org.openlegacy.annotations.screen.ScreenEntity;
import org.openlegacy.annotations.screen.ScreenEntitySuperClass;

import org.openlegacy.terminal.TerminalField;
import org.openlegacy.terminal.TerminalSnapshot;
import org.openlegacy.terminal.definitions.TerminalActionDefinition;

import java.util.ArrayList;

/**
 * @author Matvey Mitnitsky
 * @since 3.6.0-SNAPSHOT
 */
public class ScreenEntitiyInterfaceJavacHandler {

    private String terminalField;
    private String terminalSnapshot;
    private String terminalActionDefinition;

    public void handle(JavacNode typeNode, JavacNode source) {
        JCClassDecl typeDecl = HandleOLData.checkAnnotation(typeNode, source);
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
        if (!superClassAnnotation) {
            // get names to be used in the CU
            terminalActionDefinition = JavacOLUtil.getFullyQualifiedName(TerminalActionDefinition.class);
        }

        java.util.List<JCVariableDecl> newFields = new ArrayList<JCVariableDecl>();
        Object objects = null;
        if (!superClassAnnotation) {
            createNonSuperEntityFields(typeNode, newFields, supportTerminalData);
        }

        //TODO implement createFieldBasedFields method
//        createFieldBasedFields(typeNode, newFields, supportTerminalData);
        // add new fields into the type declaration
        JavacOLUtil.injectFields(typeNode, newFields);
    }

    private void createNonSuperEntityFields(JavacNode typeNode, java.util.List<JCVariableDecl> newFields, boolean supportTerminalData) {
        JCClassDecl typeDecl = (JCClassDecl) typeNode.get();
        JavacTreeMaker jcMaker = typeNode.getTreeMaker();
        //create terminalSnapshot field
//        if (supportTerminalData && !fieldExist((JCVariableDecl) typeDecl.getMembers(), StringUtil.getVariableName(terminalSnapshot))) {
        //TODO take care of another conditions (e.g. fieldExists)
        if (supportTerminalData) {
            JCVariableDecl terminalDataDecl = jcMaker.VarDef(
                    jcMaker.Modifiers(Flags.PRIVATE),
                    typeNode.toName(StringUtil.getVariableName(terminalSnapshot)),
                    JavacOLUtil.getJCExpresssionForType(typeNode, terminalSnapshot),
                    null);
            //to hide setter need to add @Setter(value = AccessLevel.NONE)
            newFields.add(terminalDataDecl);
        }

        //TODO take into account an existing field condition
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

        JCVariableDecl focusDecl = jcMaker.VarDef(
                jcMaker.Modifiers(Flags.PRIVATE),
                typeNode.toName("focusField"),
                JavacOLUtil.getJCExpresssionForJavaLangType(typeNode, "String"),
                null);

        newFields.add(focusDecl);
        JCVariableDecl pcCommandDecl = jcMaker.VarDef(
                jcMaker.Modifiers(Flags.PRIVATE),
                typeNode.toName("pcCommand"),
                JavacOLUtil.getJCExpresssionForJavaLangType(typeNode, "String"),
                null);
        newFields.add(pcCommandDecl);
     }

    private static boolean fieldExist(List<JCVariableDecl> fields, String fieldName) {
        if (fields == null || fields.size() == 0 || fieldName == null || fieldName.trim().isEmpty()) {
            return false;
        }
        for (JCVariableDecl field : fields) {
            if (fieldName.equals(field.name)) {
                return true;
            }
        }
        return false;
    }

    /*
     * get supportTerminalData attribute value
     */
    private boolean supportTerminalData(JavacNode typeNode, List<JCAnnotation> annotations) {
        JCAnnotation ann = null;
        for (JCAnnotation annotation : annotations) {
        	String singleString = null;
        	JCTree annTree = annotation.getAnnotationType();
//        	if(annTree instanceof JCFieldAccess)
//        		singleString = ((JCFieldAccess) annTree).name.toString();
            /*
             * different Annotations has different JC class representation
             * for example JCFieldAccess class represents OLData annotation
             */
        	if(annTree instanceof JCIdent)
        		singleString = ((JCIdent) annTree).name.toString();
        	
        	if(singleString == null)
        		continue;

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
