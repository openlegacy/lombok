package lombok.javac.handlers;

import com.sun.tools.javac.tree.JCTree;
import lombok.core.AnnotationValues;
import lombok.javac.JavacAnnotationHandler;
import lombok.javac.JavacNode;
import lombok.javac.handlers.openlegacy.RpcEntityInterfaceHandler;
import org.mangosdk.spi.ProviderFor;
import org.openlegacy.annotations.rpc.RpcEntity;

import static lombok.javac.handlers.OLJavacHandlerUtil.*;

/**
 * @author Matvey Mitnitsky on 24-May-17.
 */
@ProviderFor(JavacAnnotationHandler.class)
public class HandleRpcEntity extends JavacAnnotationHandler<RpcEntity> {

    @Override
    public void handle(AnnotationValues<RpcEntity> annotation, JCTree.JCAnnotation ast, JavacNode annotationNode) {
        JavacNode typeNode = annotationNode.up();
        JCTree.JCClassDecl typeDecl = checkAnnotation(typeNode, annotationNode);
        if (typeDecl != null) {

            RpcEntityInterfaceHandler.handle(typeNode);

            generateGettersAndSetters(typeNode, annotationNode);

        }
    }
}
