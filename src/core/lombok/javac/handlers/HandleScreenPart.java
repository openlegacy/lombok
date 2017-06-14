package lombok.javac.handlers;

import com.sun.tools.javac.tree.JCTree;
import lombok.core.AnnotationValues;
import lombok.javac.JavacAnnotationHandler;
import lombok.javac.JavacNode;
import lombok.javac.handlers.openlegacy.ScreenEntityHandler;
import org.mangosdk.spi.ProviderFor;
import org.openlegacy.core.annotations.screen.ScreenPart;

import static lombok.javac.handlers.OLJavacHandlerUtil.*;

/**
 * @author Matvey Mitnitsky on 14-Jun-17.
 */
@ProviderFor(JavacAnnotationHandler.class)
public class HandleScreenPart extends JavacAnnotationHandler<ScreenPart> {

    @Override
    public void handle(AnnotationValues<ScreenPart> annotation, JCTree.JCAnnotation ast, JavacNode annotationNode) {
        JavacNode typeNode = annotationNode.up();

        if (validateAnnotation(typeNode, annotationNode)) {
            ScreenPart instance = annotation.getInstance();
            boolean supportTerminalData = instance.supportTerminalData();
            ScreenEntityHandler.handle(typeNode, supportTerminalData, false);
            generateGettersAndSetters(typeNode, annotationNode);
        }
    }
}
