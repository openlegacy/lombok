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
import lombok.eclipse.handlers.HandleImplements;
import openlegacy.utils.EclipseAstUtil;
import openlegacy.utils.EclipseImportsUtil;
import openlegacy.utils.StringUtil;
import org.eclipse.jdt.internal.compiler.ast.*;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFileConstants;
import org.openlegacy.definitions.RpcActionDefinition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Matvey Mitnitsky
 * @since 3.6.0-SNAPSHOT
 */
public class RpcEntityInterfaceHandler {

    private String rpcActionDefinition;

    public void handle(EclipseNode typeNode, EclipseNode source) {
        TypeDeclaration typeDecl = HandleImplements.checkAnnotation(typeNode, source);

        if (typeDecl == null) {
            return;
        }

        rpcActionDefinition = EclipseImportsUtil.getTypeName(typeNode.getAst(), RpcActionDefinition.class);

        createNonSuperEntityFields(typeNode);
    }

    private void createNonSuperEntityFields(EclipseNode typeNode) {
        TypeDeclaration typeDecl = (TypeDeclaration) typeNode.get();

        if (!fieldExist(typeDecl.fields, StringUtil.getVariableName("actions"))) {
            FieldDeclaration decl = new FieldDeclaration("actions".toCharArray(), 0, 0);
            decl.modifiers = decl.modifiers | ClassFileConstants.AccPrivate;
            TypeReference typeArg = EclipseAstUtil.createTypeReference(rpcActionDefinition);
            //return type
            String list = EclipseImportsUtil.getTypeName(typeNode.getAst(), List.class);
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
