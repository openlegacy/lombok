package lombok.test;
import org.openlegacy.db.DbEntity;
import java.io.Serializable;

@org.openlegacy.annotations.db.DbEntity class DbEntityNotQualifiedInterfaces implements DbEntity, Serializable {
  private static final long serialVersionUID = 1L;
  private @javax.persistence.Transient() java.util.List<org.openlegacy.db.definitions.DbActionDefinition> actions = new java.util.ArrayList<org.openlegacy.db.definitions.DbActionDefinition>();
  <clinit>() {
  }
  DbEntityNotQualifiedInterfaces() {
    super();
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<org.openlegacy.db.definitions.DbActionDefinition> getActions() {
    return this.actions;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setActions(final java.util.List<org.openlegacy.db.definitions.DbActionDefinition> actions) {
    this.actions = actions;
  }
}