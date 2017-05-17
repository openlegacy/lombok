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

import com.sun.source.tree.Tree;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.JCExpression ;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import com.sun.tools.javac.tree.JCTree.JCPrimitiveTypeTree;
import lombok.javac.JavacNode;

/**
 * <h1>JavacOLUtil</h1>
 * <p>JavacOLUtil provides API that mostly incapsulates JavacHandlerUtil API.</p>
 * <p>JCTree is a root class for abstract syntax tree nodes.
 * It provides definitions for specific tree nodes as subclasses nested inside.
 * JCTree is a superclass for many Java compiler nodes representations
 * e.g. {@link JCExpression}, JCImport, JCMethodDecl, JCStatement</p>
 * <p>JCExpression is a central abstraction class it is widely used for AST modifications
 * JCExpression has the following important subclasses:
 * JCCLassDecl, JCVariableDecl, NewClass, TypeApply, etc.</p>
 * <p> JavacTreeMaker is utility for building JCTree objects.
 * For example:
 * {@code JCVariableDecl VarDef(...)},
 * {@code JCTypeApply TypeApply(JCExpression clazz, List<JCExpression> typeArgs) }</p>
 *
 * @author Matvey Mitnitsky
 * @since 3.6.0-SNAPSHOT
 */


/*
 *TODO create methods that incapsulate JavacTreeMaker API such as maker.VarDef(...), maker.TypeApply(...) or maker.NewClass(...)
 */
public class JavacOLUtil {

    public static final String JAVA_UTIL_PACKAGE = "java.util";

    public static JCExpression getJCExpresssionForType(JavacNode typeNode, Class<?> clazz){
        if(typeNode == null)
            return null;
        return clazz != null ? JavacHandlerUtil.chainDotsString(typeNode, clazz.getName()) : null;
    }

    public static JCExpression getJCExpresssionForType(JavacNode typeNode, String fullyQualifiedName){
        if(typeNode == null)
            return null;
        return (fullyQualifiedName != null && !fullyQualifiedName.isEmpty()) ? JavacHandlerUtil.chainDotsString(typeNode, fullyQualifiedName) : null;
    }

    public static JCExpression getJCExpresssionForJavaLangType(JavacNode typeNode, String simpleName){
        if(typeNode == null)
            return null;
        return (simpleName != null && !simpleName.trim().isEmpty()) ?
                JavacHandlerUtil.genJavaLangTypeRef(typeNode, simpleName) : null;
    }

    public static JCExpression getJCExpresssionForJavaUtilType(JavacNode typeNode, String simpleName){
        if(simpleName == null || simpleName.trim().isEmpty())
            return null;
        String fqName = JAVA_UTIL_PACKAGE + simpleName;
        return JavacHandlerUtil.genTypeRef(typeNode, fqName);
    }

    public static String getFullyQualifiedName(Class<?> clazz){
        return clazz != null ? clazz.getName() : null;
    }

    public static void injectFields(JavacNode typeNode, java.util.List<JCVariableDecl> varsList) {
        if (typeNode == null || varsList == null || varsList.isEmpty())
            return;
        for (JCVariableDecl vd : varsList)
            JavacHandlerUtil.injectFieldAndMarkGenerated(typeNode, vd);
    }

    public static boolean isPrimitive(JavacNode fieldNode) {
        return ((JCVariableDecl) fieldNode.get()).vartype.getKind() == Tree.Kind.PRIMITIVE_TYPE;
    }

    public static String toGetterName(String nameWithFieldSuffix) {
        char [] charFieldName = nameWithFieldSuffix.toCharArray();
        charFieldName[0] = Character.toUpperCase(charFieldName[0]);
        String fieldGetterSufix = charFieldName.toString();
        return "get"+fieldGetterSufix;
    }
}
