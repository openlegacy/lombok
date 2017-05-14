package lombok.eclipse.handlers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import openlegacy.eclipse.DbEntityInterfaceHandler;
import openlegacy.eclipse.ScreenEntityInterfaceHandler;

import openlegacy.eclipse.RpcEntityInterfaceHandler;
import org.eclipse.jdt.internal.compiler.ast.AllocationExpression;
import org.eclipse.jdt.internal.compiler.ast.Annotation;
import org.eclipse.jdt.internal.compiler.ast.FieldDeclaration;
import org.eclipse.jdt.internal.compiler.ast.QualifiedTypeReference;
import org.eclipse.jdt.internal.compiler.ast.TypeDeclaration;
import org.eclipse.jdt.internal.compiler.ast.TypeReference;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFileConstants;
import org.mangosdk.spi.ProviderFor;

import lombok.AccessLevel;
import lombok.OLData;
import lombok.core.AnnotationValues;
import lombok.eclipse.Eclipse;
import lombok.eclipse.EclipseAnnotationHandler;
import lombok.eclipse.EclipseNode;
import openlegacy.utils.EclipseAstUtil;
import openlegacy.utils.EclipseImportsUtil;
import openlegacy.utils.StringUtil;
import org.openlegacy.db.DbEntity;
import org.openlegacy.rpc.RpcEntity;
import org.openlegacy.terminal.ScreenEntity;

/**
 * Created by  on 24-Apr-17.
 *
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
            addImplements(typeDecl, entityType.getName());
//            handleScreens(typeNode, entityType.getName());
            new ScreenEntityInterfaceHandler().handle(typeNode, annotationNode);
        }

        if (entityType.getName().equals(RpcEntity.class.getName())) {
            String interfaceName = entityType.getName();
            addImplements(typeDecl, interfaceName);

            //TODO implement RpcEntityInterfaceHandler
            new RpcEntityInterfaceHandler().handle(typeNode, annotationNode);
        }

        if (entityType.getName().equals(DbEntity.class.getName())) {
            String interfaceName = entityType.getName();
            addImplements(typeDecl, interfaceName);
            addImplements(typeDecl, Serializable.class.getName());
            DbEntityInterfaceHandler.handle(typeNode, annotationNode);
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
            EclipseHandlerUtil.injectField(typeNode, decl);
        }
        // create pcCommand field
        if (!fieldExist(typeDecl.fields, StringUtil.getVariableName("pcCommand"))) {
            FieldDeclaration decl = new FieldDeclaration("pcCommand".toCharArray(), 0, 0);
            decl.modifiers = decl.modifiers | ClassFileConstants.AccPrivate;
            decl.type = EclipseAstUtil.createTypeReference(String.class.getSimpleName());
            EclipseHandlerUtil.injectField(typeNode, decl);

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
            EclipseHandlerUtil.injectField(typeNode, decl);
        }
    }

    private void addImplements(TypeDeclaration typeDecl, String interfaceName) {
        TypeReference[] implArr = null;
        TypeReference[] newImplArr = null;

        if (typeDecl.superInterfaces != null) {
            implArr = typeDecl.superInterfaces;
        }

        if (implArr != null && implArr.length > 0) {
            newImplArr = Arrays.copyOf(implArr, implArr.length+1);
            newImplArr[newImplArr.length - 1] = EclipseAstUtil.createTypeReference(interfaceName);
        } else {
            newImplArr = new TypeReference[1];
            newImplArr[0] = EclipseAstUtil.createTypeReference(interfaceName);
        }

        typeDecl.superInterfaces = newImplArr;
    }


    public static TypeDeclaration checkAnnotation(EclipseNode typeNode, EclipseNode annotationNode) {
        TypeDeclaration typeDecl = null;
        if (typeNode.get() instanceof TypeDeclaration) {
            typeDecl = (TypeDeclaration) typeNode.get();
        }
        int modifiers = typeDecl == null ? 0 : typeDecl.modifiers;
        boolean notAClass = (modifiers
                & (ClassFileConstants.AccInterface | ClassFileConstants.AccAnnotation | ClassFileConstants.AccEnum)) != 0;

        if (typeDecl == null || notAClass) {
            annotationNode.addError("@Implements is only supported on a class.");
            return null;
        }
        return typeDecl;
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

