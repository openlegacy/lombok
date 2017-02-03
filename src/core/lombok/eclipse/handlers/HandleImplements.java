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

package lombok.eclipse.handlers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.Annotation;
import org.eclipse.jdt.internal.compiler.ast.SingleTypeReference;
import org.eclipse.jdt.internal.compiler.ast.TypeDeclaration;
import org.eclipse.jdt.internal.compiler.ast.TypeReference;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFileConstants;
import org.mangosdk.spi.ProviderFor;

import lombok.AccessLevel;
import lombok.Implements;
import lombok.core.AnnotationValues;
import lombok.eclipse.EclipseAnnotationHandler;
import lombok.eclipse.EclipseNode;

/**
 * Handles the {@code lombok.Implements} annotation for eclipse.
 * 
 * @author Ivan Bort
 * @since 3.6.0-SNAPSHOT
 */
@ProviderFor(EclipseAnnotationHandler.class)
public class HandleImplements extends EclipseAnnotationHandler<Implements> {

	@Override
	public void handle(AnnotationValues<Implements> annotationValues, Annotation annotation, EclipseNode annotationNode) {
		//get type node
		EclipseNode typeNode = annotationNode.up();
		TypeDeclaration typeDecl = checkAnnotation(typeNode, annotationNode);

		if (typeDecl == null) {
			return;
		}

		//get classes to be added into the implements 
		Implements instance = annotationValues.getInstance();

		generateImplementsForType(typeNode, annotationNode, annotationValues.getProbableFQTypes("value"), instance.getters(), instance.setters());
	}

	/**
	 * Generates new or extends existing "implements"
	 */
	public boolean generateImplementsForType(EclipseNode typeNode, EclipseNode annotationNode, List<String> types,
			boolean getters, boolean setters) {
		TypeDeclaration typeDecl = checkAnnotation(typeNode, annotationNode);

		if (typeDecl == null || types == null || types.isEmpty()) {
			return false;
		}

		List<TypeReference> resultingList = new ArrayList<TypeReference>();
		long pos = 0;
		// represents a list of full qualified declared interfaces
		List<String> existingFqInterfacesNames = new ArrayList<String>();
		// represents a list of simple declared interfaces
		List<String> existingInterfacesNames = new ArrayList<String>();

		if (typeDecl.superInterfaces != null) {
			for (TypeReference reference : typeDecl.superInterfaces) {
				resultingList.add(reference);
				char[][] typeName = reference.getTypeName();
				if (typeName.length > 1) {
					// add to fq list
					existingFqInterfacesNames.add(char2dArrayToSingleString(typeName));
				} else {
					existingInterfacesNames.add(String.valueOf(reference.getLastToken()));
				}
				if (pos < reference.sourceEnd) {
					pos = reference.sourceEnd;
				}
			}
		}
		
		for (String clazz : types) {
			//check that current clazz
			if (existingFqInterfacesNames.contains(clazz)){
				continue;
			}
			String simpleName = clazz;
			if (simpleName.contains(".")) {
				String[] split = simpleName.split(".");
				simpleName = split[split.length-1];
			}
			if (existingInterfacesNames.contains(simpleName)){
				continue;
			}
			SingleTypeReference singleTypeReference = new SingleTypeReference(clazz.toCharArray(), pos);
			resultingList.add(singleTypeReference);
			if (pos < singleTypeReference.sourceEnd) {
				pos = singleTypeReference.sourceEnd;
			}
		}
		//set new list of interfaces
		typeDecl.superInterfaces = resultingList.toArray(new TypeReference[] {});

		if (getters) {
			new HandleGetter().generateGetterForType(typeNode, annotationNode, AccessLevel.PUBLIC, true);
		}
		if (setters) {
			new HandleSetter().generateSetterForType(typeNode, annotationNode, AccessLevel.PUBLIC, true);
		}
		return true;
	}

	private static TypeDeclaration checkAnnotation(EclipseNode typeNode, EclipseNode annotationNode) {
		TypeDeclaration typeDecl = null;
		if (typeNode.get() instanceof TypeDeclaration) {
			typeDecl = (TypeDeclaration) typeNode.get();
		}
		int modifiers = typeDecl == null ? 0 : typeDecl.modifiers;
		boolean notAClass = (modifiers
				& (ClassFileConstants.AccInterface | ClassFileConstants.AccAnnotation | ClassFileConstants.AccEnum)) != 0;

		if (typeDecl == null || notAClass) {
			annotationNode.addError("@Implements is only supported on a class.");
			return null;
		}
		return typeDecl;
	}

	private static String char2dArrayToSingleString(char[][] chars) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < chars.length; i++) {
			for (int j = 0; j < chars[i].length; j++) {
				builder.append(chars[i][j]);
			}
		}
		return builder.toString();
	}
}
