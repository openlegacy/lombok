package lombok.eclipse.handlers;

import lombok.core.AnnotationValues;
import lombok.eclipse.EclipseAnnotationHandler;
import lombok.eclipse.EclipseNode;
import lombok.eclipse.handlers.openlegacy.DbEntityInterfaceHandler;
import org.eclipse.jdt.internal.compiler.ast.Annotation;
import org.eclipse.jdt.internal.compiler.ast.TypeDeclaration;
import org.mangosdk.spi.ProviderFor;
import org.openlegacy.annotations.db.DbEntity;

import static lombok.eclipse.handlers.openlegacy.EclipseHandlerUtil.*;

/**
 * @author Matvey Mitnitsky on 22-May-17.
 */

@ProviderFor(EclipseAnnotationHandler.class)
public class HandleDbEntity extends EclipseAnnotationHandler<DbEntity> {

    @Override
    public void handle(AnnotationValues<DbEntity> annotation, Annotation ast, EclipseNode annotationNode) {
        EclipseNode typeNode = annotationNode.up();

        if (validateAnnotation(typeNode, annotationNode)) {
            DbEntityInterfaceHandler.handle(typeNode);
            getenerateLombokGetAndSet(typeNode, annotationNode);
        }
    }
}
