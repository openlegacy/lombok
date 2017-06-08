package lombok.test;
@org.openlegacy.core.annotations.db.DbEntity class DbWithInnerClass implements org.openlegacy.core.db.DbEntity, java.io.Serializable {
  public static class InnerPart {
    public String var;
    public InnerPart() {
      super();
    }
  }
  private static final long serialVersionUID = 1L;
  private @javax.persistence.Transient() java.util.List<org.openlegacy.core.db.definitions.DbActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.db.definitions.DbActionDefinition>();
  lombok.test.DbWithInnerClass.InnerPart inner;
  <clinit>() {
  }
  DbWithInnerClass() {
    super();
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") lombok.test.DbWithInnerClass.InnerPart getInner() {
    return this.inner;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<org.openlegacy.core.db.definitions.DbActionDefinition> getActions() {
    return this.actions;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setInner(final lombok.test.DbWithInnerClass.InnerPart inner) {
    this.inner = inner;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setActions(final java.util.List<org.openlegacy.core.db.definitions.DbActionDefinition> actions) {
    this.actions = actions;
  }
}