package lombok.javac.handlers;

import static lombok.javac.handlers.OLJavacHandlerUtil.*;

import java.io.Serializable;

import org.mangosdk.spi.ProviderFor;
import org.openlegacy.core.annotations.BusinessEntity;
import org.openlegacy.core.db.DbEntity;
import org.openlegacy.core.rpc.RpcEntity;
import org.openlegacy.core.terminal.ScreenEntity;

import com.sun.tools.javac.tree.JCTree.JCAnnotation;
import com.sun.tools.javac.tree.JCTree.JCClassDecl;

import lombok.AccessLevel;
import lombok.OLData;
import lombok.core.AnnotationValues;
import lombok.javac.JavacAnnotationHandler;
import lombok.javac.JavacNode;
import lombok.javac.handlers.openlegacy.BusinessEntityHandler;
import lombok.javac.handlers.openlegacy.DbEntityHandler;
import lombok.javac.handlers.openlegacy.RpcEntityHandler;
import lombok.javac.handlers.openlegacy.ScreenEntityHandler;

/**
 * <h1>Prototype Handler</h1>
 * <p>
 * Handles the {@code lombok.Implements} annotation for java compiler.
 *
 * @author Matvey Mitnitsky, Tom Fingerman
 * @since 3.6.0-SNAPSHOT
 */

@ProviderFor(JavacAnnotationHandler.class)
public class HandleOLData extends JavacAnnotationHandler<OLData> {

    @Override
    public void handle(AnnotationValues<OLData> annotationValues, JCAnnotation ast, JavacNode annotationNode) {
        boolean isRpcEntity = false;
        JavacNode typeNode = annotationNode.up();
        JCClassDecl typeDecl = OLJavacHandlerUtil.checkAnnotation(typeNode, annotationNode);
        if (typeDecl == null) {
            return;
        }

        OLData instance = annotationValues.getInstance();
        Class<?> entityType = instance.value();

        if (entityType.getName().equals(ScreenEntity.class.getName())) {
            String interfaceName = entityType.getName();
            addImplements(typeNode, interfaceName);

            /**
             * prototype implementation of OLData was retrieving supportTerminalData value in the Handler method.
             * Now we can simply retrieve it from annotation in general handler as far as we are handling
             * ScreenEntity annotation instead of OLData.
             */
            ScreenEntityHandler.handle(typeNode, false, true);
        }

        if (isRpcEntity = entityType.getName().equals(RpcEntity.class.getName())) {
            String interfaceName = entityType.getName();
            addImplements(typeNode, interfaceName);

            RpcEntityHandler.handle(typeNode, true);
        }
		
        if (entityType.getName().equals(BusinessEntity.class.getName())) {
            BusinessEntityHandler.handle(typeNode);
        }

        if (entityType.getName().equals(DbEntity.class.getName())) {
            String interfaceName = entityType.getName();
            addImplements(typeNode, interfaceName);
            addImplements(typeNode, Serializable.class.getName());
            DbEntityHandler.handle(typeNode);
        }

        new HandleGetter().generateGetterForType(typeNode, annotationNode, AccessLevel.PUBLIC, true);
        new HandleSetter().generateSetterForType(typeNode, annotationNode, AccessLevel.PUBLIC, true);
        if (isRpcEntity)
            createJacksonAnnotations(typeNode);
    }


}
