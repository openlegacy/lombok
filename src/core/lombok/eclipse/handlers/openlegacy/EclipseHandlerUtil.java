package lombok.eclipse.handlers.openlegacy;

import lombok.eclipse.Eclipse;
import lombok.eclipse.EclipseNode;
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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Matvey Mitnitsky
 * @since 3.6.0-SNAPSHOT
 */
public class EclipseHandlerUtil {

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

    public static boolean fieldExist(FieldDeclaration[] fields, String fieldName) {
        if (fields == null || fields.length == 0 || fieldName == null || fieldName.trim().isEmpty()) {
            return false;
        }
        for (FieldDeclaration declaration : fields) {
            if (fieldName.equals(declaration.name)) {
                return true;
            }
        }
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
        TypeReference[] implArr = null;
        TypeDeclaration typeDecl = (TypeDeclaration) typeNode.get();

        if (interfaces == null || interfaces.length == 0)
            return;

        implArr = typeDecl.superInterfaces;

        TypeReference[] newInterfaces = getTypeReferenceArray   (interfaces);

        typeDecl.superInterfaces = mergeArraysAndRemoveDuplicates(implArr, newInterfaces);

    }


    /**
     * Takes 2 arrays of TypeReference type and merges into one array without duplicates
     */
    private static TypeReference[] mergeArraysAndRemoveDuplicates(TypeReference[] arr1, TypeReference[] arr2) {
        if (arr1 == null || arr1.length == 0)
            return arr2;
        if (arr2 == null || arr2.length == 0)
            return arr1;

        int arr1Length = arr1.length;
        int arr2Length = arr2.length;

        TypeReference[] merged = new TypeReference[arr1Length + arr2Length];
        //copy all arrays content to the merged array
        System.arraycopy(arr1, 0, merged, 0, arr1Length);
        System.arraycopy(arr2, 0, merged, arr1Length, arr2Length);

        Set<TypeReference> typeReferences = new HashSet();
        //by adding array to the Set we simply remove duplicates
        typeReferences.addAll(Arrays.asList(merged));

        return (TypeReference[]) typeReferences.toArray();
    }

    /**
     * Transforms Class array to TypeReference array
     */
    private static TypeReference[] getTypeReferenceArray(Class<?>[] interfaces) {
        TypeReference[] res = new TypeReference[interfaces.length];
        for (int i = 0; i < interfaces.length; i++) {
            res[i] = EclipseAstUtil.createTypeReference(interfaces[i].getName());
        }

        return res;
    }
}
