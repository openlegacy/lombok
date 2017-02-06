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

import org.eclipse.jdt.internal.compiler.ast.ParameterizedQualifiedTypeReference;
import org.eclipse.jdt.internal.compiler.ast.ParameterizedSingleTypeReference;
import org.eclipse.jdt.internal.compiler.ast.QualifiedTypeReference;
import org.eclipse.jdt.internal.compiler.ast.SingleTypeReference;
import org.eclipse.jdt.internal.compiler.ast.TypeReference;

/**
 * @author Ivan Bort
 * @since 3.6.0-SNAPSHOT
 */
public final class EclipseAstUtil {

	public static TypeReference createTypeReference(String name){
		String simpleName = name;
		if (simpleName.contains(".")) {
			String[] split = simpleName.split("\\.");
			simpleName = split[split.length - 1];
		}
		TypeReference typeReference = null;
		if (name.contains(".")) {
			char[][] tokens = StringUtil.singleStringToChar2dArray(name);
			typeReference = new QualifiedTypeReference(tokens, new long[tokens.length]);
		} else {
			typeReference = new SingleTypeReference(simpleName.toCharArray(), 0);
		}
		return typeReference;
	}
	
	public static TypeReference createParametrizedTypeReference(String name, TypeReference typeArg){
		if (name.contains(".")){
			char[][] tokens = StringUtil.singleStringToChar2dArray(name);
			TypeReference[][] typeArgs = new TypeReference[tokens.length][];
			typeArgs[tokens.length-1] = new TypeReference[]{typeArg}; 
			return new ParameterizedQualifiedTypeReference(tokens, typeArgs, 0, new long[tokens.length]);
		} else {
			TypeReference[] typeArgs = new TypeReference[1];
			typeArgs[0] = typeArg; 
			return new ParameterizedSingleTypeReference(name.toCharArray(), typeArgs, 0, 0);
		}
	}
	
}
