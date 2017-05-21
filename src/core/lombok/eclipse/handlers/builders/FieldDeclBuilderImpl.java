package lombok.eclipse.handlers.builders;

import lombok.eclipse.handlers.openlegacy.EclipseModifier;
import openlegacy.utils.EclipseAstUtil;
import openlegacy.utils.StringUtil;
import org.eclipse.jdt.internal.compiler.ast.AllocationExpression;
import org.eclipse.jdt.internal.compiler.ast.FieldDeclaration;
import org.eclipse.jdt.internal.compiler.ast.TypeReference;

import static openlegacy.utils.EclipseAstUtil.*;

/**
 * @author Matvey Mitnitsky on 21-May-17.
 */
public class FieldDeclBuilderImpl implements EclipseFieldDeclBuilder {

    private FieldDeclaration fieldDeclaration;

    public FieldDeclBuilderImpl(String fieldName) {
        fieldDeclaration = new FieldDeclaration(fieldName.toCharArray(), 0, 0);
    }

    @Override
    public FieldDeclaration build() {
        return fieldDeclaration;
    }

    @Override
    public EclipseFieldDeclBuilder withModifiers(EclipseModifier... modifiers) {
        if (modifiers.length == 0)
            return this;
        int fieldModifiers = fieldDeclaration.modifiers;
        for (EclipseModifier modifier : modifiers) {
            fieldModifiers |= modifier.modifierConstant;
        }
        fieldDeclaration.modifiers = fieldModifiers;
        return this;
    }

    @Override
    public EclipseFieldDeclBuilder withEclipseModifiers(int... modifiers) {
        if (modifiers.length == 0)
            return this;
        int fieldModifiers = fieldDeclaration.modifiers;
        for (int modifier : modifiers)
            fieldModifiers |= modifier;
        fieldDeclaration.modifiers = fieldModifiers;
        return this;
    }

    @Override
    public EclipseFieldDeclBuilder withType(Class<?> fieldType) {
        TypeReference typeReference = EclipseAstUtil.createTypeReference(fieldType.getName());
        fieldDeclaration.type = typeReference;
        return this;
    }

    @Override
    public EclipseFieldDeclBuilder withNameSuffix(String suffix) {
        String fieldNameWithSuffix = String.valueOf(fieldDeclaration.name) + suffix;
        fieldDeclaration.name = fieldNameWithSuffix.toCharArray();
        return this;
    }

    @Override
    public EclipseFieldDeclBuilder withNamePrefix(String prefix) {
        if (prefix.isEmpty())
            return this;
        String previousName = StringUtil.firstCharToUpperCase(fieldDeclaration.name);
        String fieldNameWithPrefix = prefix + previousName;
        fieldDeclaration.name = fieldNameWithPrefix.toCharArray();
        return this;
    }

    @Override
    public EclipseFieldDeclBuilder withDiamondsType(Class<?> diamondsType) {
        TypeReference typeReference = fieldDeclaration.type;
        TypeReference diamondsTypeRef = EclipseAstUtil.createTypeReference(diamondsType.getName());
        fieldDeclaration.type = EclipseAstUtil.createParametrizedTypeReference(
                typeReference.toString(), diamondsTypeRef, 0
        );
        return this;
    }

    @Override
    public EclipseFieldDeclBuilder withInitialization() {
        fieldDeclaration.initialization = new AllocationExpression();
        return this;
    }

    @Override
    public EclipseFieldDeclBuilder withInitialization(Class<?> initializationType) {
        AllocationExpression allocExpression = new AllocationExpression();
        allocExpression.type = EclipseAstUtil.createTypeReference(initializationType.getName());
        fieldDeclaration.initialization = allocExpression;
        return this;
    }

    @Override
    public EclipseFieldDeclBuilder withDiamondsInitialization(Class<?> diamondType) {
        AllocationExpression allocExpression = (AllocationExpression) fieldDeclaration.initialization;
        TypeReference allocTypeRef = allocExpression.type;
        TypeReference diamondsTypeRef = createTypeReference(diamondType.getName());
        allocExpression.type = EclipseAstUtil.createParametrizedTypeReference(allocTypeRef.toString(), diamondsTypeRef, 0);
        return this;
    }
}
