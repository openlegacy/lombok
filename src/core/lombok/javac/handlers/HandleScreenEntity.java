package lombok.javac.handlers;

import com.sun.tools.javac.tree.JCTree;
import lombok.core.AnnotationValues;
import lombok.eclipse.handlers.openlegacy.ScreenEntityInterfaceHandler;
import lombok.javac.JavacAnnotationHandler;
import lombok.javac.JavacNode;
import org.mangosdk.spi.ProviderFor;
import org.openlegacy.annotations.screen.ScreenEntity;

/**
 * @author Matvey Mitnitsky
 * @since 3.6.0-SNAPSHOT
 */
@ProviderFor(JavacAnnotationHandler.class)
public class HandleScreenEntity extends JavacAnnotationHandler<ScreenEntity>{

    @Override
    public void handle(AnnotationValues<ScreenEntity> annotation, JCTree.JCAnnotation ast, JavacNode annotationNode) {
        JavacNode typeNode = annotationNode.up();
        JCTree.JCClassDecl typeDecl = OLJavacHandlerUtil.checkAnnotation(typeNode, annotationNode);
        if (typeDecl == null) {
            return;
        }

        ScreenEntity instance = annotation.getInstance();
        boolean supportTerminalData = instance.supportTerminalData();
//        ScreenEntityInterfaceHandler.handle(typeNode, supportTerminalData);

    }
}
