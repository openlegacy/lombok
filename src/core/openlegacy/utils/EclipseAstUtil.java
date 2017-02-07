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

package openlegacy.utils;

import static lombok.eclipse.Eclipse.poss;

import org.eclipse.jdt.internal.compiler.ast.Annotation;
import org.eclipse.jdt.internal.compiler.ast.FieldDeclaration;
import org.eclipse.jdt.internal.compiler.ast.MemberValuePair;
import org.eclipse.jdt.internal.compiler.ast.NormalAnnotation;
import org.eclipse.jdt.internal.compiler.ast.ParameterizedQualifiedTypeReference;
import org.eclipse.jdt.internal.compiler.ast.ParameterizedSingleTypeReference;
import org.eclipse.jdt.internal.compiler.ast.QualifiedTypeReference;
import org.eclipse.jdt.internal.compiler.ast.SingleTypeReference;
import org.eclipse.jdt.internal.compiler.ast.TypeReference;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.eclipse.Eclipse;
import lombok.eclipse.handlers.EclipseHandlerUtil;

/**
 * @author Ivan Bort
 * @since 3.6.0-SNAPSHOT
 */
public final class EclipseAstUtil {

	public static TypeReference createTypeReference(String name) {
		String simpleName = name;
		if (simpleName.contains(".")) {
			String[] split = simpleName.split("\\.");
			simpleName = split[split.length - 1];
		}
		TypeReference typeReference = null;
		if (name.contains(".")) {
			char[][] tokens = Eclipse.fromQualifiedName(name);
			typeReference = new QualifiedTypeReference(tokens, new long[tokens.length]);
		} else {
			typeReference = new SingleTypeReference(simpleName.toCharArray(), 0);
		}
		return typeReference;
	}

	public static TypeReference createParametrizedTypeReference(String name, TypeReference typeArg, int dimension) {
		if (name.contains(".")) {
			char[][] tokens = Eclipse.fromQualifiedName(name);
			TypeReference[][] typeArgs = new TypeReference[tokens.length][];
			TypeReference[] innerTypeArgs = new TypeReference[dimension];
			for (int i = 0; i< dimension; i++){
				innerTypeArgs[i] = typeArg;
			}
			typeArgs[tokens.length - 1] = innerTypeArgs;
			return new ParameterizedQualifiedTypeReference(tokens, typeArgs, 0, new long[tokens.length]);
		} else {
			TypeReference[] typeArgs = new TypeReference[dimension];
			for (int i = 0; i< dimension; i++){
				typeArgs[i] = typeArg;
			}
			return new ParameterizedSingleTypeReference(name.toCharArray(), typeArgs, 0, 0);
		}
	}

	public static void addLombokGetterOnField(FieldDeclaration fieldDeclaration, AccessLevel level) {
		char[][] qualifiedName = Eclipse.fromQualifiedName(Getter.class.getName());
		QualifiedTypeReference qtr = new QualifiedTypeReference(qualifiedName, poss(fieldDeclaration, qualifiedName.length));
		EclipseHandlerUtil.setGeneratedBy(qtr, fieldDeclaration);
		NormalAnnotation na = new NormalAnnotation(qtr, fieldDeclaration.sourceStart());
		MemberValuePair pair = new MemberValuePair(new char[] { 'v', 'a', 'l', 'u', 'e' }, 0, 0,
				EclipseHandlerUtil.createNameReference(AccessLevel.class.getName() + "." + level.name(), na));
		na.memberValuePairs = new MemberValuePair[] { pair };
		na.sourceStart = 1;
		EclipseHandlerUtil.setGeneratedBy(na, fieldDeclaration);
		if (fieldDeclaration.annotations == null) {
			fieldDeclaration.annotations = new Annotation[] { na };
			return;
		}
		Annotation[] newAnnotationArray = new Annotation[fieldDeclaration.annotations.length + 1];
		System.arraycopy(fieldDeclaration.annotations, 0, newAnnotationArray, 0, fieldDeclaration.annotations.length);
		newAnnotationArray[fieldDeclaration.annotations.length] = na;
		fieldDeclaration.annotations = newAnnotationArray;
	}

	public static void addLombokSetterOnField(FieldDeclaration fieldDeclaration, AccessLevel level) {
		char[][] qualifiedName = Eclipse.fromQualifiedName(Setter.class.getName());
		QualifiedTypeReference qtr = new QualifiedTypeReference(qualifiedName, poss(fieldDeclaration, qualifiedName.length));
		EclipseHandlerUtil.setGeneratedBy(qtr, fieldDeclaration);
		NormalAnnotation na = new NormalAnnotation(qtr, fieldDeclaration.sourceStart());
		MemberValuePair pair = new MemberValuePair(new char[] { 'v', 'a', 'l', 'u', 'e' }, 0, 0,
				EclipseHandlerUtil.createNameReference(AccessLevel.class.getName() + "." + level.name(), na));
		na.memberValuePairs = new MemberValuePair[] { pair };
		na.sourceStart = 1;
		EclipseHandlerUtil.setGeneratedBy(na, fieldDeclaration);
		if (fieldDeclaration.annotations == null) {
			fieldDeclaration.annotations = new Annotation[] { na };
			return;
		}
		Annotation[] newAnnotationArray = new Annotation[fieldDeclaration.annotations.length + 1];
		System.arraycopy(fieldDeclaration.annotations, 0, newAnnotationArray, 0, fieldDeclaration.annotations.length);
		newAnnotationArray[fieldDeclaration.annotations.length] = na;
		fieldDeclaration.annotations = newAnnotationArray;
	}
}
