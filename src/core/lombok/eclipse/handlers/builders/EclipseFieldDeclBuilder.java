package lombok.eclipse.handlers.builders;

import lombok.eclipse.handlers.openlegacy.EclipseModifier;
import org.eclipse.jdt.internal.compiler.ast.FieldDeclaration;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFileConstants;

/**
 * @author Matvey Mitnitsky
 * @since 3.6.0-SNAPSHOT
 */
public interface EclipseFieldDeclBuilder extends EclipseTreeBuilder<FieldDeclaration> {

    /**
     * Adds modifiers to the new Fields' Declaration
     *
     * @param modifiers {@link EclipseModifier} (e.g. public, private, final, static, synchronized)
     * @return reference to builder object
     */
    EclipseFieldDeclBuilder withModifiers(EclipseModifier... modifiers);

    /**
     * Adds modifiers to the new Fields' Declaration
     *
     * @param modifiers accepts public properties of interface {@link ClassFileConstants} (e.g. AccPublic, AccPrivate)
     * @return reference to builder object
     */
    EclipseFieldDeclBuilder withEclipseModifiers(int... modifiers);

    /**
     * Set TypeReference type representation object to the field declaration
     *
     * @param fieldType Class instance of Java type that to be set to the new field
     * @return reference to builder object
     */
    EclipseFieldDeclBuilder withType(Class<?> fieldType);

//    /**
//     * Set name to the new Fields' Declaration
//     *
//     * @param name simple string name to be set to the new filed variable
//     * @return reference to builder object
//     */
//    EclipseFieldDeclBuilder withName(String name);

    /**
     * Set suffix to the new Fileds' Declaration name.
     *
     * @param suffix
     * @return
     */
    EclipseFieldDeclBuilder withNameSuffix(String suffix);


    /**
     * Set prefix to the new Fileds' Declaration name.
     *
     * @param prefix
     * @return
     */
    EclipseFieldDeclBuilder withNamePrefix(String prefix);

    /**
     * Set TypeReferences to the new Fields' Declaration, so the new field will have type references
     * in the diamonds (e.g. List<String>, Map<String, Object>).
     *
     * @param diamondsType could be any Class objects. Appropriate Type  will be set to the diamonds.
     * @return reference to builder object
     */
    EclipseFieldDeclBuilder withDiamondsType(Class<?> diamondsType);

    /**
     * Set TypeReferences to the new Fields' Declaration, so the new field will have type references
     * in the diamonds (e.g. List<String>, Map<String, Object>).
     *
     * @param diamondsTypes could be an array of any Class objects. Appropriate Types will be set to the diamonds.
     * @return reference to builder object
     */
//    EclipseFieldDeclBuilder withDiamondsType(Class<?>... diamondsTypes);

    /**
     * Create an allocation expression (e.g. {@code = new String();})
     * Type of constructor is the same as Reference type
     *
     * @return reference to builder object
     */
    EclipseFieldDeclBuilder withInitialization();

    /**
     * Create an allocation expression (e.g. {@code = new String();})
     *
     * @param initializationType is the type of initializing constructor
     * @return reference to builder object
     */
    EclipseFieldDeclBuilder withInitialization(Class<?> initializationType);

    /**
     * Set TypeReferences to the new Fields' init expression, so the new field will have initialization with
     * diamonds (e.g. {@code = new List<String>();}, {@code = new Map<String, Object>();} ).
     *
     * @param diamondType could be any Class object. Appropriate type will be set
     *                    to the diamonds in the initialization
     * @return reference to builder object
     */
    EclipseFieldDeclBuilder withDiamondsInitialization(Class<?> diamondType);

    /**
     * Set TypeReferences to the new Fields' init expression, so the new field will have initialization with
     * diamonds (e.g. {@code = new List<String>();}, {@code = new Map<String, Object>();} ).
     *
     * @param diamondTypes could be an array of any Class object. Appropriate types will be set
     *                     to the diamonds in the initialization
     * @return reference to builder object
     */
//    EclipseFieldDeclBuilder withDiamondsInitialization(Class<?>... diamondTypes);


}
