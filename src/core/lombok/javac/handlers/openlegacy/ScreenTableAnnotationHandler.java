package lombok.javac.handlers.openlegacy;

import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import lombok.javac.JavacNode;
import lombok.javac.handlers.JavacHandlerUtil;
import lombok.javac.handlers.JavacOLUtil;
import lombok.javac.handlers.OLJavacHandlerUtil;
import lombok.javac.handlers.builders.FieldDeclBuilder;
import org.openlegacy.core.terminal.TerminalField;

import java.util.ArrayList;

import static lombok.javac.handlers.JavacHandlerUtil.*;
import static lombok.javac.handlers.JavacOLUtil.*;
import static lombok.javac.handlers.OLJavacHandlerUtil.*;
import static openlegacy.LombokOLConstants.*;


/**
 * @author Matvey Mitnitsky on 01-Jun-17.
 */
public class ScreenTableAnnotationHandler {


    public static void handle(JavacNode typeNode, boolean supportTerminalData) {
        createScreenTableFields(typeNode);
        if (supportTerminalData) createTerminalFields(typeNode);
    }

    private static void createScreenTableFields(JavacNode typeNode) {
        if (!fieldExist(typeNode, FOCUS_FIELD_NAME)) {
            JCTree.JCVariableDecl focusFieldDecl = new FieldDeclBuilder(typeNode, FOCUS_FIELD_NAME)
                    .withModifiers(Flags.PRIVATE)
                    .withType(String.class)
                    .build();

            injectField(typeNode, focusFieldDecl);
        }
    }


    private static void createTerminalFields(JavacNode typeNode) {
        java.util.List<JCVariableDecl> terminalFields = new ArrayList<JCTree.JCVariableDecl>();

        for (JavacNode fieldNode : findAllScreenFields(typeNode)) {
            String nameWithFieldSuffix = fieldNode.getName() + FIELD_SUFFIX;
            boolean isBoolean = JavacHandlerUtil.isBoolean(fieldNode);
            String getterName = JavacOLUtil.toGetterName(nameWithFieldSuffix);

            if (JavacHandlerUtil.MemberExistsResult.NOT_EXISTS.equals(
                    JavacHandlerUtil.methodExists(getterName, fieldNode, isBoolean, 0))
                    && OLJavacHandlerUtil.isPrimitive(fieldNode) && !fieldExist(typeNode, nameWithFieldSuffix)) {

                JCTree.JCVariableDecl terminalField = new FieldDeclBuilder(typeNode, nameWithFieldSuffix)
                        .withModifiers(Flags.PRIVATE)
                        .withType(TerminalField.class)
                        .build();

                terminalFields.add(terminalField);
            }
        }

        injectFields(typeNode, terminalFields);
    }


}