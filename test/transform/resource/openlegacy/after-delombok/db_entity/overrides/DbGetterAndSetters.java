package lombok.test;

@org.openlegacy.annotations.db.DbEntity
class DbGetterAndSetters implements org.openlegacy.db.DbEntity, java.io.Serializable {
  private static final long serialVersionUID = 1L;
  private java.util.List<org.openlegacy.db.definitions.DbActionDefinition> actions = new java.util.ArrayList<org.openlegacy.db.definitions.DbActionDefinition>();

  DbGetterAndSetters() {
  }

  @java.lang.SuppressWarnings("all")
  @javax.annotation.Generated("lombok")
  public java.util.List<org.openlegacy.db.definitions.DbActionDefinition> getActions() {
    return this.actions;
  }

  @java.lang.SuppressWarnings("all")
  @javax.annotation.Generated("lombok")
  public void setActions(final java.util.List<org.openlegacy.db.definitions.DbActionDefinition> actions) {
    this.actions = actions;
  }
}
