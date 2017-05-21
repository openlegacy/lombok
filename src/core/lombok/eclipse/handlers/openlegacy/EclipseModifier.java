package lombok.eclipse.handlers.openlegacy;

import static org.eclipse.jdt.internal.compiler.classfmt.ClassFileConstants.*;

/**
 * @author Matvey Mitnitsky
 * @since 3.6.0-SNAPSHOT
 */

public enum EclipseModifier {

    PUBLIC(AccPublic),
    PRIVATE(AccPrivate),
    PROTECTED(AccProtected),
    STATIC(AccStatic),
    FINAL(AccFinal),
    SYNCHRONIZED(AccSynchronized),
    VOLATILE(AccVolatile),
    INTERFACE(AccInterface),
    ABSTRACT(AccAbstract),
    ANNOTATION(AccAnnotation),
    ENUM(AccEnum);

    public final int modifierConstant;

    EclipseModifier(int modifierConstant) {
        this.modifierConstant = modifierConstant;
    }

}
