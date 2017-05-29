package lombok.javac.handlers.openlegacy;

import lombok.javac.Javac;
import lombok.javac.JavacTreeMaker;

/**
 * @author Matvey Mitnitsky on 25-May-17.
 */
public enum JavacPrimitives {

    BOOLEAN(Javac.CTC_BOOLEAN),
    CHAR(Javac.CTC_CHAR),
    BYTE(Javac.CTC_BYTE),
    SHORT(Javac.CTC_SHORT),
    INT(Javac.CTC_INT),
    LONG(Javac.CTC_LONG),
    FLOAT(Javac.CTC_FLOAT),
    DOUBLE(Javac.CTC_DOUBLE);

    JavacPrimitives(JavacTreeMaker.TypeTag typeTag) {
        this.typeTag = typeTag;
    }

    public JavacTreeMaker.TypeTag typeTag;
    }
