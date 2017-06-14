package lombok.javac.handlers;

import com.sun.tools.javac.tree.JCTree;
import lombok.core.AnnotationValues;
import lombok.javac.JavacAnnotationHandler;
import lombok.javac.JavacNode;
import lombok.javac.handlers.openlegacy.ScreenEntityHandler;
import org.mangosdk.spi.ProviderFor;
import org.openlegacy.core.annotations.screen.ScreenEntitySuperClass;

import static lombok.javac.handlers.OLJavacHandlerUtil.*;

/**
 * @author Matvey Mitnitsky on 14-Jun-17.
 */
@ProviderFor(JavacAnnotationHandler.class)
public class HandleScreenEntitySuperClass extends JavacAnnotationHandler<ScreenEntitySuperClass>{

    @Override
    public void handle(AnnotationValues<ScreenEntitySuperClass> annotation, JCTree.JCAnnotation ast, JavacNode annotationNode) {
        JavacNode typeNode = annotationNode.up();

        if (validateAnnotation(typeNode, annotationNode)) {
            ScreenEntitySuperClass instance = annotation.getInstance();
            boolean supportTerminalData = instance.supportTerminalData();
            ScreenEntityHandler.handle(typeNode, supportTerminalData, false);
            generateGettersAndSetters(typeNode, annotationNode);
        }
    }
}
