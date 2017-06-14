package lombok.eclipse.handlers;

import lombok.core.AnnotationValues;
import lombok.eclipse.EclipseAnnotationHandler;
import lombok.eclipse.EclipseNode;
import lombok.eclipse.handlers.openlegacy.RpcEntityHandler;
import org.eclipse.jdt.internal.compiler.ast.Annotation;
import org.mangosdk.spi.ProviderFor;
import org.openlegacy.core.annotations.rpc.RpcPartSuperClass;

import static lombok.eclipse.handlers.openlegacy.EclipseHandlerUtil.*;

/**
 * @author Matvey Mitnitsky on 14-Jun-17.
 */
@ProviderFor(EclipseAnnotationHandler.class)
public class HandleRpcPartSuperClass extends EclipseAnnotationHandler<RpcPartSuperClass> {
    @Override
    public void handle(AnnotationValues<RpcPartSuperClass> annotation, Annotation ast, EclipseNode annotationNode) {
        EclipseNode typeNode = annotationNode.up();

        if (validateAnnotation(typeNode, annotationNode)) {
            RpcEntityHandler.handle(typeNode,  false);
            getenerateLombokGetAndSet(typeNode, annotationNode);
        }
    }
}
