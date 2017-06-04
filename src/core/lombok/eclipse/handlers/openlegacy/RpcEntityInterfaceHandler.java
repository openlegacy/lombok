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

import lombok.core.AST;
import lombok.eclipse.EclipseNode;
import lombok.eclipse.handlers.builders.FieldDeclarationBuilder;
import openlegacy.utils.StringUtil;
import org.eclipse.jdt.internal.compiler.ast.FieldDeclaration;
import org.eclipse.jdt.internal.compiler.ast.TypeDeclaration;
import org.eclipse.jdt.internal.compiler.ast.TypeReference;
import org.openlegacy.annotations.rpc.RpcField;
import org.openlegacy.definitions.RpcActionDefinition;
import org.openlegacy.rpc.RpcEntity;

import java.util.ArrayList;
import java.util.List;

import static lombok.eclipse.handlers.EclipseHandlerUtil.*;
import static lombok.eclipse.handlers.openlegacy.EclipseHandlerUtil.*;

/**
 * @author Matvey Mitnitsky
 * @since 3.6.0-SNAPSHOT
 */
public class RpcEntityInterfaceHandler {


    public static void handle(EclipseNode typeNode, boolean rpcPart) {

        if (!rpcPart) addImplements(typeNode, RpcEntity.class);
        createNonSuperEntityFields(typeNode);
        createFieldActions(typeNode);
    }

    private static void createNonSuperEntityFields(EclipseNode typeNode) {
        TypeDeclaration typeDecl = (TypeDeclaration) typeNode.get();

        if (!fieldExist(typeDecl.fields, StringUtil.getVariableName("actions"))) {
            FieldDeclaration decl = new FieldDeclarationBuilder("actions")
                    .withModifiers(EclipseModifier.PRIVATE)
                    .withType(List.class)
                    .withDiamondsType(RpcActionDefinition.class)
                    .withInitialization(ArrayList.class)
                    .withDiamondsInitialization(RpcActionDefinition.class)
                    .build();

            injectField(typeNode, decl);
        }
    }

    private static void createFieldActions(EclipseNode typeNode) {
        TypeDeclaration typeDeclaration = (TypeDeclaration) typeNode.get();

        for (EclipseNode fieldNode : findAllRpcFields(typeNode)) {
            String fieldNameWithActionsSuffix = fieldNode.getName() + "Actions";
            FieldDeclaration field = (FieldDeclaration) fieldNode.get();

            TypeReference fieldType = copyType(field.type, field);
            char[][] typeChar = fieldType.getTypeName();
            String typeName = new String(typeChar[typeChar.length - 1]);

            if (typeName.contains("List")) {
                if (fieldNode.getName().contains("actions") || fieldExist(typeDeclaration.fields, fieldNameWithActionsSuffix))
                    continue;

                FieldDeclaration fieldDeclaration = new FieldDeclarationBuilder(fieldNameWithActionsSuffix)
                        .withModifiers(EclipseModifier.PRIVATE)
                        .withType(List.class)
                        .withDiamondsType(RpcActionDefinition.class)
                        .withInitialization(ArrayList.class)
                        .withDiamondsInitialization(RpcActionDefinition.class)
                        .build();

                injectField(typeNode, fieldDeclaration);
            }
        }
    }

    private static List<EclipseNode> findAllRpcFields(EclipseNode typeNode) {
        List<EclipseNode> fields = new ArrayList<EclipseNode>();
        for (EclipseNode child : typeNode.down()) {
            if (child.getKind() == AST.Kind.FIELD && hasAnnotation(RpcField.class, child))
                fields.add(child);
        }

        return fields;
    }
}
