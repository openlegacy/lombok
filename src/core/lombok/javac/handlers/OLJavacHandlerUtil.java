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
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.JCClassDecl;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import com.sun.tools.javac.tree.JCTree.JCExpression ;
import com.sun.tools.javac.util.List;
import lombok.javac.Javac;
import lombok.javac.JavacNode;
import lombok.javac.JavacTreeMaker;

import java.lang.reflect.Modifier;

/**
 * @author Matvey Mitnitsky
 * @since 3.6.0-SNAPSHOT
 */
public class OLJavacHandlerUtil {

    public static JCClassDecl checkAnnotation(JavacNode typeNode, JavacNode annotationNode) {
        JCClassDecl typeDecl = null;
        if (typeNode.get() instanceof JCClassDecl) {
            typeDecl = (JCClassDecl) typeNode.get();
        }

        long modifiers = typeDecl == null ? 0 : typeDecl.mods.flags;
        boolean notAClass = (modifiers & (Flags.INTERFACE | Flags.ANNOTATION | Flags.ENUM)) != 0;

        if (typeDecl == null | notAClass) {
            annotationNode.addError("@Implements is only supported on a class.");
            return null;
        }

        return typeDecl;
    }

    public static JCExpression createVariableDeclarationForGenericClass(JavacNode typeNode, Class<?> clazz, java.util.List<Class<?>> typeArgs) {
        List<JCExpression> jcTypeArgs = List.<JCExpression>nil();
        if(typeArgs != null) {
            jcTypeArgs = toTypeArgs(typeNode, typeArgs);
        }

        JavacTreeMaker jcMaker = typeNode.getTreeMaker();
        JCExpression variableDecl = jcMaker.TypeApply(JavacOLUtil.getJCExpresssionForType(typeNode, clazz), jcTypeArgs);

        return variableDecl;
    }

    public static JCVariableDecl createSimpleVariableDeclaration(JavacNode typeNode, Class<?> clazz, String name) {
        JavacTreeMaker jcMker = typeNode.getTreeMaker();
        return jcMker.VarDef(
                jcMker.Modifiers(Flags.PRIVATE),
                typeNode.toName(name),
                JavacOLUtil.getJCExpresssionForType(typeNode, clazz),
                null
        );
    }

    public static JCExpression createSimpleVariableInitialization(JavacNode typeNode, Class<?> clazz, java.util.List<Class<?>> typeargs){
        List<JCExpression> jcTypeArgs = List.<JCExpression>nil();
        if(typeargs != null) {
            jcTypeArgs = toTypeArgs(typeNode, typeargs);
        }

        return createVariableInitialization(typeNode, null, jcTypeArgs, JavacOLUtil.getJCExpresssionForType(typeNode, clazz), List.<JCExpression>nil(), null);
    }

    public static JCExpression createVariableInitialization(JavacNode typeNode, JCExpression encl, List<JCExpression> typeargs, JCExpression clazz, List<JCExpression> args, JCClassDecl def){
        JavacTreeMaker jcMaker = typeNode.getTreeMaker();
        JCExpression varInit = jcMaker.NewClass(encl, typeargs, clazz, args, def);

        return varInit;
    }

    public static JCVariableDecl createVariableDefinition(JavacNode typeNode, String fieldName, JCExpression fieldDecl, JCExpression fieldInit){
        JavacTreeMaker jcMaker = typeNode.getTreeMaker();
        //TODO add modifiers method argument
        JCVariableDecl variableDef = jcMaker.VarDef(
                jcMaker.Modifiers(Flags.PRIVATE),
                typeNode.toName(fieldName),
                fieldDecl,
                fieldInit);

        return variableDef;

    }

    private static List<JCExpression> toTypeArgs(JavacNode typeNode, java.util.List<Class<?>> typeargs) {
        List<JCExpression> jcTypeArgs =  List.<JCExpression>nil();
        if(typeargs != null && !typeargs.isEmpty()) {
            for(Class<?> type : typeargs) {
                JCExpression typeExpression = JavacOLUtil.getJCExpresssionForType(typeNode, type);
                jcTypeArgs = jcTypeArgs.append(typeExpression);
            }

            return jcTypeArgs;
        }

        return null;
    }

    public static boolean fieldExist(JavacNode typeNode, String fieldName) {
        JCClassDecl typeDecl = (JCClassDecl) typeNode.get();
        if (typeDecl == null || fieldName == null || fieldName.trim().isEmpty())
            return false;
        List<JCTree> defs = typeDecl.getMembers();
        for (JCTree def : defs)
            if (JCVariableDecl.class.isInstance(def)
                    && (((JCVariableDecl) def).getName().toString()).equals(fieldName)) {
                return true;
            }
        return false;
    }
    
    public static <A> List<A> getJavacListFromJavaUtilList(java.util.List<A> input){
    	List<A> output = List.<A>nil();
    	if(input == null || input.isEmpty())
    		return output;
    	for(A a : input)
    		output = output.append(a);
    	
    	return output;
    }
}
