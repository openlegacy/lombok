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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.JCClassDecl;
import com.sun.tools.javac.tree.JCTree.JCExpression;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import com.sun.tools.javac.util.List;
import lombok.AccessLevel;
import lombok.core.AST;
import lombok.javac.JavacNode;
import lombok.javac.JavacTreeMaker;
import org.openlegacy.annotations.screen.ScreenField;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

import static lombok.javac.handlers.JavacHandlerUtil.*;

/**
 * @author Matvey Mitnitsky
 * @since 3.6.0-SNAPSHOT
 */
public class OLJavacHandlerUtil {

    /**
     * Generate getters and setters for typeNode
     */
    static void generateGettersAndSetters(JavacNode typeNode, JavacNode annotationNode) {
        new HandleGetter().generateGetterForType(typeNode, annotationNode, AccessLevel.PUBLIC, true);
        new HandleSetter().generateSetterForType(typeNode, annotationNode, AccessLevel.PUBLIC, true);
    }

    /**
     * Generate Lombok Data for typeNode (Constructor, Getter, Setter, equals And hashCode, toString
     */
    public static void generateLombokData(JavacNode typeNode, JavacNode annotationNode) {
        new HandleConstructor().generateRequiredArgsConstructor(typeNode, AccessLevel.PUBLIC, "", HandleConstructor.SkipIfConstructorExists.YES, annotationNode);
        new HandleGetter().generateGetterForType(typeNode, annotationNode, AccessLevel.PUBLIC, true);
        new HandleSetter().generateSetterForType(typeNode, annotationNode, AccessLevel.PUBLIC, true);
        new HandleEqualsAndHashCode().generateEqualsAndHashCodeForType(typeNode, annotationNode);
        new HandleToString().generateToStringForType(typeNode, annotationNode);
    }

    /**
     * @deprecated in favor of {@link #validateAnnotation(JavacNode, JavacNode)}
     * <p>
     * Method checks if annotation is set to appropriate element
     * adds an error if it set to Annotation, Enum or Interface
     */
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

    /**
     * Method checks if annotation is set to appropriate element
     * adds an error if it set to Annotation, Enum or Interface
     */
    public static boolean validateAnnotation(JavacNode typeNode, JavacNode annotationNode) {
        JCClassDecl typeDecl = null;
        if (typeNode.get() instanceof JCClassDecl) {
            typeDecl = (JCClassDecl) typeNode.get();
        }

        long modifiers = typeDecl == null ? 0 : typeDecl.mods.flags;
        boolean notAClass = (modifiers & (Flags.INTERFACE | Flags.ANNOTATION | Flags.ENUM)) != 0;

        if (typeDecl == null | notAClass) {
            annotationNode.addError("@Implements is only supported on a class.");
            return false;
        }

        return true;
    }

    /**
     * Implements interfaces of passed types to the typeNode.
     *
     * @param typeNode   {@link JavacNode}
     * @param interfaces {@link Class} varargs
     */
    public static void addImplements(JavacNode typeNode, Class<?>... interfaces) {
        if (interfaces == null || interfaces.length == 0)
            return;

        for (Class iFace : interfaces)
            addImplements(typeNode, iFace.getName());
    }

    /**
     * Implements interface with passed name to the typeNode.
     *
     * @param typeNode      {@link JavacNode}
     * @param interfaceName String name
     */
    public static void addImplements(JavacNode typeNode, String interfaceName) {
        JCClassDecl classDecl = (JCClassDecl) typeNode.get();

        java.util.List<JCExpression> resultingList = new ArrayList<JCExpression>();
        // represents a list of full qualified declared interfaces
        java.util.List<String> existingFqInterfacesNames = new ArrayList<String>();
        // represents a list of simple declared interfaces
        java.util.List<String> existingInterfacesNames = new ArrayList<String>();

        if (classDecl.implementing != null) {
            for (JCExpression expression : classDecl.implementing) {
                resultingList.add(expression);
                String ifName = null;
                if (expression instanceof JCTree.JCFieldAccess) {
                    ifName = ((JCTree.JCFieldAccess) expression).name.toString();
                }
                if (expression instanceof JCTree.JCIdent)
                    ifName = ((JCTree.JCIdent) expression).name.toString();
                if (ifName == null)
                    continue;
                String[] split = ifName.split("\\.");
                if (split.length > 1) {
                    // add to fq list
                    existingFqInterfacesNames.add(ifName);
                } else {
                    existingInterfacesNames.add(ifName);
                }
            }
        }

        String simpleName = interfaceName;
        boolean qualified = simpleName.contains(".");
        if (qualified) {
            String[] split = simpleName.split("\\.");
            simpleName = split[split.length - 1];
        }

        if (!existingFqInterfacesNames.contains(interfaceName) && !existingInterfacesNames.contains(simpleName)) {
            //TODO: add logic when interface name is not qualified and comes from imports list
            JCExpression ifExpression = chainDotsString(typeNode, interfaceName);
            resultingList.add(ifExpression);
        }
        classDecl.implementing = OLJavacHandlerUtil.<JCExpression>utilListToJavacList(resultingList);
    }

    /**
     * Checks if field exists within fields of given typeNode
     */
    public static boolean fieldExist(JavacNode typeNode, String fieldName) {
        return fieldExist((JCClassDecl) typeNode.get(), fieldName);
    }

    public static boolean fieldExist(JCClassDecl typeDecl, String fieldName) {
        if (typeDecl == null || fieldName == null || fieldName.trim().isEmpty())
            return false;

        List<JCTree> defs = typeDecl.getMembers();
        for (JCTree def : defs)
            if (JCVariableDecl.class.isInstance(def) && (((JCVariableDecl) def).getName().toString()).equals(fieldName)) {
                return true;
            }

        return false;
    }

    /**
     * The method checks if inner class with {@param className String} exists in the
     * enclosing class {@param typeNode EclipseNode}
     *
     * @return boolean result
     */
    public static boolean classExists(JavacNode typeNode, String className) {
        for (JavacNode node : typeNode.down())
            if (node.getKind() == AST.Kind.TYPE && className.equals(node.getName()))
                return true;
        return false;
    }

    /**
     * Transform java.util.List of generic type into com.sun.tools.javac.util.List
     */
    public static <A> List<A> utilListToJavacList(java.util.List<A> input) {
        List<A> output = List.<A>nil();
        if (input == null || input.isEmpty())
            return output;
        for (A a : input)
            output = output.append(a);

        return output;
    }

    /**
     * @deprecated this method is used for prototype version of OLData
     */
    public static JCExpression createVariableDeclarationForGenericClass(JavacNode typeNode, Class<?> clazz, java.util.List<Class<?>> typeArgs) {
        List<JCExpression> jcTypeArgs = List.<JCExpression>nil();
        if (typeArgs != null) {
            jcTypeArgs = toTypeArgs(typeNode, typeArgs);
        }

        JavacTreeMaker jcMaker = typeNode.getTreeMaker();
        JCExpression variableDecl = jcMaker.TypeApply(JavacOLUtil.getJCExpressionForType(typeNode, clazz), jcTypeArgs);

        return variableDecl;
    }

    /**
     * @deprecated this method is used for prototype version of OLData
     */
    public static JCVariableDecl createSimpleVariableDeclaration(JavacNode typeNode, Class<?> clazz, String name) {
        JavacTreeMaker jcMker = typeNode.getTreeMaker();
        return jcMker.VarDef(
                jcMker.Modifiers(Flags.PRIVATE),
                typeNode.toName(name),
                JavacOLUtil.getJCExpressionForType(typeNode, clazz),
                null
        );
    }

    /**
     * @deprecated this method is used for prototype version of OLData
     */
    public static JCVariableDecl createVariableDefinition(JavacNode typeNode, String fieldName, JCExpression fieldDecl, JCExpression fieldInit) {
        JavacTreeMaker jcMaker = typeNode.getTreeMaker();
        JCVariableDecl variableDef = jcMaker.VarDef(
                jcMaker.Modifiers(Flags.PRIVATE),
                typeNode.toName(fieldName),
                fieldDecl,
                fieldInit);

        return variableDef;

    }

    /**
     * @deprecated this method is used for prototype version of OLData
     */
    public static JCExpression createSimpleVariableInitialization(JavacNode typeNode, Class<?> clazz, java.util.List<Class<?>> typeargs) {
        List<JCExpression> jcTypeArgs = List.<JCExpression>nil();
        if (typeargs != null) {
            jcTypeArgs = toTypeArgs(typeNode, typeargs);
        }

        return createVariableInitialization(typeNode, null, jcTypeArgs, JavacOLUtil.getJCExpressionForType(typeNode, clazz), List.<JCExpression>nil(), null);
    }


    /**
     * @deprecated this method is used for prototype version of OLData
     */
    public static void createJacksonAnnotations(JavacNode typeNode) {
        List<JCTree.JCMethodDecl> methods = List.<JCTree.JCMethodDecl>nil();
        JavacTreeMaker treeMaker = typeNode.getTreeMaker();

        for (JavacNode child : typeNode.down()) {
            if (child.getKind() != AST.Kind.METHOD || child.getName().contains("init")) {
                continue;
            }

            JCTree.JCMethodDecl methodDecl = (JCTree.JCMethodDecl) child.get();
            List<JCTree.JCAnnotation> annotationList = methodDecl.mods.annotations;
            JCTree.JCAnnotation ann = treeMaker.Annotation(
                    JavacOLUtil.getJCExpressionForType(typeNode, JsonProperty.class),
                    List.<JCExpression>nil());
            annotationList = annotationList.append(ann);
            methodDecl.mods.annotations = annotationList;
        }

    }

    private static JCExpression createVariableInitialization(JavacNode typeNode, JCExpression encl, List<JCExpression> typeargs, JCExpression clazz, List<JCExpression> args, JCClassDecl def) {
        JavacTreeMaker jcMaker = typeNode.getTreeMaker();
        JCExpression varInit = jcMaker.NewClass(encl, typeargs, clazz, args, def);

        return varInit;
    }

    private static List<JCExpression> toTypeArgs(JavacNode typeNode, java.util.List<Class<?>> typeargs) {
        List<JCExpression> jcTypeArgs = List.<JCExpression>nil();
        if (typeargs != null && !typeargs.isEmpty()) {
            for (Class<?> type : typeargs) {
                JCExpression typeExpression = JavacOLUtil.getJCExpressionForType(typeNode, type);
                jcTypeArgs = jcTypeArgs.append(typeExpression);
            }

            return jcTypeArgs;
        }

        return null;
    }

    /**
     * method returns List of all fields annotated with {@link ScreenField} annotation
     */
    public static java.util.List<JavacNode> findAllScreenFields(JavacNode typeNode) {
        java.util.List<JavacNode> fields = new java.util.ArrayList<JavacNode>();
        for (JavacNode child : typeNode.down()) {
            if (child.getKind() == AST.Kind.FIELD && JavacHandlerUtil.hasAnnotation(ScreenField.class, child))
                fields.add(child);
        }
        return fields;
    }

    /**
     * returns true in the case passed typeReference is a representation of OL primitive types that are not inner or external class types
     */
    public static boolean isPrimitive(JavacNode fieldNode) {
        JCVariableDecl variableDecl = (JCVariableDecl) fieldNode.get();
        String variableType = variableDecl.getType().toString();

        if (variableType.equals("int"))
            return true;

        if (variableType.equals(String.class.getSimpleName()) || variableType.equals(String.class.getName()))
            return true;

        if (variableType.equals(BigInteger.class.getSimpleName()) || variableType.equals(BigInteger.class.getName()))
            return true;

        if (variableType.equals(Integer.class.getSimpleName()) || variableType.equals(Integer.class.getName()))
            return true;

        if (variableType.equals(Boolean.class.getSimpleName()) || variableType.equals(Boolean.class.getName())
                || variableType.equals("boolean"))
            return true;

        if (variableType.equals(Date.class.getSimpleName()) || variableType.equals(Date.class.getName()))
            return true;

        if (variableType.equals(Double.class.getSimpleName()) || variableType.equals(Double.class.getName()))
            return true;

        if (variableType.equals(BigDecimal.class.getSimpleName()) || variableType.equals(BigDecimal.class.getName()))
            return true;

        return false;
    }
}
