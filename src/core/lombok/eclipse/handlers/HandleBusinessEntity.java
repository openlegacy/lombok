/*
 * Copyright (c) 2018 OpenLegacy Inc.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     OpenLegacy Inc. - initial API and implementation
 */

package lombok.eclipse.handlers;

import static lombok.eclipse.handlers.openlegacy.EclipseHandlerUtil.*;

import org.eclipse.jdt.internal.compiler.ast.Annotation;
import org.mangosdk.spi.ProviderFor;
import org.openlegacy.core.annotations.BusinessEntity;

import lombok.core.AST;
import lombok.core.AnnotationValues;
import lombok.eclipse.EclipseAnnotationHandler;
import lombok.eclipse.EclipseNode;
import lombok.eclipse.handlers.openlegacy.BusinessEntityHandler;

/**
 * @author Vadym Ladonya
 * @since 4.1.0
 */

@ProviderFor(EclipseAnnotationHandler.class)
public class HandleBusinessEntity extends EclipseAnnotationHandler<BusinessEntity> {
	
	@Override
    public void handle(AnnotationValues<BusinessEntity> annotation, Annotation ast, EclipseNode annotationNode) {
        EclipseNode typeNode = annotationNode.up();

        if (validateAnnotation(typeNode, annotationNode)) {
			handleEntityWithInnerClasses(typeNode, annotationNode);
        }
    }

	/**
	 * Handles class which annotated with {@link BusinessEntity} annotation and
	 * all its inner classes
	 */
	private void handleEntityWithInnerClasses(EclipseNode typeNode, EclipseNode annotationNode) {
		BusinessEntityHandler.handle(typeNode);
		getenerateLombokGetAndSet(typeNode, annotationNode);
		
		// process inner classes
		for (EclipseNode child : typeNode.down()) {
			if (child.getKind() == AST.Kind.TYPE) {
				handleEntityWithInnerClasses(child, annotationNode);
			}
		}
	}
	
}
