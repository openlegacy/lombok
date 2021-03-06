package lombok.eclipse.handlers.builders;

import openlegacy.utils.EclipseAstUtil;
import org.eclipse.jdt.internal.compiler.ast.ClassLiteralAccess;
import org.eclipse.jdt.internal.compiler.ast.MemberValuePair;
import org.eclipse.jdt.internal.compiler.ast.NormalAnnotation;
import org.eclipse.jdt.internal.compiler.ast.QualifiedNameReference;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Matvey Mitnitsky on 23-May-17.
 */
public class AnnotationBuilder implements EclipseTreeBuilder<NormalAnnotation> {

    private NormalAnnotation annotation;

    private MemberValuePair[] arguments;

    private Set<String> argumentKeys = new HashSet<String>();

    public AnnotationBuilder(Class<?> annotationClass) {
        annotation = new NormalAnnotation(EclipseAstUtil.createTypeReference(annotationClass.getName()), 0);
    }

    /**
     * Builds an object of NormalAnnotation type
     *
     * @return {@link NormalAnnotation}
     */
    @Override
    public NormalAnnotation build() {
        annotation.memberValuePairs = arguments;
        return this.annotation;
    }

    /**
     * appends an argument to the annotation
     *
     * @return instance of the AnnotationBuilder class
     */
    public AnnotationBuilder appendArgumentWithClassLiteralValue(String argumentName, Class<?> value) {
        return appendArgumentWithClassLiteralValue(argumentName, value.getName());
    }

    /**
     * appends an argument to the annotation
     *
     * @return instance of the AnnotationBuilder class
     */
    public AnnotationBuilder appendArgumentWithClassLiteralValue(String argumentName, String classFQName) {
        // do not add argument if it exists
        if (argumentKeys.contains(argumentName)) return this;

        if (arguments == null || arguments.length == 0) {
            return setArgumentWithClassLiteralValue(argumentName, classFQName);
        }

        MemberValuePair argumentPair = createClassLiteralMemberValuePair(argumentName, classFQName);

        appendArgument(argumentPair);
        // add argumentName do the set to keep track of created annotation args
        argumentKeys.add(argumentName);
        return this;
    }

    /**
     * set an argument to the annotation
     *
     * @return
     */
    public AnnotationBuilder setArgumentWithClassLiteralValue(String argumentName, Class<?> value) {
        return setArgumentWithClassLiteralValue(argumentName, value.getName());
    }

    /**
     * set an argument to the annotation
     *
     * @return
     */
    public AnnotationBuilder setArgumentWithClassLiteralValue(String argumentName, String classFQName) {
        // do not add argument if it exists
        if (argumentKeys.contains(argumentName)) return this;

        MemberValuePair argumentPair = createClassLiteralMemberValuePair(argumentName, classFQName);
        this.arguments = new MemberValuePair[]{argumentPair};
        // add argumentName do the set to keep track of created annotation args
        argumentKeys.add(argumentName);
        return this;
    }

    public AnnotationBuilder appendEnumLiteral(String argumentName, Enum<?> enumLiteral) {
        // do not add argument if it exists
        String enumName = enumLiteral.getClass().getName();
        String enumConstantFQName = enumName + "." + enumLiteral.toString();
        String[] nameArray = enumConstantFQName.split("\\.");
        char[][] token = stringArrayToToken(nameArray);
        if (!argumentKeys.contains(enumName)) {
            MemberValuePair memberValuePair = new MemberValuePair(
                    argumentName.toCharArray(),
                    0,
                    0,
                    new QualifiedNameReference(token, null, 0, 0)
            );
            appendArgument(memberValuePair);
        }
        return this;
    }

    private MemberValuePair createClassLiteralMemberValuePair(String argumentName, String value) {
        ClassLiteralAccess classLiteral = new ClassLiteralAccess(0, EclipseAstUtil.createTypeReference(value));
        return new MemberValuePair(argumentName.toCharArray(), 0, 0, classLiteral);
    }

    private void appendArgument(MemberValuePair argumentPair) {
        if (arguments != null && arguments.length != 0) {
            int previousLength = arguments.length;
            MemberValuePair[] newArgumentArray = Arrays.copyOf(arguments, previousLength + 1);
            newArgumentArray[previousLength] = argumentPair;
            arguments = newArgumentArray;
        } else {
            arguments = new MemberValuePair[]{argumentPair};
        }
    }

    private char[][] stringArrayToToken(String[] nameArray) {
        char[][] token = new char[nameArray.length][];
        for (int i = 0; i < nameArray.length; i++) {
            token[i] = nameArray[i].toCharArray();
        }
        return token;
    }
}
