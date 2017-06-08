package lombok.eclipse.handlers.builders;

import lombok.eclipse.handlers.openlegacy.EclipseModifier;
import org.eclipse.jdt.internal.compiler.ast.FieldDeclaration;
import org.eclipse.jdt.internal.compiler.ast.TypeDeclaration;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFileConstants;

import java.util.ArrayList;
import java.util.List;

import static lombok.eclipse.handlers.openlegacy.EclipseHandlerUtil.*;
import static openlegacy.utils.StringUtil.prepend;

/**
 * @author Matvey Mitnitsky on 22-May-17.
 */
public class TypeDeclarationBuilder implements EclipseTreeBuilder<TypeDeclaration> {

    private TypeDeclaration typeDecl;

    public TypeDeclarationBuilder(String className, TypeDeclaration enclosingType) {
        this.typeDecl = new TypeDeclaration(enclosingType.compilationResult);
        typeDecl.enclosingType = enclosingType;
        typeDecl.name = className.toCharArray();
    }

    /**
     * Builds TypeDeclaration object.
     *
     * @return {@link TypeDeclaration}
     */
    @Override
    public TypeDeclaration build() {
        return typeDecl;
    }

    /**
     * Adds modifiers to the new Type Declaration
     *
     * @param modifiers {@link EclipseModifier} (e.g. public, private, final, static, synchronized)
     * @return reference to builder object
     */
    public TypeDeclarationBuilder withModifiers(EclipseModifier... modifiers) {
        addModifiers(typeDecl, modifiers);
        return this;
    }

    /**
     * Adds modifiers to the new Type Declaration
     *
     * @param modifiers int, accepts public properties of interface {@link ClassFileConstants} (e.g. AccPublic, AccPrivate)
     * @return reference to builder object
     */
    public TypeDeclarationBuilder withModifiers(int... modifiers) {
        addModifiers(typeDecl, modifiers);
        return this;
    }

    public TypeDeclarationBuilder withImplements(Class<?>... interfaces) {
        if (interfaces == null || interfaces.length == 0)
            return this;

        typeDecl.superInterfaces = getTypeReferenceArray(interfaces);
        return this;
    }

    public TypeDeclarationBuilder setFieldDeclarations(FieldDeclaration... fieldDeclarations) {
        typeDecl.fields = fieldDeclarations;
        return this;
    }

    public TypeDeclarationBuilder appendFieldDeclarations(FieldDeclaration... fieldDeclarations) {
        FieldDeclaration[] currentFields = typeDecl.fields;
        List<FieldDeclaration> fieldDeclarationsList = new ArrayList();
        for (int i = 0; i < fieldDeclarations.length; i++) {
            if (fieldExist(fieldDeclarations[i], currentFields))
                continue;
            fieldDeclarationsList.add(fieldDeclarations[i]);
        }
        FieldDeclaration[] resultingDeclarations = new FieldDeclaration[currentFields.length + fieldDeclarationsList.size()];
        combineArrayAndList(currentFields, fieldDeclarationsList, resultingDeclarations);
        typeDecl.fields = resultingDeclarations;
        return this;
    }

    /**
     * Set suffix to the new Class Declaration name.
     *
     * @param suffix
     * @return
     */
    public TypeDeclarationBuilder withNameSuffix(String suffix) {
        String fieldNameWithSuffix = String.valueOf(typeDecl.name) + suffix;
        typeDecl.name = fieldNameWithSuffix.toCharArray();
        return this;
    }


    /**
     * Set prefix to the new Class Declaration name.
     *
     * @param prefix
     * @return
     */
    public TypeDeclarationBuilder withNamePrefix(String prefix) {
        typeDecl.name = prepend(typeDecl.name, prefix);
        return this;
    }

    private boolean fieldExist(FieldDeclaration fieldDeclaration, FieldDeclaration[] currentFields) {
        String fieldDeclName = String.valueOf(fieldDeclaration.name);
        for (int i = 0; i < currentFields.length; i++) {
            if (fieldDeclName.equals(String.valueOf(currentFields[i])))
                return true;
        }
        return false;
    }

    private void combineArrayAndList(FieldDeclaration[] array, List<FieldDeclaration> list, FieldDeclaration[] arrayTo) {
        if (array.length + list.size() > arrayTo.length)
            return;
        System.arraycopy(array, 0, arrayTo, 0, array.length);

        int i = array.length;

        for (FieldDeclaration fieldDeclaration : list)
            arrayTo[i++] = fieldDeclaration;
    }
}
