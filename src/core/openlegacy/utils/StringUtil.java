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

	public static String char2dArrayToSingleString(char[][] chars) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < chars.length; i++) {
			for (int j = 0; j < chars[i].length; j++) {
				builder.append(chars[i][j]);
			}
			builder.append(".");
		}
		return builder.toString()
				.substring(0, builder.length() - 1);
	}

	public static char[][] singleStringToChar2dArray(String source) {
		String[] split = source.split("\\.");
		char[][] char2dArray = new char[split.length][0];

		for (int i = 0; i < split.length; i++) {
			String value = split[i];
			char2dArray[i] = value.toCharArray();
		}

		return char2dArray;
	}
	
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
}
