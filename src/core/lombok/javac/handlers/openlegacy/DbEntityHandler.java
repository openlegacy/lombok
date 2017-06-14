package lombok.javac.handlers.openlegacy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.JCAnnotation;
import com.sun.tools.javac.tree.JCTree.JCClassDecl;
import com.sun.tools.javac.tree.JCTree.JCExpression;
import com.sun.tools.javac.tree.JCTree.JCModifiers;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import com.sun.tools.javac.util.List;
import lombok.core.AST;
import lombok.javac.JavacNode;
import lombok.javac.JavacTreeMaker;
import lombok.javac.handlers.JavacHandlerUtil;
import lombok.javac.handlers.JavacOLUtil;
import lombok.javac.handlers.builders.AnnotationBuilder;
import lombok.javac.handlers.builders.ClassDeclarationBuilder;
import lombok.javac.handlers.builders.FieldDeclBuilder;
import openlegacy.utils.StringUtil;
import org.openlegacy.core.db.DbEntity;
import org.openlegacy.core.db.definitions.DbActionDefinition;

import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Transient;
import java.io.Serializable;

import static lombok.javac.handlers.OLJavacHandlerUtil.*;
import static openlegacy.LombokOLConstants.*;

/**
 * @author Matvey Mitnitsky
 * @since 3.6.0-SNAPSHOT
 */
public class DbEntityHandler {

    public static void handle(JavacNode typeNode) {

        addImplements(typeNode, DbEntity.class, Serializable.class);
        createDbEntityEntityFields(typeNode);

        //TODO add if exists condition
        if (hasMultipleId(typeNode)) {
            createCompositeKey(typeNode);
        }
    }

    /**
     * generates actions and serialVersionUID for typeNode
     */
    private static void createDbEntityEntityFields(JavacNode typeNode) {
        JavacTreeMaker jcMaker = typeNode.getTreeMaker();

        if (!fieldExist(typeNode, StringUtil.getVariableName(ACTIONS_FIELD_NAME))) {

            JCVariableDecl actionsDeclaration = new FieldDeclBuilder(typeNode, ACTIONS_FIELD_NAME)
                    .withModifiers(Flags.PRIVATE)
                    .withDiamondsType(java.util.List.class, DbActionDefinition.class)
                    .withDiamondsInitialization(
                            java.util.ArrayList.class, DbActionDefinition.class
                    )
                    .setAnnotations(Transient.class)
                    .build();

            JavacHandlerUtil.injectField(typeNode, actionsDeclaration);
        }

        if (!fieldExist(typeNode, StringUtil.getVariableName(SERIAL_VERSION_FIELD_NAME)))
            JavacHandlerUtil.injectField(typeNode, createSerialVersionUID(typeNode));
    }

    /**
     * The method creates composite Key nested class for typeNode
     */
    private static void createCompositeKey(JavacNode typeNode) {
        JavacTreeMaker treeMaker = typeNode.getTreeMaker();

        String className = ((JCClassDecl) typeNode.get()).name.toString();
        String idClassNameWithSuffix = className + COMPOSITE_KEY_SUFFIX;
        String qualifiedName = className + "." + idClassNameWithSuffix;

        if(!classExists(typeNode, idClassNameWithSuffix)) {

            //List of definitions inside the new nested class (serialVersionUID is added)
            List<JCTree> classDefs = List.<JCTree>nil().append(createSerialVersionUID(typeNode));

        /* Remove @Id annotation in the loop */
            classDefs = classDefs.appendList(removeAnnotationsForIdFields(typeNode));

            //Declaration of the new nested class
            JCClassDecl idClassDecl = new ClassDeclarationBuilder(typeNode, idClassNameWithSuffix)
                    .withModifiers(Flags.PUBLIC, Flags.STATIC)
                    .appendImplements(Serializable.class)
                    .setVariableDecl(classDefs)
                    .build();

            // inject new Type inside enclosing entity
            JavacNode idClassTypeNode = JavacHandlerUtil.injectType(typeNode, idClassDecl);

            //generate getters and setters for nested class
            generateLombokData(idClassTypeNode, idClassTypeNode.up());
        }

        createIdClassAnnotation(typeNode, qualifiedName);

    }

    /**
     * The method creates IdClass Annotation over typeNode
     */
    private static void createIdClassAnnotation(JavacNode typeNode, String idClassName) {
        if (JavacHandlerUtil.hasAnnotation(IdClass.class, typeNode))
            return;
        
        JCModifiers modifiers = ((JCClassDecl) typeNode.get()).getModifiers();

        List<JCAnnotation> annotations = modifiers.annotations;
        
        modifiers.annotations = annotations.append(
                new AnnotationBuilder(typeNode, IdClass.class)
                .appendClassLiteralArgument("value", idClassName)
                .build()
        );
    }

    //checks for more then one Id in the entity class
    private static boolean hasMultipleId(JavacNode typeNode) {
        java.util.List<JavacNode> idNodes = findIdFields(typeNode);
        return idNodes.size() > 1;
    }

    //return List of JavacNodes which have @Id annotation
    private static java.util.List<JavacNode> findIdFields(JavacNode typeNode) {
        java.util.List<JavacNode> fields = new java.util.ArrayList<JavacNode>();
        for (JavacNode child : typeNode.down()) {
            if (child.getKind() == AST.Kind.FIELD && JavacHandlerUtil.hasAnnotation(Id.class, child))
                fields.add(child);
        }
        return fields;
    }

    /**
     * creates serialVersionUID field with literal initialization
     */
    private static JCVariableDecl createSerialVersionUID(JavacNode typeNode) {

        return new FieldDeclBuilder(typeNode, SERIAL_VERSION_FIELD_NAME)
                .withModifiers(Flags.PRIVATE, Flags.FINAL, Flags.STATIC)
                .withPrimitiveType(JavacPrimitives.LONG)
                .withLiteralInit(JavacPrimitives.LONG, 1L)
                .build();
    }

    /**
     * the method removes annotations and returns List with field declarations to be set to the composite class
     */
    private static List<JCTree> removeAnnotationsForIdFields(JavacNode typeNode) {
        JavacTreeMaker treeMaker = typeNode.getTreeMaker();
        List<JCTree> fieldDeclarations = List.<JCTree>nil();

        java.util.List<JavacNode> idNodes = findIdFields(typeNode);
        for (JavacNode idNode : idNodes) {
            for (JavacNode idChild : idNode.down()) {
                if (idChild.getKind() == AST.Kind.ANNOTATION) {
                    Type annotationType = ((JCAnnotation) idChild.get()).type;
                    String annotationName = annotationType.tsym.toString();

                    if (!annotationName.trim().isEmpty() && annotationName.equals(Id.class.getName())) {
                        JCVariableDecl idNodeDecl = ((JCVariableDecl) idNode.get());
                        // Create copy of Variable declaration to avoid influence on the enclosing entity class fields
                        JCVariableDecl idNodeCopy = copyVariableDeclaration(treeMaker, idNodeDecl);
                        fieldDeclarations = fieldDeclarations.append(idNodeCopy);
                    }
                }
            }
        }

        return fieldDeclarations;
    }

    private static JCVariableDecl copyVariableDeclaration(JavacTreeMaker treeMaker, JCVariableDecl input) {
        return treeMaker.VarDef(
                treeMaker.Modifiers(Flags.PRIVATE),
                input.name,
                input.vartype,
                input.init
        );
    }

}
