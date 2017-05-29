package lombok.javac.handlers.builders;

import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.List;
import lombok.javac.JavacNode;
import lombok.javac.JavacTreeMaker;
import lombok.javac.handlers.openlegacy.JavacPrimitives;
import openlegacy.utils.StringUtil;
import org.eclipse.jdt.internal.compiler.ast.FieldDeclaration;

import static lombok.javac.handlers.JavacOLUtil.*;

/**
 * @author Matvey Mitnitsky on 24-May-17.
 */
public class FieldDeclBuilder implements JavacTreeBuilder<JCTree.JCVariableDecl> {

    private final JavacNode typeNode;
    private final JavacTreeMaker treeMaker;

    private String name;
    private JCTree.JCModifiers modifiers;
    private JCTree.JCExpression definition;
    private JCTree.JCExpression initialization;
    private List<JCTree.JCAnnotation> annotations = List.<JCTree.JCAnnotation>nil();

    public FieldDeclBuilder(JavacNode typeNode, String name) {
        this.typeNode = typeNode;
        this.treeMaker = typeNode.getTreeMaker();
        this.name = name;
    }

    /**
     * Builds an object of JCVariableDecl
     *
     * @return {@link com.sun.tools.javac.tree.JCTree.JCVariableDecl}
     */
    @Override
    public JCTree.JCVariableDecl build() {
        JCTree.JCVariableDecl variableDecl = treeMaker.VarDef(
                modifiers,
                typeNode.toName(name),
                definition,
                initialization
        );

        variableDecl.getModifiers().annotations = annotations;
        return variableDecl;
    }

    /**
     * Appends String suffix to the field name
     *
     * @param suffix String
     * @return builder instance
     */
    public FieldDeclBuilder withSuffix(String suffix) {
        this.name += suffix;
        return this;
    }

    /**
     * Prepends String prefix to the field name
     *
     * @param prefix String
     * @return builder instance
     */
    public FieldDeclBuilder withPrefix(String prefix) {
        String nameWithUpperFirstChar = StringUtil.firstCharToUpperCase(name.toCharArray());
        name = prefix + nameWithUpperFirstChar;
        return this;
    }

    /**
     * Set type of Class to the field
     *
     * @param type {@link Class}
     * @return builder instance
     */
    public FieldDeclBuilder withType(Class<?> type) {
        definition = getJCExpressionForType(typeNode, type);
        return this;
    }

    /**
     * Set diamonds type to the field declaration
     *
     * @param type         {@link Class} is the base type
     * @param diamondTypes {@link Class} array of types to be set to the diamonds
     * @return
     */
    public FieldDeclBuilder withDiamondsType(Class<?> type, Class<?>... diamondTypes) {
        List<JCTree.JCExpression> diamondTypeExpressions = List.<JCTree.JCExpression>nil();
        for (Class dtype : diamondTypes)
            diamondTypeExpressions = diamondTypeExpressions.append(getJCExpressionForType(typeNode, dtype));
        definition = treeMaker.TypeApply(
                getJCExpressionForType(typeNode, type), diamondTypeExpressions
        );
        return this;
    }


    /**
     * Set modifiers (e.g. public, private, synchronized, volatile) to the field declaration
     *
     * @param modifiers long. Use {@link com.sun.tools.javac.code.Flags} enum in order to pass right values
     * @return builder instance
     */
    public FieldDeclBuilder withModifiers(long... modifiers) {
        long mod = modifiers[0];
        for (int i = 1; i < modifiers.length; i++)
            mod |= modifiers[i];
        this.modifiers = treeMaker.Modifiers(mod);
        return this;
    }


    /**
     * Set an initialization of type initializationType to the field declaration
     *
     * @param initializationType Class
     * @return builder instance
     */
    public FieldDeclBuilder withInitialization(Class<?> initializationType) {
        initialization = treeMaker.NewClass(
                null, List.<JCTree.JCExpression>nil(),
                getJCExpressionForType(typeNode, initializationType),
                List.<JCTree.JCExpression>nil(),
                null
        );
        return this;
    }

    /**
     * Set diamonds type to the field initialization
     *
     * @param initializationType {@link Class} is the base type
     * @param diamondTypes       {@link Class} array of types to be set to the diamonds
     * @return builder instance
     */
    public FieldDeclBuilder withDiamondsInitialization(Class<?> initializationType, Class<?>... diamondTypes) {
        List<JCTree.JCExpression> typeArgs = List.<JCTree.JCExpression>nil();
        for (Class dtype : diamondTypes)
            typeArgs = typeArgs.append(getJCExpressionForType(typeNode, dtype));
        JCTree.JCTypeApply typeApply = treeMaker.TypeApply(
                getJCExpressionForType(typeNode, initializationType),
                typeArgs);
        initialization = treeMaker.NewClass(
                null, List.<JCTree.JCExpression>nil(),
                typeApply,
                List.<JCTree.JCExpression>nil(),
                null
        );
        return this;
    }

    /**
     * set annotations without arguments to the Field Declaration
     */
    public FieldDeclBuilder setAnnotations(Class<?>... annotations) {

        List<JCTree.JCAnnotation> fieldAnnotations = List.<JCTree.JCAnnotation>nil();
        // empty args list
        List<JCTree.JCExpression> fieldArgs = List.<JCTree.JCExpression>nil();
        for (Class annotation : annotations)
            fieldAnnotations = fieldAnnotations.append(treeMaker.Annotation(getJCExpressionForType(typeNode, annotation), fieldArgs));
        this.annotations = fieldAnnotations;
        return this;
    }

    /**
     * set primitive type to Field Definition
     */
    public FieldDeclBuilder withPrimitiveType(JavacPrimitives primitiveType) {
        definition = treeMaker.TypeIdent(primitiveType.typeTag);
        return this;
    }

    /**
     * set literal initialization to the Field
     */
    public FieldDeclBuilder withLiteralInit(JavacPrimitives literalType, Object literal) {
        initialization = treeMaker.Literal(literalType.typeTag, literal);
        return this;
    }
}
