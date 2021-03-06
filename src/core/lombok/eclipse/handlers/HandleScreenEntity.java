package lombok.eclipse.handlers;

import lombok.core.AnnotationValues;
import lombok.eclipse.EclipseAnnotationHandler;
import lombok.eclipse.EclipseNode;
import lombok.eclipse.handlers.openlegacy.ScreenEntityHandler;
import org.eclipse.jdt.internal.compiler.ast.Annotation;
import org.eclipse.jdt.internal.compiler.ast.TypeDeclaration;
import org.mangosdk.spi.ProviderFor;
import org.openlegacy.core.annotations.screen.ScreenEntity;

import static lombok.eclipse.handlers.openlegacy.EclipseHandlerUtil.*;

/**
 * @author Matvey Mitnitsky
 * @since 3.6.0-SNAPSHOT
 */
@ProviderFor(EclipseAnnotationHandler.class)
public class HandleScreenEntity extends EclipseAnnotationHandler<ScreenEntity> {

    @Override
    public void handle(AnnotationValues<ScreenEntity> annotation, Annotation ast, EclipseNode annotationNode) {
        EclipseNode typeNode = annotationNode.up();
        TypeDeclaration typeDecl = checkAnnotation(typeNode, annotationNode);

        if (validateAnnotation(typeNode, annotationNode)) {
            ScreenEntity annotationInstance = annotation.getInstance();
            boolean supportTerminalData = annotationInstance.supportTerminalData();
            ScreenEntityHandler.handle(typeNode, supportTerminalData, true);
            getenerateLombokGetAndSet(typeNode, annotationNode);
        }
    }
}
