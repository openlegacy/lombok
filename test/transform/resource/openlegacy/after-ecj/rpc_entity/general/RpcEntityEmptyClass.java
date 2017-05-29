package lombok.test;

@org.openlegacy.annotations.rpc.RpcEntity class RpcEntityEmptyClass implements org.openlegacy.rpc.RpcEntity {
  private java.util.List<org.openlegacy.definitions.RpcActionDefinition> actions = new java.util.ArrayList<org.openlegacy.definitions.RpcActionDefinition>();
  RpcEntityEmptyClass() {
    super();
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<org.openlegacy.definitions.RpcActionDefinition> getActions() {
    return this.actions;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setActions(final java.util.List<org.openlegacy.definitions.RpcActionDefinition> actions) {
    this.actions = actions;
  }
}