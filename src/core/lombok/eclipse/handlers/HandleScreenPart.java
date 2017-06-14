package lombok.eclipse.handlers;

import lombok.core.AnnotationValues;
import lombok.eclipse.EclipseAnnotationHandler;
import lombok.eclipse.EclipseNode;
import lombok.eclipse.handlers.openlegacy.ScreenEntityHandler;
import org.eclipse.jdt.internal.compiler.ast.Annotation;
import org.eclipse.jdt.internal.compiler.ast.TypeDeclaration;
import org.mangosdk.spi.ProviderFor;
import org.openlegacy.core.annotations.screen.ScreenPart;

import static lombok.eclipse.handlers.openlegacy.EclipseHandlerUtil.*;

/**
 * @author Matvey Mitnitsky on 14-Jun-17.
 */
@ProviderFor(EclipseAnnotationHandler.class)
public class HandleScreenPart extends EclipseAnnotationHandler<ScreenPart> {

    @Override
    public void handle(AnnotationValues<ScreenPart> annotation, Annotation ast, EclipseNode annotationNode) {
        EclipseNode typeNode = annotationNode.up();
        TypeDeclaration typeDecl = checkAnnotation(typeNode, annotationNode);

        if (validateAnnotation(typeNode, annotationNode)) {
            ScreenPart annotationInstance = annotation.getInstance();
            boolean supportTerminalData = annotationInstance.supportTerminalData();
            ScreenEntityHandler.handle(typeNode, supportTerminalData, false);
            getenerateLombokGetAndSet(typeNode, annotationNode);
        }
    }
}
