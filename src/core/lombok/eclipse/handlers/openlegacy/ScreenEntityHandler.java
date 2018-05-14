package lombok.eclipse.handlers.openlegacy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.core.AST;
import lombok.core.AnnotationValues;
import lombok.core.handlers.HandlerUtil;
import lombok.eclipse.EclipseNode;
import lombok.eclipse.handlers.EclipseHandlerUtil;
import lombok.eclipse.handlers.EclipseHandlerUtil.*;
import lombok.eclipse.handlers.builders.FieldDeclarationBuilder;
import lombok.experimental.Accessors;
import openlegacy.utils.StringUtil;
import org.eclipse.jdt.internal.compiler.ast.FieldDeclaration;
import org.eclipse.jdt.internal.compiler.ast.TypeDeclaration;
import org.eclipse.jdt.internal.compiler.ast.TypeReference;
import org.openlegacy.core.annotations.screen.ScreenDescriptionField;
import org.openlegacy.core.terminal.TerminalField;
import org.openlegacy.core.terminal.TerminalSnapshot;
import org.openlegacy.core.terminal.definitions.TerminalActionDefinition;

import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

import static lombok.eclipse.handlers.EclipseHandlerUtil.*;
import static lombok.eclipse.handlers.openlegacy.EclipseHandlerUtil.*;
import static openlegacy.LombokOLConstants.*;

/**
 * @author Ivan Bort
 * @since 3.6.0-SNAPSHOT
 */
public class ScreenEntityHandler {

    /**
     * Main entry point, which will generate all required code
     */
    public static void handle(EclipseNode typeNode, boolean supportTerminalData, boolean screenEntity) {
        List<FieldDeclaration> newFields = new ArrayList<FieldDeclaration>();
        if (screenEntity) {
            addImplements(typeNode, org.openlegacy.core.terminal.ScreenEntity.class);
            createScreenEntityFields(typeNode, newFields, supportTerminalData);
        }
        createFieldBasedFields(typeNode, newFields, supportTerminalData);
        createFieldActions(typeNode, newFields);
        // add @XmlAccessorType(XmlAccessType.FIELD) to the class in order
        // to activate JAXB ignoring for metadata fields
        addXmlAccessorType(typeNode);
        // add new fields into the type declaration
        injectFields(typeNode, newFields);
    }

    /**
     * Creates predefined fields for not super entity class
     */
    private static void createScreenEntityFields(EclipseNode typeNode, List<FieldDeclaration> newFields, boolean supportTerminalData) {
        TypeDeclaration typeDecl = (TypeDeclaration) typeNode.get();
        //create TERMINAL_SNAPSHOT field
        if (supportTerminalData && !fieldExist(typeDecl.fields, TERMINAL_SNAPSHOT)) {
            FieldDeclaration decl = new FieldDeclarationBuilder(TERMINAL_SNAPSHOT)
                    .withModifiers(EclipseModifier.PRIVATE)
                    .withType(TerminalSnapshot.class)
                    .build();
            newFields.add(decl);
        }
        //create focusField field
        if (!fieldExist(typeDecl.fields, StringUtil.getVariableName(FOCUS_FIELD_NAME))) {
            FieldDeclaration decl = new FieldDeclarationBuilder(FOCUS_FIELD_NAME)
                    .withModifiers(EclipseModifier.PRIVATE)
                    .withType(String.class)
                    .appendAnnotation(JsonIgnore.class)
                    .appendAnnotation(XmlTransient.class)
                    .build();
            newFields.add(decl);
        }
        // create pcCommand field
        if (!fieldExist(typeDecl.fields, StringUtil.getVariableName(PC_COMMAND_FIELD_NAME))) {
            FieldDeclaration decl = new FieldDeclarationBuilder(PC_COMMAND_FIELD_NAME)
                    .withModifiers(EclipseModifier.PRIVATE)
                    .withType(String.class)
                    .appendAnnotation(JsonIgnore.class)
                    .appendAnnotation(XmlTransient.class)
                    .build();
            newFields.add(decl);
        }
        //create actions field
        if (!fieldExist(typeDecl.fields, StringUtil.getVariableName(ACTIONS_FIELD_NAME))) {
            FieldDeclaration decl = new FieldDeclarationBuilder(ACTIONS_FIELD_NAME)
                    .withModifiers(EclipseModifier.PRIVATE)
                    .withType(List.class)
                    .withDiamondsType(TerminalActionDefinition.class)
                    .withInitialization(ArrayList.class)
                    .withDiamondsInitialization(TerminalActionDefinition.class)
                    .appendAnnotation(JsonIgnore.class)
                    .appendAnnotation(XmlTransient.class)
                    .build();
            newFields.add(decl);
        }
    }

    /**
     * Creates fields based on declared fields inside the entity class. Possible suffixes: *Field, *Values, *Description, *Actions
     */
    private static void createFieldBasedFields(EclipseNode typeNode, List<FieldDeclaration> newFields, boolean supportTerminalData) {
        TypeDeclaration typeDecl = (TypeDeclaration) typeNode.get();

        // walk through all fields annotated with @ScreenField
        for (EclipseNode fieldNode : findAllScreenFields(typeNode)) {
            FieldDeclaration field = (FieldDeclaration) fieldNode.get();
            //******
            //try to create "private TerminalField *Field" if getter for it doesn't exist & satisfy other conditions
            String nameWithFieldSuffix = fieldNode.getName() + FIELD_SUFFIX;
            TypeReference fieldType = copyType(field.type, field);
            boolean isBoolean = isBoolean(fieldType);
            String getterName = HandlerUtil.toGetterName(fieldNode.getAst(), AnnotationValues.of(Accessors.class, fieldNode),
                    nameWithFieldSuffix, isBoolean);
            boolean primitive = isPrimitive(fieldType);
            if (MemberExistsResult.NOT_EXISTS.equals(EclipseHandlerUtil.methodExists(getterName, typeNode, -1))
                    && supportTerminalData && primitive && !fieldExist(typeDecl.fields, nameWithFieldSuffix)) {
                FieldDeclaration decl = new FieldDeclarationBuilder(nameWithFieldSuffix)
                        .withModifiers(EclipseModifier.PRIVATE)
                        .withType(TerminalField.class)
                        .build();

                newFields.add(decl);
            }

            //*****
            //try to create "private String *Description"
            if (EclipseHandlerUtil.hasAnnotation(ScreenDescriptionField.class, fieldNode) && !fieldExist(typeDecl.fields, fieldNode.getName() + DESCRIPTION_SUFFIX)) {

                FieldDeclaration decl = new FieldDeclarationBuilder(fieldNode.getName())
                        .withNameSuffix(DESCRIPTION_SUFFIX)
                        .withModifiers(EclipseModifier.PRIVATE)
                        .withType(String.class)
                        .appendAnnotation(JsonIgnore.class)
                        .appendAnnotation(XmlTransient.class)
                        .build();

                newFields.add(decl);
            }

            //*****
            //try to create "private List<TerminalActionDefinition> *Actions = new ArrayList<TerminalActionDefinition>()"
            //if field type is List
        }
    }

    private static void createFieldActions(EclipseNode typeNode, List<FieldDeclaration> newFields) {
        TypeDeclaration typeDecl = (TypeDeclaration) typeNode.get();

        for (EclipseNode fieldNode : findAllFields(typeNode)) {
            FieldDeclaration field = (FieldDeclaration) fieldNode.get();
            TypeReference fieldType = copyType(field.type, field);
            String fieldTypeName = fieldType.toString();

            if (fieldTypeName.contains("List") && !fieldTypeName.contains("actions")
                    && !fieldExist(typeDecl.fields, fieldNode.getName() + ACTIONS_SUFFIX)
                    && hasScreenTableClass(typeNode, fieldTypeName)) {

                FieldDeclaration decl = new FieldDeclarationBuilder(fieldNode.getName())
                        .withNameSuffix(ACTIONS_SUFFIX)
                        .withModifiers(EclipseModifier.PRIVATE)
                        .withType(List.class)
                        .withDiamondsType(TerminalActionDefinition.class)
                        .withInitialization(ArrayList.class)
                        .withDiamondsInitialization(TerminalActionDefinition.class)
                        .appendAnnotation(JsonIgnore.class)
                        .appendAnnotation(XmlTransient.class)
                        .build();

                newFields.add(decl);
            }
        }
    }

    private static boolean hasScreenTableClass(EclipseNode typeNode, String varType) {
        java.util.List<EclipseNode> innerClasses = findAllInnerClasses(typeNode);
        for(EclipseNode type : innerClasses){
            String typeName =  type.getName();
            if(varType.contains(typeName)){
                return true;
            }
        }
        return false;
    }

    private static List<EclipseNode> findAllInnerClasses(EclipseNode typeNode) {
        java.util.List<EclipseNode> fields = new java.util.ArrayList<EclipseNode>();
        for (EclipseNode child : typeNode.down()) {
            if (child.getKind() == AST.Kind.TYPE) {
                fields.add(child);
            }
        }
        return fields;
    }

    private static java.util.List<EclipseNode> findAllFields(EclipseNode typeNode) {
        java.util.List<EclipseNode> fields = new java.util.ArrayList<EclipseNode>();
        for (EclipseNode child : typeNode.down()) {
            if (child.getKind() == AST.Kind.FIELD) {
                fields.add(child);
            }
        }
        return fields;
    }

    /**
     * returns true in the case passed typeReference is a representation of OL primitive types that are not inner or external class types
     */
    private static void injectFields(EclipseNode typeNode, List<FieldDeclaration> fields) {
        for (FieldDeclaration field : fields)
            EclipseHandlerUtil.injectField(typeNode, field);
    }

}
