package lombok.test;
@org.openlegacy.core.annotations.rpc.RpcEntity @javax.xml.bind.annotation.XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD) class RpcEntityInnerClass implements org.openlegacy.core.rpc.RpcEntity {
  public static @org.openlegacy.core.annotations.rpc.RpcPart(name = "Dfhcommarea",originalName = "DFHCOMMAREA",displayName = "Dfhcommarea") @javax.xml.bind.annotation.XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD) class Dfhcommarea {
    private @com.fasterxml.jackson.annotation.JsonIgnore() @javax.xml.bind.annotation.XmlTransient() java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.definitions.RpcActionDefinition>();
    public Dfhcommarea() {
      super();
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> getActions() {
      return this.actions;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setActions(final java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> actions) {
      this.actions = actions;
    }
  }
  private @com.fasterxml.jackson.annotation.JsonIgnore() @javax.xml.bind.annotation.XmlTransient() java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.definitions.RpcActionDefinition>();
  private Dfhcommarea dfhcommarea;
  RpcEntityInnerClass() {
    super();
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") Dfhcommarea getDfhcommarea() {
    return this.dfhcommarea;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> getActions() {
    return this.actions;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setDfhcommarea(final Dfhcommarea dfhcommarea) {
    this.dfhcommarea = dfhcommarea;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setActions(final java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> actions) {
    this.actions = actions;
  }
}
