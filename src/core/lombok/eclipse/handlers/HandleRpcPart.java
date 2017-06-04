package lombok.eclipse.handlers;

import lombok.core.AnnotationValues;
import lombok.eclipse.EclipseAnnotationHandler;
import lombok.eclipse.EclipseNode;
import lombok.eclipse.handlers.openlegacy.RpcEntityInterfaceHandler;
import org.eclipse.jdt.internal.compiler.ast.Annotation;
import org.mangosdk.spi.ProviderFor;
import org.openlegacy.annotations.rpc.RpcPart;

import static lombok.eclipse.handlers.openlegacy.EclipseHandlerUtil.*;

/**
 * @author Matvey Mitnitsky on 01-Jun-17.
 */
@ProviderFor(EclipseAnnotationHandler.class)
public class HandleRpcPart extends EclipseAnnotationHandler<RpcPart> {

    @Override
    public void handle(AnnotationValues<RpcPart> annotation, Annotation ast, EclipseNode annotationNode) {
        EclipseNode typeNode = annotationNode.up();

        if (validateAnnotation(typeNode, annotationNode)) {
            RpcEntityInterfaceHandler.handle(typeNode, true);
            getenerateLombokGetAndSet(typeNode, annotationNode);
        }
    }
}
