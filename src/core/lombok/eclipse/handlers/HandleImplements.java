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
import org.eclipse.jdt.internal.compiler.ast.TypeDeclaration;
import org.eclipse.jdt.internal.compiler.ast.TypeReference;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFileConstants;
import org.mangosdk.spi.ProviderFor;
import org.openlegacy.annotations.screen.ScreenEntitySuperClass;
import org.openlegacy.terminal.ScreenEntity;

import lombok.AccessLevel;
import lombok.Implements;
import lombok.core.AnnotationValues;
import lombok.eclipse.EclipseAnnotationHandler;
import lombok.eclipse.EclipseNode;
import openlegacy.ScreenEntityInterfaceHandler;
import openlegacy.utils.EclipseAstUtil;
import openlegacy.utils.StringUtil;

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
		// get type node
		EclipseNode typeNode = annotationNode.up();
		TypeDeclaration typeDecl = checkAnnotation(typeNode, annotationNode);

		if (typeDecl == null) {
			return;
		}

		String probableFQType = annotationValues.getProbableFQType("value");
		generateImplementsForType(typeNode, annotationNode, probableFQType);

		Implements instance = annotationValues.getInstance();
		if (ScreenEntity.class.getName().equals(probableFQType)) {
			new ScreenEntityInterfaceHandler().handle(typeNode, annotationNode);
		}

		if (instance.getters()) {
			new HandleGetter().generateGetterForType(typeNode, annotationNode, AccessLevel.PUBLIC, true);
		}
		if (instance.setters()) {
			new HandleSetter().generateSetterForType(typeNode, annotationNode, AccessLevel.PUBLIC, true);
		}
	}

	/**
	 * Generates new or extends existing "implements"
	 */
	public boolean generateImplementsForType(EclipseNode typeNode, EclipseNode annotationNode, String type) {
		TypeDeclaration typeDecl = checkAnnotation(typeNode, annotationNode);

		if (typeDecl == null || type == null || type.isEmpty()) {
			return false;
		}

		//we cannot add implements for class that annotated as super class
		if (!EclipseHandlerUtil.hasAnnotation(ScreenEntitySuperClass.class, typeNode)) {
			List<TypeReference> resultingList = new ArrayList<TypeReference>();
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
						existingFqInterfacesNames.add(StringUtil.char2dArrayToSingleString(typeName));
					} else {
						existingInterfacesNames.add(String.valueOf(reference.getLastToken()));
					}
				}
			}

			String simpleName = type;
			if (simpleName.contains(".")) {
				String[] split = simpleName.split("\\.");
				simpleName = split[split.length - 1];
			}
			// check that current type was not already declared
			if (!existingFqInterfacesNames.contains(type) && !existingInterfacesNames.contains(simpleName)) {
				String fromImports = typeNode.getAst().getImportList().getFullyQualifiedNameForSimpleName(simpleName);
				TypeReference typeReference = EclipseAstUtil.createTypeReference(fromImports == null ? type : simpleName);
				resultingList.add(typeReference);
			}
			// set new list of interfaces
			typeDecl.superInterfaces = resultingList.toArray(new TypeReference[] {});

		}
		return true;
	}

	public static TypeDeclaration checkAnnotation(EclipseNode typeNode, EclipseNode annotationNode) {
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

}
