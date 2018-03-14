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

package lombok.eclipse.handlers.openlegacy;

import static lombok.eclipse.handlers.openlegacy.EclipseHandlerUtil.addXmlAccessorType;

import lombok.eclipse.EclipseNode;

/**
 * @author Vadym Ladonya
 * @since 4.1.0
 */
public class BusinessEntityHandler {
	
	public static void handle(EclipseNode typeNode) {
		// add @XmlAccessorType(XmlAccessType.FIELD) to the class in order
		// to activate JAXB ignoring for metadata fields
		addXmlAccessorType(typeNode);
	}
	
}
