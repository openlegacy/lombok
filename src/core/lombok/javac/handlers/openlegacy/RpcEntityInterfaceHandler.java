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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.JCExpression;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import com.sun.tools.javac.tree.JCTree.JCClassDecl;
import com.sun.tools.javac.tree.JCTree.JCAnnotation;
import com.sun.tools.javac.tree.JCTree.JCMethodDecl;
import com.sun.tools.javac.util.List;
import lombok.core.AST;
import lombok.core.LombokImmutableList;
import lombok.javac.JavacAST;
import lombok.javac.JavacNode;
import lombok.javac.JavacTreeMaker;
import lombok.javac.handlers.HandleOLData;
import lombok.javac.handlers.JavacHandlerUtil;
import lombok.javac.handlers.JavacOLUtil;
import openlegacy.utils.StringUtil;
import org.openlegacy.definitions.RpcActionDefinition;

import static lombok.javac.handlers.OLJavacHandlerUtil.*;

import java.util.ArrayList;

/**
 * @author Matvey Mitnitsky
 * @since 3.6.0-SNAPSHOT
 */
public class RpcEntityInterfaceHandler {

    public static void handle(JavacNode typeNode, JavacNode annotationNode) {
        JCClassDecl typeDecl = checkAnnotation(typeNode, annotationNode);
        if (typeDecl == null) {
            return;
        }

        createNonSuperEntityFields(typeNode);
    }

    private static void createNonSuperEntityFields(JavacNode typeNode) {
        JavacTreeMaker jcMaker = typeNode.getTreeMaker();

        if (!fieldExist(typeNode, StringUtil.getVariableName("actions"))) {
            JCExpression listTypeRef = jcMaker.TypeApply(
                    JavacOLUtil.getJCExpresssionForType(typeNode, java.util.List.class),
                    List.<JCExpression>nil().append(JavacOLUtil.getJCExpresssionForType(typeNode, RpcActionDefinition.class)));

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

            JavacHandlerUtil.injectField(typeNode, actionsDecl);
        }
    }

    public static void createJacksonAnnotations(JavacNode typeNode){
        List<JCMethodDecl> methods = List.<JCMethodDecl>nil();
        JavacTreeMaker treeMaker = typeNode.getTreeMaker();

        for (JavacNode child : typeNode.down()) {
            if (child.getKind() != AST.Kind.METHOD || child.getName().contains("init")) {
            	continue;
            }

            JCMethodDecl methodDecl = (JCMethodDecl) child.get();
            List<JCAnnotation> annotationList = methodDecl.mods.annotations;
            JCAnnotation ann = treeMaker.Annotation(
                    JavacOLUtil.getJCExpresssionForType(typeNode, JsonProperty.class),
                    List.<JCExpression>nil());
            annotationList = annotationList.append(ann);
            methodDecl.mods.annotations = annotationList;

        }

    }

}