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

import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import lombok.core.AST;
import lombok.javac.JavacNode;
import lombok.javac.handlers.builders.FieldDeclBuilder;
import openlegacy.utils.StringUtil;
import org.openlegacy.annotations.rpc.RpcField;
import org.openlegacy.definitions.RpcActionDefinition;
import org.openlegacy.rpc.RpcEntity;

import java.util.ArrayList;
import java.util.List;

import static lombok.javac.handlers.JavacHandlerUtil.*;
import static lombok.javac.handlers.OLJavacHandlerUtil.*;

/**
 * @author Matvey Mitnitsky
 * @since 3.6.0-SNAPSHOT
 */
public class RpcEntityInterfaceHandler {

    public static void handle(JavacNode typeNode, boolean rpcPart) {
        if (!rpcPart) addImplements(typeNode, RpcEntity.class);
        createNonSuperEntityFields(typeNode);
        createFieldActions(typeNode);
    }

    private static void createNonSuperEntityFields(JavacNode typeNode) {

        if (!fieldExist(typeNode, StringUtil.getVariableName("actions"))) {
            JCVariableDecl actionsDeclaration = new FieldDeclBuilder(typeNode, "actions")
                    .withModifiers(Flags.PRIVATE)
                    .withDiamondsType(java.util.List.class, RpcActionDefinition.class)
                    .withDiamondsInitialization(
                            java.util.ArrayList.class, RpcActionDefinition.class
                    )
                    .build();

            injectField(typeNode, actionsDeclaration);

        }
    }

    private static void createFieldActions(JavacNode typeNode) {
        for (JavacNode fieldNode : findAllRpcFields(typeNode)) {
            String fieldNameWithActionsSuffix = fieldNode.getName() + "Actions";
            String varType = ((JCVariableDecl) fieldNode.get()).getType().toString();
            if (varType.contains("List<")) {
                if (fieldNode.getName().contains("actions") || fieldExist(typeNode, fieldNameWithActionsSuffix))
                    continue;
                JCVariableDecl fieldDeclaration = new FieldDeclBuilder(typeNode, fieldNameWithActionsSuffix)
                        .withModifiers(Flags.PRIVATE)
                        .withDiamondsType(java.util.List.class, RpcActionDefinition.class)
                        .withDiamondsInitialization(ArrayList.class, RpcActionDefinition.class)
                        .build();

                injectField(typeNode, fieldDeclaration);
            }
        }

    }

    private static List<JavacNode> findAllRpcFields(JavacNode typeNode) {
        java.util.List<JavacNode> fields = new java.util.ArrayList<JavacNode>();
        for (JavacNode child : typeNode.down()) {
            if (child.getKind() == AST.Kind.FIELD && hasAnnotation(RpcField.class, child))
                fields.add(child);
        }
        return fields;
    }
}