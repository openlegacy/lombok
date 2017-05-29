package lombok.javac.handlers;

import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.JCAnnotation;
import lombok.core.AnnotationValues;
import lombok.javac.JavacAnnotationHandler;
import lombok.javac.JavacNode;
import lombok.javac.handlers.openlegacy.DbEntityInterfaceHandler;
import org.mangosdk.spi.ProviderFor;
import org.openlegacy.annotations.db.DbEntity;

import static lombok.javac.handlers.OLJavacHandlerUtil.*;

/**
 * @author Matvey Mitnitsky on 25-May-17.
 */
@ProviderFor(JavacAnnotationHandler.class)
public class HandleDbEntity extends JavacAnnotationHandler<DbEntity> {

    @Override
    public void handle(AnnotationValues<DbEntity> annotation, JCAnnotation ast, JavacNode annotationNode) {
        JavacNode typeNode = annotationNode.up();
        JCTree.JCClassDecl typeDecl = checkAnnotation(typeNode, annotationNode);
        if(typeDecl != null){

            DbEntityInterfaceHandler.handle(typeNode);
            
            generateGettersAndSetters(typeNode, annotationNode);
        }
    }
}
