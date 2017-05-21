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

import lombok.eclipse.EclipseNode;
import lombok.eclipse.handlers.EclipseHandlerUtil;
import lombok.eclipse.handlers.builders.FieldDeclBuilderImpl;
import openlegacy.utils.StringUtil;
import org.eclipse.jdt.internal.compiler.ast.FieldDeclaration;
import org.eclipse.jdt.internal.compiler.ast.TypeDeclaration;
import org.openlegacy.definitions.RpcActionDefinition;

import java.util.ArrayList;
import java.util.List;

import static lombok.eclipse.handlers.openlegacy.EclipseHandlerUtil.*;

/**
 * @author Matvey Mitnitsky
 * @since 3.6.0-SNAPSHOT
 */
public class RpcEntityInterfaceHandler {


    public static void handle(EclipseNode typeNode) {

        createNonSuperEntityFields(typeNode);
    }

    private static void createNonSuperEntityFields(EclipseNode typeNode) {
        TypeDeclaration typeDecl = (TypeDeclaration) typeNode.get();

        if (!fieldExist(typeDecl.fields, StringUtil.getVariableName("actions"))) {
            FieldDeclaration decl = new FieldDeclBuilderImpl("actions")
                    .withModifiers(EclipseModifier.PRIVATE)
                    .withType(List.class)
                    .withDiamondsType(RpcActionDefinition.class)
                    .withInitialization(ArrayList.class)
                    .build();
//            FieldDeclaration decl = new FieldDeclaration("actions".toCharArray(), 0, 0);
//            decl.modifiers = decl.modifiers | ClassFileConstants.AccPrivate;
//            TypeReference typeArg = EclipseAstUtil.createTypeReference(rpcActionDefinition);
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
            EclipseHandlerUtil.injectField(typeNode, decl);
        }
    }
}
