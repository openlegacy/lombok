package lombok.eclipse.handlers;

import lombok.core.AnnotationValues;
import lombok.eclipse.EclipseAnnotationHandler;
import lombok.eclipse.EclipseNode;
import lombok.eclipse.handlers.openlegacy.ScreenEntityHandler;
import org.eclipse.jdt.internal.compiler.ast.Annotation;
import org.eclipse.jdt.internal.compiler.ast.TypeDeclaration;
import org.mangosdk.spi.ProviderFor;
import org.openlegacy.core.annotations.screen.ScreenEntitySuperClass;

import static lombok.eclipse.handlers.openlegacy.EclipseHandlerUtil.*;

/**
 * @author Matvey Mitnitsky on 14-Jun-17.
 */
@ProviderFor(EclipseAnnotationHandler.class)
public class HandleScreenEntitySuperClass extends EclipseAnnotationHandler<ScreenEntitySuperClass> {

    @Override
    public void handle(AnnotationValues<ScreenEntitySuperClass> annotation, Annotation ast, EclipseNode annotationNode) {
        EclipseNode typeNode = annotationNode.up();
        TypeDeclaration typeDecl = checkAnnotation(typeNode, annotationNode);

        if (validateAnnotation(typeNode, annotationNode)) {
            ScreenEntitySuperClass annotationInstance = annotation.getInstance();
            boolean supportTerminalData = annotationInstance.supportTerminalData();
            ScreenEntityHandler.handle(typeNode, supportTerminalData, false);
            getenerateLombokGetAndSet(typeNode, annotationNode);
        }
    }
}
