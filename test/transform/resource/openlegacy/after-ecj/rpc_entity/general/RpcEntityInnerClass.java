package lombok.test;

@org.openlegacy.annotations.rpc.RpcEntity class RpcEntityInnerClass implements org.openlegacy.rpc.RpcEntity {
  public static @org.openlegacy.annotations.rpc.RpcPart(name = "Dfhcommarea",originalName = "DFHCOMMAREA",displayName = "Dfhcommarea") class Dfhcommarea {
    public Dfhcommarea() {
      super();
    }
  }
  private java.util.List<org.openlegacy.definitions.RpcActionDefinition> actions = new java.util.ArrayList<org.openlegacy.definitions.RpcActionDefinition>();
  private Dfhcommarea dfhcommarea;
  RpcEntityInnerClass() {
    super();
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") Dfhcommarea getDfhcommarea() {
    return this.dfhcommarea;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<org.openlegacy.definitions.RpcActionDefinition> getActions() {
    return this.actions;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setDfhcommarea(final Dfhcommarea dfhcommarea) {
    this.dfhcommarea = dfhcommarea;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setActions(final java.util.List<org.openlegacy.definitions.RpcActionDefinition> actions) {
    this.actions = actions;
  }
}