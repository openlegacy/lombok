///*******************************************************************************
// * Copyright (c) 2017 OpenLegacy Inc.
// * All rights reserved. This program and the accompanying materials
// * are made available under the terms of the Eclipse Public License v1.0
// * which accompanies this distribution, and is available at
// * http://www.eclipse.org/legal/epl-v10.html
// *
// * Contributors:
// *     OpenLegacy Inc. - initial API and implementation
// *
// * Copyright (C) 2009-2016 The Project Lombok Authors.
// *
// * Permission is hereby granted, free of charge, to any person obtaining a copy
// * of this software and associated documentation files (the "Software"), to deal
// * in the Software without restriction, including without limitation the rights
// * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// * copies of the Software, and to permit persons to whom the Software is
// * furnished to do so, subject to the following conditions:
// *
// * The above copyright notice and this permission notice shall be included in
// * all copies or substantial portions of the Software.
// *
// * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// * THE SOFTWARE.
//
// *******************************************************************************/
//
//package lombok.javac.handlers;
//
//
//import com.sun.source.tree.Tree;
//import com.sun.source.tree.TreeVisitor;
//import com.sun.tools.javac.code.Flags;
//import com.sun.tools.javac.tree.JCTree;
//import com.sun.tools.javac.tree.JCTree.JCClassDecl;
//import com.sun.tools.javac.tree.JCTree.JCAnnotation;
//import com.sun.tools.javac.tree.JCTree.JCExpression;
//import com.sun.tools.javac.tree.JCTree.JCTypeCast;
//import com.sun.tools.javac.util.List;
//import lombok.AccessLevel;
//import lombok.Implements;
//import lombok.core.AnnotationValues;
//import lombok.javac.*;
//import openlegacy.ScreenEntityInterfaceHandler;
//import org.mangosdk.spi.ProviderFor;
//import org.openlegacy.annotations.screen.ScreenEntity;
//import org.openlegacy.annotations.screen.ScreenEntitySuperClass;
//
//
///**
// * Handles the {@code lombok.Implements} annotation for java compiler.
// *
// * @author Matvey Mitnitsky
// * @since 3.6.0-SNAPSHOT
// */
//@ProviderFor(JavacAnnotationHandler.class)
//public class HandleImplements extends JavacAnnotationHandler<Implements> {
//
//    @Override
//    public void handle(AnnotationValues<Implements> annotationValues, JCAnnotation ast, JavacNode annotationNode) {
//        JavacNode typeNode = annotationNode.up();
//
//        JCClassDecl typeDecl = checkAnnotation(typeNode, annotationNode);
//
//        if (typeDecl == null) {
//            return;
//        }
//
//        Implements instance = annotationValues.getInstance();
//        String entityType = instance.value();
//
//
//        switch (entityType)
//        ScreenEntity screenEntity = typeNode.getElement().getAnnotation(ScreenEntity.class);
//
////        generateImplementsForType(typeNode, annotationNode, probableFQType);
////
////        Implements instance = annotationValues.getInstance();
////        if (ScreenEntity.class.getName().equals(probableFQType)) {
////            //TODO create handler for JavaCompiler
////            new ScreenEntityInterfaceHandler().handle(typeNode, annotationNode);
////        }
////
////        if (instance.getters()) {
////            new HandleGetter().generateGetterForType(typeNode, annotationNode, AccessLevel.PUBLIC, true);
////        }
////        if (instance.setters()) {
////            new HandleSetter().generateSetterForType(typeNode, annotationNode, AccessLevel.PUBLIC, true);
////        }
//
//    }
//
//    private boolean generateImplementsForType(JavacNode typeNode, JavacNode annotationNode, String probableFQType) {
//        JCClassDecl typeDecl = checkAnnotation(typeNode, annotationNode);
//
//        if (typeDecl == null || probableFQType == null || probableFQType.isEmpty()) {
//            return false;
//        }
//
//        //we cannot add implements for class that annotated as super class
//        if (!JavacHandlerUtil.hasAnnotation(ScreenEntitySuperClass.class, typeNode)) {
//            List<JCExpression> resultingList = List.nil();
//            // represents a list of full qualified declared interfaces
//            List<String> existingFqInterfacesNames = List.nil();
//            // represents a list of simple declared interfaces
//            List<String> existingInterfacesNames = List.nil();
//
//            if (typeDecl.implementing != null) {
//                for (JCExpression expression : typeDecl.implementing) {
//                    if(expression.getKind() != Tree.Kind.INTERFACE)
//                        continue;
//                    resultingList.add(expression);
////                    String typeName = ((JCTypeCast) expression).getType().type.toString();
////                    String [] typeNameArray = typeName.split(".");
////                    if (typeNameArray.length > 1) {
////                        // add to fq list
////                        existingFqInterfacesNames.add(Eclipse.toQualifiedName(typeName));
////                    } else {
////                        existingInterfacesNames.add(String.valueOf(expression.getLastToken()));
////                    }
//                }
//            }
//
//            String simpleName = probableFQType;
//            boolean qualified = simpleName.contains(".");
//            if (qualified) {
//                String[] split = simpleName.split("\\.");
//                simpleName = split[split.length - 1];
//            }
//            // check that current type was not already declared
////            if (!existingFqInterfacesNames.contains(probableFQType) && !existingInterfacesNames.contains(simpleName)) {
//            if (!resultingList.contains(probableFQType) && !existingInterfacesNames.contains(simpleName)) {
////                String fromImports = typeNode.getAst().getImportList().getFullyQualifiedNameForSimpleName(simpleName);
////                TypeReference typeReference = EclipseAstUtil.createTypeReference(qualified || fromImports == null ? probableFQType : simpleName);
//
//                resultingList.add(typeReference);
//            }
//            // set new list of interfaces
//
//            JavacHandlerUtil.injectType(typeNode.up(), annotationNode.)
//
//            List<JCExpression> superInterfaces = List.nil();
//            typeDecl.implementing =  superInterfaces;
//
//
//        }
//        return true;
//    }
//
//    public static JCClassDecl checkAnnotation(JavacNode typeNode, JavacNode annotationNode) {
//        JCClassDecl typeDecl = null;
//        if (typeNode.get() instanceof JCClassDecl) {
//            typeDecl = (JCClassDecl) typeNode.get();
//        }
//
//        long modifiers = typeDecl == null ? 0 : typeDecl.mods.flags;
//        boolean notAClass = (modifiers & (Flags.INTERFACE | Flags.ANNOTATION | Flags.ENUM)) != 0;
//
//        if (typeDecl == null | notAClass) {
//            annotationNode.addError("@Implements is only supported on a class.");
//            return null;
//        }
//
//        return typeDecl;
//    }
//}
