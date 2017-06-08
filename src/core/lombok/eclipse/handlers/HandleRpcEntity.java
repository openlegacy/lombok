package lombok.eclipse.handlers;

import lombok.core.AnnotationValues;
import lombok.eclipse.EclipseAnnotationHandler;
import lombok.eclipse.EclipseNode;
import lombok.eclipse.handlers.openlegacy.RpcEntityInterfaceHandler;
import org.eclipse.jdt.internal.compiler.ast.Annotation;
import org.mangosdk.spi.ProviderFor;
import org.openlegacy.core.annotations.rpc.RpcEntity;

import static lombok.eclipse.handlers.openlegacy.EclipseHandlerUtil.*;

/**
 * @author Matvey Mitnitsky on 21-May-17.
 */

@ProviderFor(EclipseAnnotationHandler.class)
public class HandleRpcEntity extends EclipseAnnotationHandler<RpcEntity> {

    @Override
    public void handle(AnnotationValues<RpcEntity> annotation, Annotation ast, EclipseNode annotationNode) {
        EclipseNode typeNode = annotationNode.up();

        if (validateAnnotation(typeNode, annotationNode)) {
            RpcEntityInterfaceHandler.handle(typeNode, false);
            getenerateLombokGetAndSet(typeNode, annotationNode);
        }
    }
}
