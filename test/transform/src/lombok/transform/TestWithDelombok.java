/*
 * Copyright (C) 2009-2015 The Project Lombok Authors.
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
 */
package lombok.transform;

import java.io.File;

import lombok.DirectoryRunner;
import org.junit.runner.RunWith;

@RunWith(DirectoryRunner.class)
public class TestWithDelombok extends DirectoryRunner.TestParams {

	public static final String OL_BEFORE_DIRECTORY = "test/transform/resource/openlegacy/before";
	public static final String OL_AFTER_DIRECTORY = "test/transform/resource/openlegacy/after-delombok";

	//set this directories to the getBeforeDirectory( ) and getAfterDirectory() to run tests on lombok annotations
	public static final String LOMBOK_BEFORE_DIRECTORY = "test/transform/resource/before";
	public static final String LOMBOK_AFTER_DIRECTORY = "test/transform/resource/after-ecj";

	@Override
	public DirectoryRunner.Compiler getCompiler() {
		return DirectoryRunner.Compiler.DELOMBOK;
	}
	
	@Override
	public boolean printErrors() {
		return true;
	}
	
	@Override
	public File getBeforeDirectory() {
		return new File(OL_BEFORE_DIRECTORY);
	}
	
	@Override
	public File getAfterDirectory() {
		return new File(OL_AFTER_DIRECTORY);
	}
	
	@Override
	public File getMessagesDirectory() {
		return new File("test/transform/resource/messages-delombok");
	}
	
	@Override
	public boolean expectChanges() {
		return true;
	}
}
