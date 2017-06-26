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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.tree.JCTree.JCClassDecl;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import lombok.core.AST;
import lombok.javac.JavacNode;
import lombok.javac.handlers.JavacHandlerUtil;
import lombok.javac.handlers.JavacOLUtil;
import lombok.javac.handlers.OLJavacHandlerUtil;
import lombok.javac.handlers.builders.FieldDeclBuilder;
import openlegacy.utils.StringUtil;
import org.openlegacy.core.annotations.screen.ScreenDescriptionField;
import org.openlegacy.core.annotations.screen.ScreenField;
import org.openlegacy.core.terminal.TerminalField;
import org.openlegacy.core.terminal.TerminalSnapshot;
import org.openlegacy.core.terminal.definitions.TerminalActionDefinition;

import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

import static lombok.javac.handlers.OLJavacHandlerUtil.*;
import static openlegacy.LombokOLConstants.*;

/**
 * @author Matvey Mitnitsky
 * @since 3.6.0-SNAPSHOT
 */
public class ScreenEntityHandler {

    /**
     * Main entry point, which will generate all required code
     */
    public static void handle(JavacNode typeNode, boolean supportTerminalData, boolean screenEntity) {

        java.util.List<JCVariableDecl> newFields = new ArrayList<JCVariableDecl>();

        if (screenEntity) {
            addImplements(typeNode, org.openlegacy.core.terminal.ScreenEntity.class);
            createScreenEntityFields(typeNode, newFields, supportTerminalData);
        }
        createFieldBasedFields(typeNode, newFields, supportTerminalData);
        createFieldActions(typeNode, newFields);
        // add @XmlAccessorType(XmlAccessType.FIELD) to the class in order
        // to activate JAXB ignoring for metadata fields
        addXmlAccessorType(typeNode);

        // add new fields into the type declaration
        JavacOLUtil.injectFields(typeNode, newFields);
    }

    /**
     * Creates predefined fields for not super entity class
     */
    private static void createScreenEntityFields(JavacNode typeNode, java.util.List<JCVariableDecl> newFields, boolean supportTerminalData) {
        JCClassDecl typeDecl = (JCClassDecl) typeNode.get();

        //create terminalSnapshot field
        if (supportTerminalData && !fieldExist(typeDecl, TERMINAL_SNAPSHOT)) {
            JCVariableDecl terminalDataDecl = new FieldDeclBuilder(typeNode, TERMINAL_SNAPSHOT)
                    .withModifiers(Flags.PRIVATE)
                    .withType(TerminalSnapshot.class)
                    .setAnnotations(JsonIgnore.class, XmlTransient.class)
                    .build();
            newFields.add(terminalDataDecl);
        }

        if (!fieldExist(typeDecl, StringUtil.getVariableName(ACTIONS_FIELD_NAME))) {

            JCVariableDecl actionsDecl = new FieldDeclBuilder(typeNode, ACTIONS_FIELD_NAME)
                    .withModifiers(Flags.PRIVATE)
                    .withDiamondsType(java.util.List.class, TerminalActionDefinition.class)
                    .withDiamondsInitialization(ArrayList.class, TerminalActionDefinition.class)
                    .setAnnotations(JsonIgnore.class, XmlTransient.class)
                    .build();

            newFields.add(actionsDecl);
        }

        if (!fieldExist(typeDecl, StringUtil.getVariableName(FOCUS_FIELD_NAME))) {
            JCVariableDecl focusDecl = new FieldDeclBuilder(typeNode, FOCUS_FIELD_NAME)
                    .withModifiers(Flags.PRIVATE)
                    .withType(String.class)
                    .setAnnotations(JsonIgnore.class, XmlTransient.class)
                    .build();

            newFields.add(focusDecl);
        }

        if (!fieldExist(typeDecl, StringUtil.getVariableName(PC_COMMAND_FIELD_NAME))) {
            JCVariableDecl pcCommandDecl = new FieldDeclBuilder(typeNode, PC_COMMAND_FIELD_NAME)
                    .withModifiers(Flags.PRIVATE)
                    .withType(String.class)
                    .setAnnotations(JsonIgnore.class, XmlTransient.class)
                    .build();

            newFields.add(pcCommandDecl);
        }
    }

    /**
     * Creates fields based on declared fields inside the entity class. Possible suffixes: *Field, *Values, *Description, *Actions
     */
    private static void createFieldBasedFields(JavacNode typeNode, java.util.List<JCVariableDecl> newFields, boolean supportTerminalData) {
        JCClassDecl typeDecl = (JCClassDecl) typeNode.get();

        for (JavacNode fieldNode : findAllScreenFields(typeNode)) {

            String nameWithFieldSuffix = fieldNode.getName() + FIELD_SUFFIX;
            boolean isBoolean = JavacHandlerUtil.isBoolean(fieldNode);
            String getterName = JavacOLUtil.toGetterName(nameWithFieldSuffix);

            if (JavacHandlerUtil.MemberExistsResult.NOT_EXISTS.equals(
                    JavacHandlerUtil.methodExists(getterName, fieldNode, isBoolean, 0))
                    && supportTerminalData && isPrimitive(fieldNode) && !fieldExist(typeDecl, nameWithFieldSuffix)) {

                JCVariableDecl terminalField = new FieldDeclBuilder(typeNode, nameWithFieldSuffix)
                        .withModifiers(Flags.PRIVATE)
                        .withType(TerminalField.class)
                        .setAnnotations(JsonIgnore.class, XmlTransient.class)
                        .build();

                newFields.add(terminalField);
            }

            if (JavacHandlerUtil.hasAnnotation(ScreenDescriptionField.class, fieldNode)) {
                if (fieldExist(typeDecl, fieldNode.getName() + DESCRIPTION_SUFFIX))
                    continue;

                JCVariableDecl descriptionDecl = new FieldDeclBuilder(typeNode, fieldNode.getName())
                        .withSuffix(DESCRIPTION_SUFFIX)
                        .withModifiers(Flags.PRIVATE)
                        .withType(String.class)
                        .setAnnotations(JsonIgnore.class, XmlTransient.class)
                        .build();

                newFields.add(descriptionDecl);
            }

        }
    }

    private static void createFieldActions(JavacNode typeNode, java.util.List<JCVariableDecl> newFields) {
        for (JavacNode fieldNode : findAllFields(typeNode)) {
            String varType = ((JCVariableDecl) fieldNode.get()).getType().toString();
            if (varType.contains("List") && !fieldNode.getName().equals(ACTIONS_FIELD_NAME)
                    && !OLJavacHandlerUtil.fieldExist(typeNode, fieldNode.getName() + ACTIONS_SUFFIX)
                    && hasScreenTableClass(typeNode, varType)) {

                JCVariableDecl actionsField = new FieldDeclBuilder(typeNode, fieldNode.getName())
                        .withSuffix(ACTIONS_SUFFIX)
                        .withModifiers(Flags.PRIVATE)
                        .withDiamondsType(java.util.List.class, TerminalActionDefinition.class)
                        .withDiamondsInitialization(ArrayList.class, TerminalActionDefinition.class)
                        .setAnnotations(JsonIgnore.class, XmlTransient.class)
                        .build();

                newFields.add(actionsField);
            }
        }
    }

    private static boolean hasScreenTableClass(JavacNode typeNode, String varType) {
        java.util.List<JavacNode> innerClasses = findAllInnerClasses(typeNode);
        for(JavacNode type : innerClasses){
            String typeName = ((JCClassDecl) type.get()).getSimpleName().toString();
            if(varType.contains(typeName)){
                return true;
            }
        }
        return false;
    }

    private static List<JavacNode> findAllInnerClasses(JavacNode typeNode) {
        java.util.List<JavacNode> fields = new java.util.ArrayList<JavacNode>();
        for (JavacNode child : typeNode.down()) {
            if (child.getKind() == AST.Kind.TYPE) {
                fields.add(child);
            }
        }
        return fields;
    }

    private static java.util.List<JavacNode> findAllFields(JavacNode typeNode) {
        java.util.List<JavacNode> fields = new java.util.ArrayList<JavacNode>();
        for (JavacNode child : typeNode.down()) {
            if (child.getKind() == AST.Kind.FIELD) {
                fields.add(child);
            }
        }
        return fields;
    }
}