package lombok.test;
import org.openlegacy.db.DbEntity;
import java.io.Serializable;

@org.openlegacy.annotations.db.DbEntity class DbEntityNotQualifiedInterfaces implements DbEntity, Serializable {
}