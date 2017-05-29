package lombok.javac.handlers.builders;

import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.List;
import lombok.javac.JavacNode;
import lombok.javac.JavacTreeMaker;

import static lombok.javac.handlers.JavacOLUtil.*;

/**
 * @author Matvey Mitnitsky on 25-May-17.
 */
public class AnnotationBuilder implements JavacTreeBuilder<JCTree.JCAnnotation> {

    private final JavacNode typeNode;
    private final JavacTreeMaker treeMaker;

    private Class<?> annotationType;
    private List<JCTree.JCExpression> args = List.<JCTree.JCExpression>nil();

    public AnnotationBuilder(JavacNode typeNode, Class<?> annotationType) {
        this.typeNode = typeNode;
        this.treeMaker = typeNode.getTreeMaker();
        this.annotationType = annotationType;
    }

    @Override
    public JCTree.JCAnnotation build() {
        return treeMaker.Annotation(
                getJCExpressionForType(typeNode, annotationType),
                args
        );
    }

    public AnnotationBuilder appendClassLiteralArgument(String argument, Class<?> clazz) {
        return appendClassLiteralArgument(argument, clazz.getName());
    }

    public AnnotationBuilder appendClassLiteralArgument(String argument, String className){
        args = args.append(treeMaker.Assign(
                treeMaker.Ident(typeNode.toName(argument)),
                treeMaker.Select(getJCExpressionForType(typeNode, className), typeNode.toName("class")))
        );

        return this;
    }
}
