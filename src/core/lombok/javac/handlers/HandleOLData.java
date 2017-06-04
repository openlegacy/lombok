package lombok.javac.handlers;

import com.sun.tools.javac.tree.JCTree.JCAnnotation;
import com.sun.tools.javac.tree.JCTree.JCClassDecl;
import lombok.AccessLevel;
import lombok.OLData;
import lombok.core.AnnotationValues;
import lombok.javac.JavacAnnotationHandler;
import lombok.javac.JavacNode;
import lombok.javac.handlers.openlegacy.DbEntityInterfaceHandler;
import lombok.javac.handlers.openlegacy.RpcEntityInterfaceHandler;
import lombok.javac.handlers.openlegacy.ScreenEntityInterfaceHandler;
import org.mangosdk.spi.ProviderFor;
import org.openlegacy.db.DbEntity;
import org.openlegacy.rpc.RpcEntity;
import org.openlegacy.terminal.ScreenEntity;

import java.io.Serializable;

import static lombok.javac.handlers.OLJavacHandlerUtil.*;

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
            ScreenEntityInterfaceHandler.handle(typeNode, false);
        }

        if (isRpcEntity = entityType.getName().equals(RpcEntity.class.getName())) {
            String interfaceName = entityType.getName();
            addImplements(typeNode, interfaceName);

            RpcEntityInterfaceHandler.handle(typeNode, false);
        }

        if (entityType.getName().equals(DbEntity.class.getName())) {
            String interfaceName = entityType.getName();
            addImplements(typeNode, interfaceName);
            addImplements(typeNode, Serializable.class.getName());
            DbEntityInterfaceHandler.handle(typeNode);
        }

        new HandleGetter().generateGetterForType(typeNode, annotationNode, AccessLevel.PUBLIC, true);
        new HandleSetter().generateSetterForType(typeNode, annotationNode, AccessLevel.PUBLIC, true);
        if (isRpcEntity)
            createJacksonAnnotations(typeNode);
    }


}
