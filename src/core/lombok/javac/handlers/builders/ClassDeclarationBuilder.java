package lombok.javac.handlers.builders;

import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.List;
import lombok.javac.JavacNode;
import lombok.javac.JavacTreeMaker;
import openlegacy.utils.StringUtil;

import static lombok.javac.handlers.JavacOLUtil.*;

/**
 * @author Matvey Mitnitsky on 25-May-17.
 */
public class ClassDeclarationBuilder implements JavacTreeBuilder<JCTree.JCClassDecl> {

    private final JavacNode typeNode;
    private final JavacTreeMaker treeMaker;

    private String name;
    private JCTree.JCModifiers modifiers;
    private List<JCTree.JCTypeParameter> typeParameters = List.<JCTree.JCTypeParameter>nil();
    private JCTree.JCExpression extending;
    private List<JCTree.JCExpression> implementing = List.<JCTree.JCExpression>nil();
    private List<JCTree> variableDefinitions = List.<JCTree>nil();

    public ClassDeclarationBuilder(JavacNode typeNode, String name) {
        this.typeNode = typeNode;
        treeMaker = typeNode.getTreeMaker();
        this.name = name;
    }

    @Override
    public JCTree.JCClassDecl build() {
        return treeMaker.ClassDef(
                modifiers,
                typeNode.toName(name),
                typeParameters,
                extending,
                implementing,
                variableDefinitions
        );
    }

    public ClassDeclarationBuilder withModifiers(long... modifiers) {
        long mod = modifiers[0];
        for (int i = 1; i < modifiers.length; i++)
            mod |= modifiers[i];
        this.modifiers = treeMaker.Modifiers(mod);
        return this;
    }

    public ClassDeclarationBuilder withSuffix(String suffix) {
        this.name += suffix;
        return this;
    }

    public ClassDeclarationBuilder withPrefix(String prefix) {
        this.name = String.valueOf(StringUtil.prepend(name, prefix));
        return this;
    }

    public ClassDeclarationBuilder setExtends(Class<?> parentClass) {
        this.extending = getJCExpressionForType(typeNode, parentClass);
        return this;
    }

    public ClassDeclarationBuilder appendImplements(Class<?> ... interfaces){
        if(interfaces != null && interfaces.length != 0) {
            for (Class ifClass : interfaces)
                implementing = implementing.append(getJCExpressionForType(typeNode, ifClass));
        }
        return this;
    }

    public ClassDeclarationBuilder setVariableDecl(List<JCTree> variableDeclaration){
        return appendVariableDeclaration(variableDeclaration.toArray(new JCTree []{}));
    }

    public ClassDeclarationBuilder appendVariableDeclaration(JCTree ... variableDecl){
        if(variableDecl != null &&  variableDecl.length != 0) {
            for (JCTree variable : variableDecl)
                this.variableDefinitions = variableDefinitions.append(variable);
        }
        return this;
    }

    /**
     * make a research about typeParams and if there need of them
     */
    public ClassDeclarationBuilder appendTypeParams(String paramName, Class<?> ... types){
        return this;
    }

}
