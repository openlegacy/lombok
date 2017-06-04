package lombok.eclipse.handlers.openlegacy;

import lombok.AccessLevel;
import lombok.core.AST;
import lombok.eclipse.Eclipse;
import lombok.eclipse.EclipseNode;
import lombok.eclipse.handlers.HandleConstructor;
import lombok.eclipse.handlers.HandleEqualsAndHashCode;
import lombok.eclipse.handlers.HandleGetter;
import lombok.eclipse.handlers.HandleSetter;
import lombok.eclipse.handlers.HandleToString;
import openlegacy.utils.EclipseAstUtil;
import org.eclipse.jdt.internal.compiler.ast.Annotation;
import org.eclipse.jdt.internal.compiler.ast.Expression;
import org.eclipse.jdt.internal.compiler.ast.FalseLiteral;
import org.eclipse.jdt.internal.compiler.ast.FieldDeclaration;
import org.eclipse.jdt.internal.compiler.ast.MemberValuePair;
import org.eclipse.jdt.internal.compiler.ast.SingleTypeReference;
import org.eclipse.jdt.internal.compiler.ast.TypeDeclaration;
import org.eclipse.jdt.internal.compiler.ast.TypeReference;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFileConstants;
import org.openlegacy.annotations.screen.ScreenEntity;
import org.openlegacy.annotations.screen.ScreenField;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Matvey Mitnitsky
 * @since 3.6.0-SNAPSHOT
 */
public class EclipseHandlerUtil {

    /**
     * generates getters and setters
     *
     * @param typeNode
     * @param annotationNode
     */
    public static void getenerateLombokGetAndSet(EclipseNode typeNode, EclipseNode annotationNode) {
        new HandleGetter().generateGetterForType(typeNode, annotationNode, AccessLevel.PUBLIC, true);
        new HandleSetter().generateSetterForType(typeNode, annotationNode, AccessLevel.PUBLIC, true);
    }

    /**
     * generates getters, setters, equals, hashcode, toString, and constructor
     *
     * @param typeNode
     * @param annotationNode
     */
    public static void generateLombokData(EclipseNode typeNode, EclipseNode annotationNode) {
        new HandleGetter().generateGetterForType(typeNode, annotationNode, AccessLevel.PUBLIC, true);
        new HandleSetter().generateSetterForType(typeNode, annotationNode, AccessLevel.PUBLIC, true);
        new HandleEqualsAndHashCode().generateEqualsAndHashCodeForType(typeNode, annotationNode);
        new HandleToString().generateToStringForType(typeNode, annotationNode);
        new HandleConstructor().generateRequiredArgsConstructor(
                typeNode, AccessLevel.PUBLIC, null, HandleConstructor.SkipIfConstructorExists.YES,
                Collections.<Annotation>emptyList(), annotationNode);

    }

    /**
     * The method checks if the annotation is set over class and not over interface, annotation or enum
     */
    public static TypeDeclaration checkAnnotation(EclipseNode typeNode, EclipseNode annotationNode) {
        TypeDeclaration typeDecl = null;
        if (typeNode.get() instanceof TypeDeclaration) {
            typeDecl = (TypeDeclaration) typeNode.get();
        }
        int modifiers = typeDecl == null ? 0 : typeDecl.modifiers;
        boolean notAClass = (modifiers
                & (ClassFileConstants.AccInterface | ClassFileConstants.AccAnnotation | ClassFileConstants.AccEnum)) != 0;

        if (typeDecl == null || notAClass) {
            annotationNode.addError("This annotation is only supported on a class.");
            return null;
        }
        return typeDecl;
    }

    /**
     * The method checks if the annotation is set over class and not over interface, annotation or enum
     */
    public static boolean validateAnnotation(EclipseNode typeNode, EclipseNode annotationNode) {
        TypeDeclaration typeDecl = null;
        if (typeNode.get() instanceof TypeDeclaration) {
            typeDecl = (TypeDeclaration) typeNode.get();
        }
        int modifiers = typeDecl == null ? 0 : typeDecl.modifiers;
        boolean notAClass = (modifiers
                & (ClassFileConstants.AccInterface | ClassFileConstants.AccAnnotation | ClassFileConstants.AccEnum)) != 0;

        if (typeDecl == null || notAClass) {
            annotationNode.addError("This annotation is only supported on a class.");
            return false;
        }
        return true;
    }

    /**
     * This method checks within fields array if fields with fieldName Exists
     *
     * @param fields    {@link FieldDeclaration} array
     * @param fieldName Sting
     * @return boolean result
     */
    public static boolean fieldExist(FieldDeclaration[] fields, String fieldName) {
        if (fields == null || fields.length == 0 || fieldName == null || fieldName.trim().isEmpty())
            return false;

        for (FieldDeclaration declaration : fields)
            if (fieldName.equals(String.valueOf(declaration.name)))
                return true;

        return false;
    }

    /**
     * @return supportTerminalData value of ScreenEntity annotation if present.
     */
    public static boolean supportTerminalData(Annotation[] annotations) {
        Annotation ann = null;
        for (Annotation annotation : annotations) {
            String singleString = Eclipse.toQualifiedName(annotation.type.getTypeName());
            String screenEntityName = annotation.type instanceof SingleTypeReference ? ScreenEntity.class.getSimpleName()
                    : ScreenEntity.class.getName();
            if (screenEntityName.equals(singleString)) {
                ann = annotation;
                break;
            }
        }

        if (ann != null) {
            MemberValuePair[] pairs = ann.memberValuePairs();
            for (MemberValuePair pair : pairs) {
                if ("supportTerminalData".equals(new String(pair.name))) {
                    Expression expression = pair.value;
                    String clz = expression.getClass().getName();
                    if (FalseLiteral.class.isInstance(expression))
                        return false;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method adds implements of passed interfaces to TypeDeclaration and
     * removes all interface duplicates if there are present.
     *
     * @param interfaces This is array of interfaces which will be added to typeDeclaration
     * @param typeNode   This is the node of type which interfaces list will be modified
     */
    public static void addImplements(EclipseNode typeNode, Class<?>... interfaces) {
        if (interfaces == null || interfaces.length == 0)
            return;

        for (Class iFace : interfaces)
            generateImplementsForType(typeNode, iFace.getName());

    }

    private static boolean generateImplementsForType(EclipseNode typeNode, String type) {
        TypeDeclaration typeDecl = (TypeDeclaration) typeNode.get();

        List<TypeReference> resultingList = new ArrayList<TypeReference>();
        // represents a list of full qualified declared interfaces
        List<String> existingFqInterfacesNames = new ArrayList<String>();
        // represents a list of simple declared interfaces
        List<String> existingInterfacesNames = new ArrayList<String>();

        if (typeDecl.superInterfaces != null) {
            for (TypeReference reference : typeDecl.superInterfaces) {
                resultingList.add(reference);
                char[][] typeName = reference.getTypeName();
                if (typeName.length > 1) {
                    // add to fq list
                    existingFqInterfacesNames.add(Eclipse.toQualifiedName(typeName));
                } else {
                    existingInterfacesNames.add(String.valueOf(reference.getLastToken()));
                }
            }
        }

        String simpleName = type;
        boolean qualified = simpleName.contains(".");
        if (qualified) {
            String[] split = simpleName.split("\\.");
            simpleName = split[split.length - 1];
        }
        // check that current type was not already declared
        if (!existingFqInterfacesNames.contains(type) && !existingInterfacesNames.contains(simpleName)) {
            String fromImports = typeNode.getAst().getImportList().getFullyQualifiedNameForSimpleName(simpleName);
            TypeReference typeReference = EclipseAstUtil.createTypeReference(qualified || fromImports == null ? type : simpleName);
            resultingList.add(typeReference);
        }
        // set new list of interfaces
        typeDecl.superInterfaces = resultingList.toArray(new TypeReference[]{});

        return true;
    }


    /**
     * Transforms Class array to TypeReference array
     */
    public static TypeReference[] getTypeReferenceArray(Class<?>[] interfaces) {
        TypeReference[] res = new TypeReference[interfaces.length];
        for (int i = 0; i < interfaces.length; i++) {
            res[i] = EclipseAstUtil.createTypeReference(interfaces[i].getName());
        }

        return res;
    }

    /**
     * used by builders to add modifiers to the type or field declaration
     */
    //TODO maybe it is preferable to place this methods in new EclipseBuildersUtil ??
    public static void addModifiers(TypeDeclaration typeDecl, EclipseModifier... modifiers) {
        if (modifiers.length == 0)
            return;
        int typeModifiers = typeDecl.modifiers;
        for (EclipseModifier modifier : modifiers) {
            typeModifiers |= modifier.modifierConstant;
        }
        typeDecl.modifiers = typeModifiers;
    }

    /**
     * used by builders to add modifiers to the type or field declaration
     */
    public static void addModifiers(FieldDeclaration fieldDeclaration, EclipseModifier... modifiers) {
        if (modifiers.length == 0)
            return;
        int fieldModifiers = fieldDeclaration.modifiers;
        for (EclipseModifier modifier : modifiers) {
            fieldModifiers |= modifier.modifierConstant;
        }
        fieldDeclaration.modifiers = fieldModifiers;
    }

    /**
     * used by builders to add modifiers to the type or field declaration
     */
    public static void addModifiers(TypeDeclaration typeDecl, int... modifiers) {
        if (modifiers.length == 0)
            return;
        int fieldModifiers = typeDecl.modifiers;
        for (int modifier : modifiers)
            fieldModifiers |= modifier;
        typeDecl.modifiers = fieldModifiers;
    }

    /**
     * used by builders to add modifiers to the type or field declaration
     */
    public static void addModifiers(FieldDeclaration fieldDeclaration, int... modifiers) {
        if (modifiers.length == 0)
            return;
        int fieldModifiers = fieldDeclaration.modifiers;
        for (int modifier : modifiers)
            fieldModifiers |= modifier;
        fieldDeclaration.modifiers = fieldModifiers;
    }

    /**
     * The method checks if inner class with {@param className String} exists in the
     * enclosing class {@param typeNode EclipseNode}
     *
     * @return boolean result
     */
    public static boolean classExists(EclipseNode typeNode, String className) {
        for (EclipseNode node : typeNode.down())
            if (node.getKind() == AST.Kind.TYPE && className.equals(node.getName()))
                return true;

        return false;
    }

    /**
     * The method copies {@param newAnnotation Annotation array} and {@param annoataion Annotation} to the
     * new Annotation array
     *
     * @return Annotation array
     */
    public static Annotation[] appendAnnotation(Annotation newAnnotation, Annotation[] annotations) {
        if (annotations == null) return new Annotation[]{newAnnotation};
        Annotation[] output = Arrays.copyOf(annotations, annotations.length + 1);
        output[output.length - 1] = newAnnotation;
        return output;
    }

    /**
     * returns true in the case passed typeReference is a representation of OL primitive types that are not inner or external class types
     */
    public static boolean isPrimitive(TypeReference typeReference) {
        if (typeReference.dimensions() != 0) {
            return false;
        }

        if (Eclipse.nameEquals(typeReference.getTypeName(), "int")) {
            return true;
        }
        if (Eclipse.nameEquals(typeReference.getTypeName(), String.class.getSimpleName())
                || Eclipse.nameEquals(typeReference.getTypeName(), String.class.getName())) {
            return true;
        }
        if (Eclipse.nameEquals(typeReference.getTypeName(), BigInteger.class.getSimpleName())
                || Eclipse.nameEquals(typeReference.getTypeName(), BigInteger.class.getName())) {
            return true;
        }
        if (Eclipse.nameEquals(typeReference.getTypeName(), Integer.class.getSimpleName())
                || Eclipse.nameEquals(typeReference.getTypeName(), Integer.class.getName())) {
            return true;
        }
        if (Eclipse.nameEquals(typeReference.getTypeName(), Boolean.class.getSimpleName())
                || Eclipse.nameEquals(typeReference.getTypeName(), Boolean.class.getName())
                || Eclipse.nameEquals(typeReference.getTypeName(), "boolean")) {
            return true;
        }
        if (Eclipse.nameEquals(typeReference.getTypeName(), Date.class.getSimpleName())
                || Eclipse.nameEquals(typeReference.getTypeName(), Date.class.getName())) {
            return true;
        }
        if (Eclipse.nameEquals(typeReference.getTypeName(), Double.class.getSimpleName())
                || Eclipse.nameEquals(typeReference.getTypeName(), Double.class.getName())) {
            return true;
        }
        if (Eclipse.nameEquals(typeReference.getTypeName(), BigDecimal.class.getSimpleName())
                || Eclipse.nameEquals(typeReference.getTypeName(), BigDecimal.class.getName())) {
            return true;
        }

        return false;
    }

    /**
     * method returns List of all fields annotated with {@link ScreenField} annotation
     */
    public static List<EclipseNode> findAllScreenFields(EclipseNode typeNode) {
        List<EclipseNode> fields = new ArrayList<EclipseNode>();
        for (EclipseNode child : typeNode.down()) {
            if (child.getKind() != AST.Kind.FIELD) {
                continue;
            }
            FieldDeclaration fieldDecl = (FieldDeclaration) child.get();
            if (!lombok.eclipse.handlers.EclipseHandlerUtil.hasAnnotation(ScreenField.class, child)) {
                continue;
            }

            fields.add(child);
        }
        return fields;
    }

}
