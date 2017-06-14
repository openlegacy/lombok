package lombok.javac.handlers;

import com.sun.tools.javac.tree.JCTree.JCAnnotation;
import lombok.core.AnnotationValues;
import lombok.javac.JavacAnnotationHandler;
import lombok.javac.JavacNode;
import lombok.javac.handlers.openlegacy.DbEntityHandler;
import org.mangosdk.spi.ProviderFor;
import org.openlegacy.core.annotations.db.DbEntity;

import static lombok.javac.handlers.OLJavacHandlerUtil.*;

/**
 * @author Matvey Mitnitsky on 25-May-17.
 */
@ProviderFor(JavacAnnotationHandler.class)
public class HandleDbEntity extends JavacAnnotationHandler<DbEntity> {

    @Override
    public void handle(AnnotationValues<DbEntity> annotation, JCAnnotation ast, JavacNode annotationNode) {
        JavacNode typeNode = annotationNode.up();

        if (validateAnnotation(typeNode, annotationNode)) {
            DbEntityHandler.handle(typeNode);
            generateGettersAndSetters(typeNode, annotationNode);
        }
    }
}
