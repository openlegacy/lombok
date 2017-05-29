package lombok.test;

@org.openlegacy.annotations.db.DbEntity class DbEntityGeneral implements org.openlegacy.db.DbEntity, java.io.Serializable {
  private static final long serialVersionUID = 1L;
  private @javax.persistence.Transient() java.util.List<org.openlegacy.db.definitions.DbActionDefinition> actions = new java.util.ArrayList<org.openlegacy.db.definitions.DbActionDefinition>();
  <clinit>() {
  }
  DbEntityGeneral() {
    super();
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<org.openlegacy.db.definitions.DbActionDefinition> getActions() {
    return this.actions;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setActions(final java.util.List<org.openlegacy.db.definitions.DbActionDefinition> actions) {
    this.actions = actions;
  }
}