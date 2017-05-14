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


import lombok.AccessLevel;
import lombok.core.AnnotationValues;
import lombok.eclipse.EclipseAnnotationHandler;
import lombok.eclipse.EclipseNode;
import openlegacy.utils.EclipseAstUtil;
import org.eclipse.jdt.internal.compiler.ast.Annotation;
import org.eclipse.jdt.internal.compiler.ast.FieldDeclaration;
import org.eclipse.jdt.internal.compiler.ast.TypeDeclaration;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFileConstants;
import org.mangosdk.spi.ProviderFor;
import org.openlegacy.annotations.screen.ScreenTable;

/**
 * @author Matvey Mitnitsky
 * @since 3.6.0-SNAPSHOT
 */
@ProviderFor(EclipseAnnotationHandler.class)
public class HandleScreenTable extends EclipseAnnotationHandler<ScreenTable>{

    private static final String FOCUS_FIELD_NAME = "focusField";

    @Override
    public void handle(AnnotationValues<ScreenTable> annotation, Annotation ast, EclipseNode annotationNode) {
        EclipseNode typeNode = annotationNode.up();
        TypeDeclaration typeDecl = (TypeDeclaration) typeNode.get();
        if(typeDecl == null)
            return;

        handleScreenTable(typeNode);

        new HandleGetter().generateGetterForType(typeNode, annotationNode, AccessLevel.PUBLIC, true);
        new HandleSetter().generateSetterForType(typeNode, annotationNode, AccessLevel.PUBLIC, true);
    }

    private void handleScreenTable(EclipseNode typeNode) {
        TypeDeclaration typeDecl = (TypeDeclaration) typeNode.get();
        if(!fieldExist(typeDecl.fields, FOCUS_FIELD_NAME)){
            FieldDeclaration focusFieldDecl = new FieldDeclaration();
            focusFieldDecl.modifiers = focusFieldDecl.modifiers | ClassFileConstants.AccPrivate;
            focusFieldDecl.name = FOCUS_FIELD_NAME.toCharArray();
            focusFieldDecl.type = EclipseAstUtil.createTypeReference("java.lang.String");

            EclipseHandlerUtil.injectField(typeNode, focusFieldDecl);
        }
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
