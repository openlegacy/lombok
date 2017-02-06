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

package openlegacy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.AllocationExpression;
import org.eclipse.jdt.internal.compiler.ast.Annotation;
import org.eclipse.jdt.internal.compiler.ast.FieldDeclaration;
import org.eclipse.jdt.internal.compiler.ast.MemberValuePair;
import org.eclipse.jdt.internal.compiler.ast.TypeDeclaration;
import org.eclipse.jdt.internal.compiler.ast.TypeReference;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFileConstants;
import org.eclipse.jdt.internal.compiler.lookup.ClassScope;
import org.openlegacy.annotations.screen.ScreenEntity;
import org.openlegacy.annotations.screen.ScreenEntitySuperClass;
import org.openlegacy.terminal.TerminalField;
import org.openlegacy.terminal.TerminalSnapshot;
import org.openlegacy.terminal.definitions.TerminalActionDefinition;

import lombok.core.AST.Kind;
import lombok.eclipse.EclipseNode;
import lombok.eclipse.handlers.EclipseHandlerUtil;
import lombok.eclipse.handlers.HandleImplements;
import lombok.eclipse.handlers.SetGeneratedByVisitor;
import openlegacy.utils.EclipseAstUtil;
import openlegacy.utils.EclipseImportsUtil;
import openlegacy.utils.StringUtil;

/**
 * @author Ivan Bort
 * @since 3.6.0-SNAPSHOT
 */
public class ScreenEntityInterfaceHandler {

	private static final String JSON_PROPERTY = "com.fasterxml.jackson.annotation.JsonProperty";
	private static final String JSON_DESERIALIZE = "com.fasterxml.jackson.databind.annotation.JsonDeserialize";
	private static final String JSON_SERIALIZE = "com.fasterxml.jackson.databind.annotation.JsonSerialize";

	private String terminalField;
	private String terminalSnapshot;
	private String terminalActionDefinition;

	public void handle(EclipseNode typeNode, EclipseNode annotationNode) {
		TypeDeclaration typeDecl = HandleImplements.checkAnnotation(typeNode, annotationNode);
		if (typeDecl == null) {
			return;
		}

		Annotation[] annotations = typeDecl.annotations;
		boolean superClassAnnotation = EclipseHandlerUtil.hasAnnotation(ScreenEntitySuperClass.class, typeNode);
		boolean supportTerminalData = supportTerminalData(annotations);

		if (supportTerminalData) {
			// get names to be used in the CU
			terminalField = EclipseImportsUtil.getTypeName(typeNode.getAst(), TerminalField.class);
			terminalSnapshot = EclipseImportsUtil.getTypeName(typeNode.getAst(), TerminalSnapshot.class);
		}
		if (!superClassAnnotation) {
			// get names to be used in the CU
			terminalActionDefinition = EclipseImportsUtil.getTypeName(typeNode.getAst(), TerminalActionDefinition.class);
		}

		List<FieldDeclaration> newFields = new ArrayList<FieldDeclaration>();
		if (!superClassAnnotation) {
			createNonSuperEntityFields(typeNode, newFields, supportTerminalData);
		}
		// add new fields into the type declaration
		injectFields(typeNode, newFields);
	}

	private void createNonSuperEntityFields(EclipseNode typeNode, List<FieldDeclaration> newFields, boolean supportTerminalData) {
		TypeDeclaration typeDecl = (TypeDeclaration) typeNode.get();
		//create terminalSnapshot field
		if (supportTerminalData && !fieldExist(typeDecl.fields, StringUtil.getVariableName(terminalSnapshot))) {
			FieldDeclaration decl = new FieldDeclaration(StringUtil.getVariableName(terminalSnapshot).toCharArray(), 0, 0);
			decl.modifiers = decl.modifiers | ClassFileConstants.AccPrivate;
			decl.type = EclipseAstUtil.createTypeReference(terminalSnapshot);
			newFields.add(decl);
		}
		//create focusField field
		if (!fieldExist(typeDecl.fields, StringUtil.getVariableName("focusField"))) {
			FieldDeclaration decl = new FieldDeclaration("focusField".toCharArray(), 0, 0);
			decl.modifiers = decl.modifiers | ClassFileConstants.AccPrivate;
			decl.type = EclipseAstUtil.createTypeReference(String.class.getSimpleName());
			newFields.add(decl);
		}
		// create pcCommand field
		if (!fieldExist(typeDecl.fields, StringUtil.getVariableName("pcCommand"))) {
			FieldDeclaration decl = new FieldDeclaration("pcCommand".toCharArray(), 0, 0);
			decl.modifiers = decl.modifiers | ClassFileConstants.AccPrivate;
			decl.type = EclipseAstUtil.createTypeReference(String.class.getSimpleName());
			newFields.add(decl);
		}
		//create actions field
		if (!fieldExist(typeDecl.fields, StringUtil.getVariableName("actions"))) {
			FieldDeclaration decl = new FieldDeclaration("actions".toCharArray(), 0, 0);
			decl.modifiers = decl.modifiers | ClassFileConstants.AccPrivate;
			TypeReference typeArg = EclipseAstUtil.createTypeReference(terminalActionDefinition);
			//return type
			String list = EclipseImportsUtil.getTypeName(typeNode.getAst(), List.class);
			decl.type = EclipseAstUtil.createParametrizedTypeReference(list, typeArg);
			//initializer
			AllocationExpression allocationExpression = new AllocationExpression();
			allocationExpression.type = EclipseAstUtil
					.createParametrizedTypeReference(EclipseImportsUtil.getTypeName(typeNode.getAst(), ArrayList.class), typeArg);
			decl.initialization = allocationExpression;
			newFields.add(decl);
		}
	}

	private static void injectFields(EclipseNode typeNode, List<FieldDeclaration> fields) {
		TypeDeclaration typeDecl = (TypeDeclaration) typeNode.get();
		FieldDeclaration[] typeDeclFields = typeDecl.fields;
		if (typeDeclFields == null) {
			typeDeclFields = new FieldDeclaration[0];
		}
		int initialLength = typeDeclFields.length;
		typeDeclFields = Arrays.copyOf(typeDeclFields, initialLength + fields.size());
		for (int i = initialLength, j = 0; i < typeDeclFields.length; i++, j++) {
			typeDeclFields[i] = fields.get(j);
		}
		typeDecl.fields = typeDeclFields;
		typeDecl.traverse(new SetGeneratedByVisitor(typeNode.get()), (ClassScope) null);
		//add new fields to the lombok node to allow generates Getters/Setters
		for (FieldDeclaration fieldDeclaration : typeDeclFields) {
			typeNode.add(fieldDeclaration, Kind.FIELD);
		}
	}

	private boolean supportTerminalData(Annotation[] annotations) {
		Annotation ann = null;
		for (Annotation annotation : annotations) {
			String singleString = StringUtil.char2dArrayToSingleString(annotation.type.getTypeName());
			if (ScreenEntity.class.getName().equals(singleString)) {
				ann = annotation;
				break;
			}
		}

		if (ann != null) {
			MemberValuePair[] pairs = ann.memberValuePairs();
			for (MemberValuePair pair : pairs) {
				if ("supportTerminalData".equals(pair.name)) {
					return true;
				}
			}
		}
		return false;
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
