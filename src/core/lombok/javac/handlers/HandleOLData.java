package lombok.javac.handlers;

import static lombok.javac.handlers.JavacHandlerUtil.chainDotsString;

import org.mangosdk.spi.ProviderFor;

import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.tree.JCTree.JCAnnotation;
import com.sun.tools.javac.tree.JCTree.JCClassDecl;
import com.sun.tools.javac.tree.JCTree.JCExpression;
import com.sun.tools.javac.util.List;

import lombok.AccessLevel;
import lombok.OLData;
import lombok.core.AnnotationValues;
import lombok.javac.JavacAnnotationHandler;
import lombok.javac.JavacNode;
import lombok.javac.handlers.openlegacy.DbEntityInterfaceHandler;
import lombok.javac.handlers.openlegacy.RpcEntityInterfaceHandler;
import lombok.javac.handlers.openlegacy.ScreenEntitiyInterfaceHandler;

import org.openlegacy.db.DbEntity;
import org.openlegacy.rpc.RpcEntity;
import org.openlegacy.terminal.ScreenEntity;

import java.io.Serializable;

import static lombok.javac.handlers.OLJavacHandlerUtil.*;

/**
 * <h1>Prototype Handler</h1>
 *
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
            new ScreenEntitiyInterfaceHandler().handle(typeNode, annotationNode);
        }

        if(isRpcEntity = entityType.getName().equals(RpcEntity.class.getName())){
            String interfaceName = entityType.getName();
            addImplements(typeNode, interfaceName);

            RpcEntityInterfaceHandler.handle(typeNode);
        }

        if (entityType.getName().equals(DbEntity.class.getName())) {
            String interfaceName = entityType.getName();
            addImplements(typeNode, interfaceName);
            addImplements(typeNode, Serializable.class.getName());
            DbEntityInterfaceHandler.handle(typeNode);
        }

        new HandleGetter().generateGetterForType(typeNode, annotationNode, AccessLevel.PUBLIC, true);
        new HandleSetter().generateSetterForType(typeNode, annotationNode, AccessLevel.PUBLIC, true);
        if(isRpcEntity)
            createJacksonAnnotations(typeNode);
    }


}
