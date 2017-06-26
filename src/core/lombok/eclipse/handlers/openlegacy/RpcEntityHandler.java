package lombok.eclipse.handlers.openlegacy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.core.AST;
import lombok.eclipse.EclipseNode;
import lombok.eclipse.handlers.builders.AnnotationBuilder;
import lombok.eclipse.handlers.builders.FieldDeclarationBuilder;
import openlegacy.utils.StringUtil;
import org.eclipse.jdt.internal.compiler.ast.FieldDeclaration;
import org.eclipse.jdt.internal.compiler.ast.NormalAnnotation;
import org.eclipse.jdt.internal.compiler.ast.TypeDeclaration;
import org.eclipse.jdt.internal.compiler.ast.TypeReference;
import org.openlegacy.core.annotations.rpc.RpcField;
import org.openlegacy.core.annotations.rpc.RpcList;
import org.openlegacy.core.definitions.RpcActionDefinition;
import org.openlegacy.core.rpc.RpcEntity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

import static lombok.eclipse.handlers.EclipseHandlerUtil.*;
import static lombok.eclipse.handlers.openlegacy.EclipseHandlerUtil.*;
import static openlegacy.LombokOLConstants.*;

/**
 * @author Matvey Mitnitsky
 * @since 3.6.0-SNAPSHOT
 */
public class RpcEntityHandler {


    public static void handle(EclipseNode typeNode, boolean rpcEntity) {

        if (rpcEntity) {
            addImplements(typeNode, RpcEntity.class);
        }
        createRpcEntityFields(typeNode);
        createFieldActions(typeNode);
        // add @XmlAccessorType(XmlAccessType.FIELD) to the class in order
        // to activate JAXB ignoring for metadata fields
        addXmlAccessorType(typeNode);
    }

    private static void createRpcEntityFields(EclipseNode typeNode) {
        TypeDeclaration typeDecl = (TypeDeclaration) typeNode.get();

        if (!fieldExist(typeDecl.fields, StringUtil.getVariableName(ACTIONS_FIELD_NAME))) {
            FieldDeclaration decl = new FieldDeclarationBuilder(ACTIONS_FIELD_NAME)
                    .withModifiers(EclipseModifier.PRIVATE)
                    .withType(List.class)
                    .withDiamondsType(RpcActionDefinition.class)
                    .withInitialization(ArrayList.class)
                    .withDiamondsInitialization(RpcActionDefinition.class)
                    .appendAnnotation(JsonIgnore.class)
                    .appendAnnotation(XmlTransient.class)
                    .build();

            injectField(typeNode, decl);
        }
    }

    private static void createFieldActions(EclipseNode typeNode) {
        TypeDeclaration typeDeclaration = (TypeDeclaration) typeNode.get();

        for (EclipseNode fieldNode : findAllRpcFields(typeNode)) {
            String fieldNameWithActionsSuffix = fieldNode.getName() + ACTIONS_SUFFIX;
            FieldDeclaration field = (FieldDeclaration) fieldNode.get();

            TypeReference fieldType = copyType(field.type, field);
            char[][] typeChar = fieldType.getTypeName();
            String typeName = getTypeNameFromCharArray(typeChar);

            if (typeName.contains("List")) {
                if (fieldNode.getName().contains(ACTIONS_FIELD_NAME) || fieldExist(typeDeclaration.fields, fieldNameWithActionsSuffix))
                    continue;

                FieldDeclaration fieldDeclaration = new FieldDeclarationBuilder(fieldNameWithActionsSuffix)
                        .withModifiers(EclipseModifier.PRIVATE)
                        .withType(List.class)
                        .withDiamondsType(RpcActionDefinition.class)
                        .withInitialization(ArrayList.class)
                        .withDiamondsInitialization(RpcActionDefinition.class)
                        .appendAnnotation(JsonIgnore.class)
                        .appendAnnotation(XmlTransient.class)
                        .build();

                injectField(typeNode, fieldDeclaration);
            }
        }
    }

    private static List<EclipseNode> findAllRpcFields(EclipseNode typeNode) {
        List<EclipseNode> fields = new ArrayList<EclipseNode>();
        for (EclipseNode child : typeNode.down()) {
            if (child.getKind() == AST.Kind.FIELD && hasAnnotation(RpcList.class, child))
                fields.add(child);
        }

        return fields;
    }

}
