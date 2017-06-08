package lombok.eclipse.handlers.builders;

import lombok.eclipse.handlers.openlegacy.EclipseHandlerUtil;
import lombok.eclipse.handlers.openlegacy.EclipseModifier;
import lombok.eclipse.handlers.openlegacy.EclipsePrimitives;
import openlegacy.utils.EclipseAstUtil;
import org.eclipse.jdt.internal.compiler.ast.AllocationExpression;
import org.eclipse.jdt.internal.compiler.ast.CharLiteral;
import org.eclipse.jdt.internal.compiler.ast.DoubleLiteral;
import org.eclipse.jdt.internal.compiler.ast.FalseLiteral;
import org.eclipse.jdt.internal.compiler.ast.FieldDeclaration;
import org.eclipse.jdt.internal.compiler.ast.FloatLiteral;
import org.eclipse.jdt.internal.compiler.ast.IntLiteral;
import org.eclipse.jdt.internal.compiler.ast.LongLiteral;
import org.eclipse.jdt.internal.compiler.ast.NormalAnnotation;
import org.eclipse.jdt.internal.compiler.ast.TrueLiteral;
import org.eclipse.jdt.internal.compiler.ast.TypeReference;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFileConstants;

import static lombok.eclipse.handlers.openlegacy.EclipseHandlerUtil.addModifiers;
import static openlegacy.utils.EclipseAstUtil.*;
import static openlegacy.utils.StringUtil.prepend;

/**
 * @author Matvey Mitnitsky on 21-May-17.
 */
public class FieldDeclarationBuilder implements EclipseTreeBuilder<FieldDeclaration> {

    private FieldDeclaration fieldDeclaration;

    public FieldDeclarationBuilder(String fieldName) {
        fieldDeclaration = new FieldDeclaration(fieldName.toCharArray(), 0, 0);
    }

    /**
     * Builds an object of FieldDeclaration type
     *
     * @return {@link FieldDeclaration}
     */
    @Override
    public FieldDeclaration build() {
        return fieldDeclaration;
    }

    /**
     * Adds modifiers to the new Field Declaration
     *
     * @param modifiers {@link EclipseModifier} (e.g. public, private, final, static, synchronized)
     * @return reference to builder object
     */
    public FieldDeclarationBuilder withModifiers(EclipseModifier... modifiers) {
        addModifiers(fieldDeclaration, modifiers);
        return this;
    }

    /**
     * Adds modifiers to the new Field Declaration
     *
     * @param modifiers accepts public properties of interface {@link ClassFileConstants} (e.g. AccPublic, AccPrivate)
     * @return reference to builder object
     */
    public FieldDeclarationBuilder withModifiers(int... modifiers) {
        addModifiers(fieldDeclaration, modifiers);
        return this;
    }

    /**
     * Set TypeReference type representation object to the field declaration
     *
     * @param fieldType Class instance of Java type that to be set to the new field
     * @return reference to builder object
     */
    public FieldDeclarationBuilder withType(Class<?> fieldType) {
        fieldDeclaration.type = EclipseAstUtil.createTypeReference(fieldType.getName());
        return this;
    }

    /**
     * Set TypeReference type representation object to the field declaration
     *
     * @param primitiveType {@link EclipsePrimitives}is primitive type that to be set to the new field
     * @return reference to builder object
     */
    public FieldDeclarationBuilder withPrimitiveType(EclipsePrimitives primitiveType) {
        fieldDeclaration.type = EclipseAstUtil.createTypeReference(primitiveType.keyword);
        return this;
    }

    /**
     * Set suffix to the new Fileds' Declaration name.
     *
     * @param suffix
     * @return
     */
    public FieldDeclarationBuilder withNameSuffix(String suffix) {
        String fieldNameWithSuffix = String.valueOf(fieldDeclaration.name) + suffix;
        fieldDeclaration.name = fieldNameWithSuffix.toCharArray();
        return this;
    }

    /**
     * Set prefix to the new Fileds' Declaration name.
     *
     * @param prefix
     * @return
     */
    public FieldDeclarationBuilder withNamePrefix(String prefix) {
        fieldDeclaration.name = prepend(fieldDeclaration.name, prefix);
        return this;
    }

    /**
     * Set TypeReferences to the new Fields' Declaration, so the new field will have type references
     * in the diamonds (e.g. List<String>, Map<String, Object>).
     *
     * @param diamondsType could be any Class objects. Appropriate Type  will be set to the diamonds.
     * @return reference to builder object
     */
    public FieldDeclarationBuilder withDiamondsType(Class<?> diamondsType) {
        TypeReference typeReference = fieldDeclaration.type;
        TypeReference diamondsTypeRef = EclipseAstUtil.createTypeReference(diamondsType.getName());
        fieldDeclaration.type = EclipseAstUtil.createParametrizedTypeReference(
                typeReference.toString(), diamondsTypeRef, 1
        );
        return this;
    }

    /**
     * Create an allocation expression (e.g. {@code = new String();})
     * Type of constructor is the same as Reference type
     *
     * @return reference to builder object
     */
    public FieldDeclarationBuilder withInitialization() {
        fieldDeclaration.initialization = new AllocationExpression();
        return this;
    }

    /**
     * Create an allocation expression (e.g. {@code = new String();})
     *
     * @param initializationType is the type of initializing constructor
     * @return reference to builder object
     */
    public FieldDeclarationBuilder withInitialization(Class<?> initializationType) {
        AllocationExpression allocExpression = new AllocationExpression();
        allocExpression.type = EclipseAstUtil.createTypeReference(initializationType.getName());
        fieldDeclaration.initialization = allocExpression;
        return this;
    }

    /**
     * Set TypeReferences to the new Fields' init expression, so the new field will have initialization with
     * diamonds (e.g. {@code = new List<String>();}, {@code = new Map<String, Object>();} ).
     *
     * @param diamondType could be any Class object. Appropriate type will be set
     *                    to the diamonds in the initialization
     * @return reference to builder object
     */
    public FieldDeclarationBuilder withDiamondsInitialization(Class<?> diamondType) {
        AllocationExpression allocExpression = (AllocationExpression) fieldDeclaration.initialization;
        TypeReference allocTypeRef = allocExpression.type;
        TypeReference diamondsTypeRef = createTypeReference(diamondType.getName());
        allocExpression.type = EclipseAstUtil.createParametrizedTypeReference(
                allocTypeRef.toString(), diamondsTypeRef, 1
        );
        return this;
    }

    /**
     * Creates initialization with empty diamonds
     *
     * @param type
     * @return reference to builder object
     */
    public FieldDeclarationBuilder withEmptyDiamondsInitialization(Class<?> type) {
        AllocationExpression allocationExpression = new AllocationExpression();
        TypeReference allocTypeRef = createTypeReference(type.getName());
        allocationExpression.type = EclipseAstUtil.createParametrizedTypeReference(
                allocTypeRef.toString(), null, 0
        );
        fieldDeclaration.initialization = allocationExpression;
        return this;
    }

    /**
     * Number Literal initialization
     */
    public FieldDeclarationBuilder withNumberLiteralInitialization(EclipsePrimitives literalType, String value) {
        switch (literalType) {
            case SHORT:
            case BYTE:
            case INT:
                fieldDeclaration.initialization = IntLiteral.buildIntLiteral(value.toCharArray(), 0, 0);
                break;
            case LONG:
                fieldDeclaration.initialization = LongLiteral.buildLongLiteral(value.toCharArray(), 0, 0);
                break;
            case FLOAT:
                fieldDeclaration.initialization = new FloatLiteral(value.toCharArray(), 0, 0);
                break;
            case DOUBLE:
                fieldDeclaration.initialization = new DoubleLiteral(value.toCharArray(), 0, 0);
                break;
        }
        return this;
    }


    /**
     * Boolean Literal initialization
     */
    public FieldDeclarationBuilder withBooleanLiteralInitialization(boolean value) {
        if (value)
            fieldDeclaration.initialization = new TrueLiteral(0, 0);
        else
            fieldDeclaration.initialization = new FalseLiteral(0, 0);
        return this;
    }

    /**
     * Char Literal initialization
     */
    public FieldDeclarationBuilder withCharLiteralInitialization(char value) {
        char[] token = new char[]{'\'', value, '\''};
        fieldDeclaration.initialization = new CharLiteral(
                token, 0, 0
        );
        return this;
    }

    public FieldDeclarationBuilder appendAnnotation(Class<?> annotationClass){
        NormalAnnotation annotation = new NormalAnnotation(EclipseAstUtil.createTypeReference(annotationClass.getName()), 0);
        fieldDeclaration.annotations = EclipseHandlerUtil.appendAnnotation(annotation, fieldDeclaration.annotations);
        return this;
    }
}
