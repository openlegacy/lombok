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

/**
 * @author Ivan Bort
 * @since 3.6.0-SNAPSHOT
 */
public final class StringUtil {

	public static String getVariableName(String source){
		if (source == null || source.trim().isEmpty()){
			return null;
		}
		String[] split = source.split("\\.");
		String variable = split[split.length-1];
		char c[] = variable.toCharArray();
		c[0] = Character.toLowerCase(c[0]);
		return new String(c);
	}

	public static String firstCharToUpperCase(String string){
		char [] chars = string.toCharArray();
		return firstCharToUpperCase(chars);
	}

	public static String firstCharToUpperCase(char [] chars){
		chars[0] = Character.toUpperCase(chars[0]);
		return new String(chars);
	}

	public static char[] prepend(String input, String prefix) {
		return prepend(input.toCharArray(), prefix);
	}


	public static char[] prepend(char [] input, String prefix){
		if (prefix == null || prefix.isEmpty())
			return input;
		String previousName = StringUtil.firstCharToUpperCase(input);
		String fieldNameWithPrefix = prefix + previousName;
		return fieldNameWithPrefix.toCharArray();
	}
}
