package lombok.javac.handlers;

import static lombok.javac.handlers.JavacHandlerUtil.chainDotsString;

import java.util.ArrayList;

import javax.lang.model.element.Element;

import openlegacy.RpcEntityInterfaceJavacHandler;
import openlegacy.ScreenEntitiyInterfaceJavacHandler;
import org.mangosdk.spi.ProviderFor;

import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.JCAnnotation;
import com.sun.tools.javac.tree.JCTree.JCClassDecl;
import com.sun.tools.javac.tree.JCTree.JCExpression;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import com.sun.tools.javac.util.List;

import lombok.AccessLevel;
import lombok.OLData;
import lombok.core.AnnotationValues;
import lombok.javac.JavacAnnotationHandler;
import lombok.javac.JavacNode;
import org.openlegacy.rpc.RpcEntity;
import org.openlegacy.terminal.ScreenEntity;

/**
 * <h1>Prototype Handler</h1>
 *
 * Handles the {@code lombok.Implements} annotation for java compiler.
 *
 * @author Matvey Mitnitsky, Tom Fingerman
 * @since 3.6.0-SNAPSHOT
 */

@ProviderFor(JavacAnnotationHandler.class)
public class HandleOLData extends JavacAnnotationHandler<OLData> {

    @Override
    public void handle(AnnotationValues<OLData> annotationValues, JCAnnotation ast, JavacNode annotationNode) {
        JavacNode typeNode = annotationNode.up();
        JCClassDecl typeDecl = checkAnnotation(typeNode, annotationNode);
        if (typeDecl == null) {
            return;
        }

        OLData instance = annotationValues.getInstance();
        Class<?> entityType = instance.value();

        if (entityType.getName().equals(ScreenEntity.class.getName())) {
            String interfaceName = entityType.getName();
            generateImplemets(typeNode, interfaceName);
            new ScreenEntitiyInterfaceJavacHandler().handle(typeNode, annotationNode);
        }

        if(entityType.getName().equals(RpcEntity.class.getName())){
            String interfaceName = entityType.getName();
            generateImplemets(typeNode, interfaceName);

            //TODO implement RpcEntityInterfaceJavacHandler
            new RpcEntityInterfaceJavacHandler().handle(typeNode, annotationNode);
        }

        new HandleGetter().generateGetterForType(typeNode, annotationNode, AccessLevel.PUBLIC, true);
        new HandleSetter().generateSetterForType(typeNode, annotationNode, AccessLevel.PUBLIC, true);
    }

    //TODO need to extend the method logic. take care of the edge cases.
    private void generateImplemets(JavacNode typeNode, String interfaceName) {
        final JCClassDecl classDecl = (JCClassDecl) typeNode.get();
        List<JCExpression> implementing = classDecl.implementing;
        JCExpression ifExpression = chainDotsString(typeNode, interfaceName);
        if(implementing!= null && !implementing.isEmpty() && implementing.contains(ifExpression))
            return;
        implementing = implementing.append(ifExpression);
        classDecl.implementing = implementing;
    }

//    private void handleScreens(JavacNode typeNode) {
//        JavacTreeMaker maker = typeNode.getTreeMaker();
//        java.util.List<JCVariableDecl> varsList = new ArrayList<JCTree.JCVariableDecl>();
//
//        JCExpression listTypeRef = maker.TypeApply(
//        		chainDotsString(typeNode, "java.util.List"),
//        		List.<JCExpression>nil().append(chainDotsString(typeNode, "org.openlegacy.terminal.definitions.TerminalActionDefinition")));
//
//        JCExpression listInit = maker.NewClass(
//                null, List.<JCExpression>nil(),
//                chainDotsString(typeNode, "java.util.ArrayList"),
//                List.<JCExpression>nil(),
//                null);
//
//        JCVariableDecl actionsDecl = maker.VarDef(
//                maker.Modifiers(Flags.PRIVATE),
//                typeNode.toName("actions"),
//                listTypeRef,
//                listInit);
//
//        varsList.add(actionsDecl);
//
//        JCVariableDecl focusDecl = maker.VarDef(
//                maker.Modifiers(Flags.PRIVATE),
//                typeNode.toName("focusField"),
//                JavacHandlerUtil.genJavaLangTypeRef(typeNode, "String"),
//                null);
//
//        varsList.add(focusDecl);
//        JCVariableDecl pcCommandDecl = maker.VarDef(
//                maker.Modifiers(Flags.PRIVATE),
//                typeNode.toName("pcCommand"),
//                JavacHandlerUtil.genJavaLangTypeRef(typeNode, "String"),
//                null);
//        varsList.add(pcCommandDecl);
//
//        injectFields(typeNode, varsList);
//    }
//
//    private void injectFields(JavacNode typeNode, java.util.List<JCVariableDecl> varsList) {
//        if (typeNode == null || varsList == null || varsList.isEmpty())
//            return;
//        for (JCVariableDecl vd : varsList)
//            JavacHandlerUtil.injectFieldAndMarkGenerated(typeNode, vd);
//
//    }

    public static JCClassDecl checkAnnotation(JavacNode typeNode, JavacNode annotationNode) {
        JCClassDecl typeDecl = null;
        if (typeNode.get() instanceof JCClassDecl) {
            typeDecl = (JCClassDecl) typeNode.get();
        }

        long modifiers = typeDecl == null ? 0 : typeDecl.mods.flags;
        boolean notAClass = (modifiers & (Flags.INTERFACE | Flags.ANNOTATION | Flags.ENUM)) != 0;

        if (typeDecl == null | notAClass) {
            annotationNode.addError("@Implements is only supported on a class.");
            return null;
        }

        return typeDecl;
    }
}
