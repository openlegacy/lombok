package lombok.test;
@org.openlegacy.core.annotations.rpc.RpcEntity @javax.xml.bind.annotation.XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD) class RpcEntityEmptyClass implements org.openlegacy.core.rpc.RpcEntity {
  private @com.fasterxml.jackson.annotation.JsonIgnore() @javax.xml.bind.annotation.XmlTransient() java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.definitions.RpcActionDefinition>();
  RpcEntityEmptyClass() {
    super();
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> getActions() {
    return this.actions;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setActions(final java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> actions) {
    this.actions = actions;
  }
}