package lombok.test;
@org.openlegacy.annotations.rpc.RpcEntity class RpcEntityInnerClass implements org.openlegacy.rpc.RpcEntity {
  public static @org.openlegacy.annotations.rpc.RpcPart(name = "Dfhcommarea",originalName = "DFHCOMMAREA",displayName = "Dfhcommarea") class Dfhcommarea {
    private java.util.List<org.openlegacy.definitions.RpcActionDefinition> actions = new java.util.ArrayList<org.openlegacy.definitions.RpcActionDefinition>();
    public Dfhcommarea() {
      super();
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<org.openlegacy.definitions.RpcActionDefinition> getActions() {
      return this.actions;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setActions(final java.util.List<org.openlegacy.definitions.RpcActionDefinition> actions) {
      this.actions = actions;
    }
  }
  private java.util.List<org.openlegacy.definitions.RpcActionDefinition> actions = new java.util.ArrayList<org.openlegacy.definitions.RpcActionDefinition>();
  private lombok.test.RpcEntityInnerClass.Dfhcommarea dfhcommarea;
  RpcEntityInnerClass() {
    super();
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") lombok.test.RpcEntityInnerClass.Dfhcommarea getDfhcommarea() {
    return this.dfhcommarea;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<org.openlegacy.definitions.RpcActionDefinition> getActions() {
    return this.actions;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setDfhcommarea(final lombok.test.RpcEntityInnerClass.Dfhcommarea dfhcommarea) {
    this.dfhcommarea = dfhcommarea;
  }
}