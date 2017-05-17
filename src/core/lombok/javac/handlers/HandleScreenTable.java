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
package lombok.javac.handlers;

import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.tree.JCTree.JCAnnotation;
import com.sun.tools.javac.tree.JCTree.JCClassDecl;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import lombok.AccessLevel;
import lombok.core.AnnotationValues;
import lombok.javac.JavacAnnotationHandler;
import lombok.javac.JavacNode;
import lombok.javac.JavacTreeMaker;
import openlegacy.utils.StringUtil;

import static lombok.javac.handlers.OLJavacHandlerUtil.*;

import org.mangosdk.spi.ProviderFor;
import org.openlegacy.annotations.screen.ScreenTable;

/**
 * @author Matvey Mitnitsky
 * @since 3.6.0-SNAPSHOT
 */

@ProviderFor(JavacAnnotationHandler.class)
public class HandleScreenTable extends JavacAnnotationHandler<ScreenTable> {

    @Override
    public void handle(AnnotationValues<ScreenTable> annotationValues, JCAnnotation ast, JavacNode annotationNode) {
        JavacNode typeNode = annotationNode.up();
        JCClassDecl typeDecl = checkAnnotation(typeNode, annotationNode);
        if (typeDecl == null) {
            return;
        }

        ScreenTable instance = annotationValues.getInstance();

        handleScreenTable(typeNode);

        new HandleGetter().generateGetterForType(typeNode, annotationNode, AccessLevel.PUBLIC, true);
        new HandleSetter().generateSetterForType(typeNode, annotationNode, AccessLevel.PUBLIC, true);
    }

    private void handleScreenTable(JavacNode typeNode) {
        JCClassDecl typeDecl = (JCClassDecl) typeNode.get();
        JavacTreeMaker treeMaker = typeNode.getTreeMaker();
        if (!fieldExist(typeNode, StringUtil.getVariableName("focusField"))) {
            JCVariableDecl focusDecl = treeMaker.VarDef(
                    treeMaker.Modifiers(Flags.PRIVATE),
                    typeNode.toName("focusField"),
                    JavacOLUtil.getJCExpresssionForJavaLangType(typeNode, "String"),
                    null);

            JavacHandlerUtil.injectField(typeNode, focusDecl);
        }

    }

}
