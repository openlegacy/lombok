package lombok.test;
import org.openlegacy.core.db.DbEntity;
import java.io.Serializable;

@org.openlegacy.core.annotations.db.DbEntity class DbEntityNotQualifiedInterfaces implements DbEntity, Serializable {
}