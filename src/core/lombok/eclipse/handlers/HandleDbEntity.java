package lombok.eclipse.handlers;

import static lombok.eclipse.handlers.openlegacy.EclipseHandlerUtil.*;

import org.eclipse.jdt.internal.compiler.ast.Annotation;
import org.eclipse.jdt.internal.compiler.ast.TypeDeclaration;
import org.mangosdk.spi.ProviderFor;
import org.openlegacy.annotations.db.DbEntity;

import lombok.core.AnnotationValues;
import lombok.eclipse.EclipseAnnotationHandler;
import lombok.eclipse.EclipseNode;
import lombok.eclipse.handlers.openlegacy.DbEntityInterfaceHandler;

/**
 * @author Matvey Mitnitsky on 22-May-17.
 */

@ProviderFor(EclipseAnnotationHandler.class) public class HandleDbEntity extends EclipseAnnotationHandler<DbEntity> {
	
	@Override public void handle(AnnotationValues<DbEntity> annotation, Annotation ast, EclipseNode annotationNode) {
		EclipseNode typeNode = annotationNode.up();
		TypeDeclaration typeDecl = checkAnnotation(typeNode, annotationNode);
		
		if (typeDecl == null) {
			return;
		}
		
		DbEntityInterfaceHandler.handle(typeNode);
		
		getenerateLombokGetAndSet(typeNode, annotationNode);
	}
}
