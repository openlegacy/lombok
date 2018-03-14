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

package lombok.javac.handlers;

import static lombok.javac.handlers.OLJavacHandlerUtil.*;

import org.mangosdk.spi.ProviderFor;
import org.openlegacy.core.annotations.BusinessEntity;

import com.sun.tools.javac.tree.JCTree;

import lombok.core.AST;
import lombok.core.AnnotationValues;
import lombok.javac.JavacAnnotationHandler;
import lombok.javac.JavacNode;
import lombok.javac.handlers.openlegacy.BusinessEntityHandler;

/**
 * @author Vadym Ladonya
 * @since 4.1.0
 */
@ProviderFor(JavacAnnotationHandler.class)
public class HandleBusinessEntity extends JavacAnnotationHandler<BusinessEntity> {
	
	@Override public void handle(AnnotationValues<BusinessEntity> annotation, JCTree.JCAnnotation ast, JavacNode annotationNode) {
		JavacNode typeNode = annotationNode.up();
		
		if (validateAnnotation(typeNode, annotationNode)) {
			handleEntityWithInnerClasses(typeNode, annotationNode);
		}
	}
	
	/**
	 * Handles class which annotated with {@link BusinessEntity} annotation and
	 * all its inner classes
	 */
	private void handleEntityWithInnerClasses(JavacNode typeNode, JavacNode annotationNode) {
		BusinessEntityHandler.handle(typeNode);
		generateGettersAndSetters(typeNode, annotationNode);
		// process inner classes
		for (JavacNode child : typeNode.down()) {
			if (child.getKind() == AST.Kind.TYPE) {
				handleEntityWithInnerClasses(child, annotationNode);
			}
		}
	}
}
