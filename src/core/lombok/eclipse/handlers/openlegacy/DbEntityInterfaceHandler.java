package lombok.eclipse.handlers.openlegacy;

import lombok.core.AST;
import lombok.eclipse.EclipseNode;
import lombok.eclipse.handlers.EclipseHandlerUtil;
import lombok.eclipse.handlers.builders.AnnotationBuilder;
import lombok.eclipse.handlers.builders.FieldDeclarationBuilder;
import lombok.eclipse.handlers.builders.TypeDeclarationBuilder;
import openlegacy.utils.StringUtil;
import org.eclipse.jdt.internal.compiler.ast.Annotation;
import org.eclipse.jdt.internal.compiler.ast.FieldDeclaration;
import org.eclipse.jdt.internal.compiler.ast.NormalAnnotation;
import org.eclipse.jdt.internal.compiler.ast.TypeDeclaration;
import org.openlegacy.core.db.DbEntity;
import org.openlegacy.core.db.definitions.DbActionDefinition;

import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static lombok.eclipse.handlers.EclipseHandlerUtil.*;
import static lombok.eclipse.handlers.openlegacy.EclipseHandlerUtil.*;

/**
 * @author Matvey Mitnitsky
 * @since 3.6.0-SNAPSHOT
 */
public class DbEntityInterfaceHandler {

    public static void handle(EclipseNode typeNode) {

        addImplements(typeNode, DbEntity.class, Serializable.class);
        createDbEntityFields(typeNode);

        if (hasMultipleId(typeNode)) {
            createCompositeKey(typeNode);
        }
    }

    private static void createDbEntityFields(EclipseNode typeNode) {
        TypeDeclaration typeDecl = (TypeDeclaration) typeNode.get();

        if (!fieldExist(typeDecl.fields, StringUtil.getVariableName("actions"))) {
            //TODO add Transient and JsonIgnore annotation
            FieldDeclaration decl = new FieldDeclarationBuilder("actions")
                    .withModifiers(EclipseModifier.PRIVATE)
                    .withType(List.class)
                    .withDiamondsType(DbActionDefinition.class)
                    .withInitialization(ArrayList.class)
                    .withDiamondsInitialization(DbActionDefinition.class)
                    .appendAnnotation(Transient.class)
                    .build();
            EclipseHandlerUtil.injectField(typeNode, decl);
        }

        if (!fieldExist(typeDecl.fields, StringUtil.getVariableName("serialVersionUID"))) {
            FieldDeclaration serialVersion = createSerialVersionUID();
            EclipseHandlerUtil.injectField(typeNode, serialVersion);
        }
    }

    private static void createCompositeKey(EclipseNode typeNode) {
        TypeDeclaration typeDeclaration = (TypeDeclaration) typeNode.get();
        String className = String.valueOf(typeDeclaration.name);
        String idClassNameWithSuffix = className + "CompositeKey";
        String qualifiedName = className + "." + idClassNameWithSuffix;

        if (!classExists(typeNode, idClassNameWithSuffix)) {

            //List of definitions inside the new nested class (serialVersionUID is added)
            List<FieldDeclaration> variables = new ArrayList<FieldDeclaration>();
            variables.add(createSerialVersionUID());

        /* Remove @Id annotation in the loop and add to variables list*/
            variables.addAll(removeAnnotationsForIdFields(typeNode));

            //Declaration of the new nested class
            TypeDeclaration idClassTypeDecl = new TypeDeclarationBuilder(idClassNameWithSuffix, typeDeclaration)
                    .withModifiers(EclipseModifier.PUBLIC, EclipseModifier.STATIC)
                    .withImplements(Serializable.class)
                    .setFieldDeclarations(getFieldDeclarationArr(variables))
                    .build();

            // inject new Type inside enclosing entity
            EclipseNode idClassTypeNode = EclipseHandlerUtil.injectType(typeNode, idClassTypeDecl);
//
            //generate lombok data for nested class
            generateLombokData(idClassTypeNode, idClassTypeNode.up());

        }

        createIdClassAnnotation(typeNode, qualifiedName);
    }

    private static void createIdClassAnnotation(EclipseNode typeNode, String idClassQualifiedName) {
        if (hasAnnotation(IdClass.class, typeNode))
            return;
        TypeDeclaration typeDeclaration = (TypeDeclaration) typeNode.get();
        NormalAnnotation annotation = new AnnotationBuilder(IdClass.class)
                .appendArgumentWithClassLiteralValue("value", idClassQualifiedName)
                .build();
        typeDeclaration.annotations = appendAnnotation(annotation, typeDeclaration.annotations);
    }

    private static boolean hasMultipleId(EclipseNode typeNode) {
        List<EclipseNode> idFields = findIdFields(typeNode);
        return idFields.size() > 1;
    }

    private static List<EclipseNode> findIdFields(EclipseNode typeNode) {
        List<EclipseNode> idFields = new ArrayList<EclipseNode>();
        for (EclipseNode child : typeNode.down())
            if (child.getKind() == AST.Kind.FIELD && hasAnnotation(Id.class, child))
                idFields.add(child);
        return idFields;
    }

    private static List<FieldDeclaration> removeAnnotationsForIdFields(EclipseNode typeNode) {
        List<EclipseNode> idFields = findIdFields(typeNode);
        List<FieldDeclaration> result = new ArrayList<FieldDeclaration>();
        for (EclipseNode idField : idFields) {
            for (EclipseNode idChild : idField.down()) {
                if (idChild.getKind() == AST.Kind.ANNOTATION) {
                    Annotation annotation = (Annotation) idChild.get();
                    String typeName = annotation.type.toString();
                    if (typeName.equals(Id.class.getName()) || typeName.equals(Id.class.getSimpleName())) {
                        FieldDeclaration originalDecl = (FieldDeclaration) idField.get();
                        FieldDeclaration declCopy = copyFieldDeclAndRemoveAnnotations(originalDecl);
                        result.add(declCopy);
                    }
                }
            }
        }
        return result;
    }

    private static FieldDeclaration copyFieldDeclAndRemoveAnnotations(FieldDeclaration originalDecl) {
        FieldDeclaration copy = new FieldDeclaration();
        Annotation[] annotationsCopy = new Annotation[0];
        copy.modifiers = originalDecl.modifiers;
        copy.initialization = originalDecl.initialization;
        copy.type = originalDecl.type;
        copy.annotations = annotationsCopy;
        copy.name = originalDecl.name;
        return copy;
    }

    private static FieldDeclaration[] getFieldDeclarationArr(List<FieldDeclaration> variables) {
        FieldDeclaration[] fieldDeclarations = new FieldDeclaration[variables.size()];
        int i = 0;
        for (FieldDeclaration fd : variables)
            fieldDeclarations[i++] = fd;
        return fieldDeclarations;
    }

    private static FieldDeclaration createSerialVersionUID() {
        return new FieldDeclarationBuilder("serialVersionUID")
                .withModifiers(EclipseModifier.PRIVATE, EclipseModifier.FINAL, EclipseModifier.STATIC)
                .withPrimitiveType(EclipsePrimitives.LONG)
                .withNumberLiteralInitialization(EclipsePrimitives.LONG, "1L")
                .build();
    }

}
