package lombok.eclipse.handlers.openlegacy;

/**
 * @author Matvey Mitnitsky on 22-May-17.
 */
public enum EclipsePrimitives {

    BOOLEAN("boolean"),
    CHAR("char"),
    BYTE("byte"),
    SHORT("short"),
    INT("int"),
    LONG("long"),
    FLOAT("float"),
    DOUBLE("double");

    EclipsePrimitives(String keyword){
        this.keyword = keyword;
    }

    public String keyword;
}
