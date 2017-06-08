package lombok.test;
@org.openlegacy.core.annotations.db.DbEntity class DbSerialVersion implements org.openlegacy.core.db.DbEntity, java.io.Serializable {
  private @javax.persistence.Transient() java.util.List<org.openlegacy.core.db.definitions.DbActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.db.definitions.DbActionDefinition>();
  private static final long serialVersionUID = 1L;
  <clinit>() {
  }
  DbSerialVersion() {
    super();
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<org.openlegacy.core.db.definitions.DbActionDefinition> getActions() {
    return this.actions;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setActions(final java.util.List<org.openlegacy.core.db.definitions.DbActionDefinition> actions) {
    this.actions = actions;
  }
}