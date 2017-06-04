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

import lombok.core.AnnotationValues;
import lombok.core.handlers.HandlerUtil;
import lombok.eclipse.EclipseNode;
import lombok.eclipse.handlers.EclipseHandlerUtil;
import lombok.eclipse.handlers.EclipseHandlerUtil.*;
import lombok.eclipse.handlers.builders.FieldDeclarationBuilder;
import lombok.experimental.Accessors;
import openlegacy.utils.StringUtil;
import org.eclipse.jdt.internal.compiler.ast.FieldDeclaration;
import org.eclipse.jdt.internal.compiler.ast.TypeDeclaration;
import org.eclipse.jdt.internal.compiler.ast.TypeReference;
import org.openlegacy.annotations.screen.ScreenDescriptionField;
import org.openlegacy.terminal.TerminalField;
import org.openlegacy.terminal.TerminalSnapshot;
import org.openlegacy.terminal.definitions.TerminalActionDefinition;

import java.util.ArrayList;
import java.util.List;

import static lombok.eclipse.handlers.EclipseHandlerUtil.*;
import static lombok.eclipse.handlers.openlegacy.EclipseHandlerUtil.*;

/**
 * @author Ivan Bort
 * @since 3.6.0-SNAPSHOT
 */
public class ScreenEntityInterfaceHandler {

    private static final String TERMINAL_SNAPSHOT = "terminalSnapshot";
    private static final String DESCRIPTION_SUFFIX = "Description";
    private static final String FIELD_SUFFIX = "Field";
    private static final String ACTIONS_SUFFIX = "Actions";

    /**
     * Main entry point, which will generate all required code
     */
    public static void handle(EclipseNode typeNode, boolean supportTerminalData) {
        List<FieldDeclaration> newFields = new ArrayList<FieldDeclaration>();
        addImplements(typeNode, org.openlegacy.terminal.ScreenEntity.class);
        createScreenEntityFields(typeNode, newFields, supportTerminalData);
        createFieldBasedFields(typeNode, newFields, supportTerminalData);
        // add new fields into the type declaration
        injectFields(typeNode, newFields);
    }

    /**
     * Creates predefined fields for not super entity class
     */
    private static void createScreenEntityFields(EclipseNode typeNode, List<FieldDeclaration> newFields, boolean supportTerminalData) {
        TypeDeclaration typeDecl = (TypeDeclaration) typeNode.get();
        //create TERMINAL_SNAPSHOT field
        if (supportTerminalData && !fieldExist(typeDecl.fields, TERMINAL_SNAPSHOT)) {
            FieldDeclaration decl = new FieldDeclarationBuilder(TERMINAL_SNAPSHOT)
                    .withModifiers(EclipseModifier.PRIVATE)
                    .withType(TerminalSnapshot.class)
                    .build();
            newFields.add(decl);
        }
        //create focusField field
        if (!fieldExist(typeDecl.fields, StringUtil.getVariableName("focusField"))) {
            FieldDeclaration decl = new FieldDeclarationBuilder("focusField")
                    .withModifiers(EclipseModifier.PRIVATE)
                    .withType(String.class)
                    .build();
            newFields.add(decl);
        }
        // create pcCommand field
        if (!fieldExist(typeDecl.fields, StringUtil.getVariableName("pcCommand"))) {
            FieldDeclaration decl = new FieldDeclarationBuilder("pcCommand")
                    .withModifiers(EclipseModifier.PRIVATE)
                    .withType(String.class)
                    .build();
            newFields.add(decl);
        }
        //create actions field
        if (!fieldExist(typeDecl.fields, StringUtil.getVariableName("actions"))) {
            FieldDeclaration decl = new FieldDeclarationBuilder("actions")
                    .withModifiers(EclipseModifier.PRIVATE)
                    .withType(List.class)
                    .withDiamondsType(TerminalActionDefinition.class)
                    .withInitialization(ArrayList.class)
                    .withDiamondsInitialization(TerminalActionDefinition.class)
                    .build();
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
            String nameWithFieldSuffix = fieldNode.getName() + FIELD_SUFFIX;
            TypeReference fieldType = copyType(field.type, field);
            boolean isBoolean = isBoolean(fieldType);
            String getterName = HandlerUtil.toGetterName(fieldNode.getAst(), AnnotationValues.of(Accessors.class, fieldNode),
                    nameWithFieldSuffix, isBoolean);
            boolean primitive = isPrimitive(fieldType);
            if (MemberExistsResult.NOT_EXISTS.equals(EclipseHandlerUtil.methodExists(getterName, typeNode, -1))
                    && supportTerminalData && primitive && !fieldExist(typeDecl.fields, nameWithFieldSuffix)) {
                FieldDeclaration decl = new FieldDeclarationBuilder(nameWithFieldSuffix)
                        .withModifiers(EclipseModifier.PRIVATE)
                        .withType(TerminalField.class)
                        .build();

                newFields.add(decl);
            }

            //*****
            //try to create "private String *Description"
            if (EclipseHandlerUtil.hasAnnotation(ScreenDescriptionField.class, fieldNode) && !fieldExist(typeDecl.fields, fieldNode.getName() + DESCRIPTION_SUFFIX)) {

                FieldDeclaration decl = new FieldDeclarationBuilder(fieldNode.getName())
                        .withNameSuffix(DESCRIPTION_SUFFIX)
                        .withModifiers(EclipseModifier.PRIVATE)
                        .withType(String.class)
                        .build();

                newFields.add(decl);
            }

            //*****
            //try to create "private List<TerminalActionDefinition> *Actions = new ArrayList<TerminalActionDefinition>()"
            //if field type is List
            char[][] typeChar = fieldType.getTypeName();
            String typeName = new String(typeChar[typeChar.length - 1]);

            if (typeName.equals("List")) {
                if (typeName.contains("actions") || fieldExist(typeDecl.fields, fieldNode.getName() + ACTIONS_SUFFIX))
                    continue;

                FieldDeclaration decl = new FieldDeclarationBuilder(fieldNode.getName())
                        .withNameSuffix(ACTIONS_SUFFIX)
                        .withModifiers(EclipseModifier.PRIVATE)
                        .withType(List.class)
                        .withDiamondsType(TerminalActionDefinition.class)
                        .withInitialization(ArrayList.class)
                        .withDiamondsInitialization(TerminalActionDefinition.class)
                        .build();

                newFields.add(decl);
            }
        }
    }


    /**
     * returns true in the case passed typeReference is a representation of OL primitive types that are not inner or external class types
     */
    private static void injectFields(EclipseNode typeNode, List<FieldDeclaration> fields) {
        for (FieldDeclaration field : fields)
            EclipseHandlerUtil.injectField(typeNode, field);
    }

}
