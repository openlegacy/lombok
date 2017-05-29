package lombok.eclipse.handlers;

import lombok.AccessLevel;
import lombok.OLData;
import lombok.core.AnnotationValues;
import lombok.eclipse.Eclipse;
import lombok.eclipse.EclipseAnnotationHandler;
import lombok.eclipse.EclipseNode;
import lombok.eclipse.handlers.openlegacy.DbEntityInterfaceHandler;
import lombok.eclipse.handlers.openlegacy.EclipseHandlerUtil;
import lombok.eclipse.handlers.openlegacy.RpcEntityInterfaceHandler;
import lombok.eclipse.handlers.openlegacy.ScreenEntityInterfaceHandler;
import openlegacy.utils.EclipseAstUtil;
import openlegacy.utils.EclipseImportsUtil;
import openlegacy.utils.StringUtil;
import org.eclipse.jdt.internal.compiler.ast.AllocationExpression;
import org.eclipse.jdt.internal.compiler.ast.Annotation;
import org.eclipse.jdt.internal.compiler.ast.FieldDeclaration;
import org.eclipse.jdt.internal.compiler.ast.QualifiedTypeReference;
import org.eclipse.jdt.internal.compiler.ast.TypeDeclaration;
import org.eclipse.jdt.internal.compiler.ast.TypeReference;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFileConstants;
import org.mangosdk.spi.ProviderFor;
import org.openlegacy.db.DbEntity;
import org.openlegacy.rpc.RpcEntity;
import org.openlegacy.terminal.ScreenEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static lombok.eclipse.handlers.openlegacy.EclipseHandlerUtil.*;

/**
 * <h1>Prototype Handler</h1>
 *
 * Handles the {@code lombok.Implements} annotation for java compiler.
 *
 * @author Matvey Mitnitsky, Tom Fingerman
 * @since 3.6.0-SNAPSHOT
 */
@ProviderFor(EclipseAnnotationHandler.class)
public class HandleOLData extends EclipseAnnotationHandler<OLData> {

    @Override
    public void handle(AnnotationValues<OLData> annotation, Annotation ast, EclipseNode annotationNode) {
        EclipseNode typeNode = annotationNode.up();
        TypeDeclaration typeDecl = checkAnnotation(typeNode, annotationNode);

        if (typeDecl == null) {
            return;
        }

        OLData instance = annotation.getInstance();
        Class<?> entityType = instance.value();

        if (entityType.getName().equals(ScreenEntity.class.getName())) {
            boolean supportTerminalData = EclipseHandlerUtil.supportTerminalData(typeDecl.annotations);
            ScreenEntityInterfaceHandler.handle(typeNode, supportTerminalData);
        }

        if (entityType.getName().equals(RpcEntity.class.getName())) {

            RpcEntityInterfaceHandler.handle(typeNode);
        }

        if (entityType.getName().equals(DbEntity.class.getName())) {
            addImplements(typeNode, entityType, Serializable.class);
            DbEntityInterfaceHandler.handle(typeNode);
        }

        if (instance.getters()) {
            new HandleGetter().generateGetterForType(typeNode, annotationNode, AccessLevel.PUBLIC, true);
        }
        if (instance.setters()) {
            new HandleSetter().generateSetterForType(typeNode, annotationNode, AccessLevel.PUBLIC, true);
        }
    }

    private void handleScreens(EclipseNode typeNode, String interfaceName) {
        TypeDeclaration typeDecl = (TypeDeclaration) typeNode.get();
        if (!fieldExist(typeDecl.fields, StringUtil.getVariableName("focusField"))) {
            FieldDeclaration decl = new FieldDeclaration("focusField".toCharArray(), 0, 0);
            decl.modifiers = decl.modifiers | ClassFileConstants.AccPrivate;
            decl.type = EclipseAstUtil.createTypeReference(String.class.getSimpleName());
            lombok.eclipse.handlers.EclipseHandlerUtil.injectField(typeNode, decl);
        }
        // create pcCommand field
        if (!fieldExist(typeDecl.fields, StringUtil.getVariableName("pcCommand"))) {
            FieldDeclaration decl = new FieldDeclaration("pcCommand".toCharArray(), 0, 0);
            decl.modifiers = decl.modifiers | ClassFileConstants.AccPrivate;
            decl.type = EclipseAstUtil.createTypeReference(String.class.getSimpleName());
            lombok.eclipse.handlers.EclipseHandlerUtil.injectField(typeNode, decl);

        }
        //create actions field
        if (!fieldExist(typeDecl.fields, StringUtil.getVariableName("actions"))) {
            FieldDeclaration decl = new FieldDeclaration("actions".toCharArray(), 0, 0);
            decl.modifiers = decl.modifiers | ClassFileConstants.AccPrivate;
            char[][] terminalAction = Eclipse.fromQualifiedName("TerminalActionDefinition");
//            EclipseImportsUtil.getTypeName(typeNode.getAst(), TerminalActionDefinition.class);
            TypeReference typeRef = new QualifiedTypeReference(terminalAction,
                    Eclipse.poss(typeNode.get(), terminalAction.length));
//            TypeReference typeArg = EclipseAstUtil.createTypeReference(EclipseImportsUtil.getTypeName(typeNode.getAst(), TerminalActionDefinition.class));
            //return type
            String list = EclipseImportsUtil.getTypeName(typeNode.getAst(), List.class);
            decl.type = EclipseAstUtil.createParametrizedTypeReference(list, typeRef, 1);
            //initializer
            AllocationExpression allocationExpression = new AllocationExpression();
            allocationExpression.type = EclipseAstUtil.createParametrizedTypeReference(
                    EclipseImportsUtil.getTypeName(typeNode.getAst(), ArrayList.class), typeRef, 1);
            decl.initialization = allocationExpression;
            //to hide setter need to add @Setter(value = AccessLevel.NONE)
            EclipseAstUtil.addLombokSetterOnField(decl, AccessLevel.NONE);
            lombok.eclipse.handlers.EclipseHandlerUtil.injectField(typeNode, decl);
        }
    }



    private static boolean fieldExist(FieldDeclaration[] fields, String fieldName) {
        if (fields == null || fields.length == 0 || fieldName == null || fieldName.trim().isEmpty()) {
            return false;
        }
        for (FieldDeclaration declaration : fields) {
            if (fieldName.equals(declaration.name)) {
                return true;
            }
        }
        return false;
    }
}

