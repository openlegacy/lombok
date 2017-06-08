package lombok.eclipse.handlers.openlegacy;

import lombok.core.AnnotationValues;
import lombok.core.handlers.HandlerUtil;
import lombok.eclipse.EclipseNode;
import lombok.eclipse.handlers.builders.FieldDeclarationBuilder;
import lombok.experimental.Accessors;
import org.eclipse.jdt.internal.compiler.ast.FieldDeclaration;
import org.eclipse.jdt.internal.compiler.ast.TypeDeclaration;
import org.eclipse.jdt.internal.compiler.ast.TypeReference;
import org.openlegacy.core.terminal.TerminalField;

import static lombok.eclipse.handlers.EclipseHandlerUtil.*;
import static lombok.eclipse.handlers.openlegacy.EclipseHandlerUtil.*;

/**
 * @author Matvey Mitnitsky on 01-Jun-17.
 */
public class ScreenTableAnnotationHandler {

    private static final String FOCUS_FIELD_NAME = "focusField";

    public static void handle(EclipseNode typeNode, boolean supportTerminalData) {
        createScreenTableFields(typeNode);
        if (supportTerminalData) createTerminalFields(typeNode);
    }

    private static void createScreenTableFields(EclipseNode typeNode) {
        TypeDeclaration typeDecl = (TypeDeclaration) typeNode.get();

        if (!fieldExist(typeDecl.fields, FOCUS_FIELD_NAME)) {
            FieldDeclaration focusFieldDecl = new FieldDeclarationBuilder(FOCUS_FIELD_NAME)
                    .withModifiers(EclipseModifier.PRIVATE)
                    .withType(String.class)
                    .build();

            injectField(typeNode, focusFieldDecl);
        }
    }

    private static void createTerminalFields(EclipseNode typeNode) {
        TypeDeclaration typeDeclaration = (TypeDeclaration) typeNode.get();

        for (EclipseNode fieldNode : findAllScreenFields(typeNode)) {
            FieldDeclaration field = (FieldDeclaration) fieldNode.get();
            //******
            //try to create "private TerminalField *Field" if getter for it doesn't exist & satisfy other conditions
            String nameWithFieldSuffix = fieldNode.getName() + "Field";
            TypeReference fieldType = copyType(field.type, field);
            boolean isBoolean = isBoolean(fieldType);
            String getterName = HandlerUtil.toGetterName(fieldNode.getAst(), AnnotationValues.of(Accessors.class, fieldNode),
                    nameWithFieldSuffix, isBoolean);
            if (MemberExistsResult.NOT_EXISTS.equals(lombok.eclipse.handlers.EclipseHandlerUtil.methodExists(getterName, typeNode, -1))
                    && isPrimitive(fieldType) && !fieldExist(typeDeclaration.fields, nameWithFieldSuffix)) {
                FieldDeclaration decl = new FieldDeclarationBuilder(nameWithFieldSuffix)
                        .withModifiers(EclipseModifier.PRIVATE)
                        .withType(TerminalField.class)
                        .build();

                injectField(typeNode, decl);
            }

        }
    }
}
