package lombok.test;
@org.openlegacy.core.annotations.rpc.RpcEntity @javax.xml.bind.annotation.XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD) class RpcEntityInnerClass implements org.openlegacy.core.rpc.RpcEntity {
  public static @org.openlegacy.core.annotations.rpc.RpcPart(name = "Dfhcommarea",originalName = "DFHCOMMAREA",displayName = "Dfhcommarea") @javax.xml.bind.annotation.XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD) class Dfhcommarea {
    private java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.definitions.RpcActionDefinition>();
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
  private java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.definitions.RpcActionDefinition>();
  private lombok.test.RpcEntityInnerClass.Dfhcommarea dfhcommarea;
  RpcEntityInnerClass() {
    super();
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") lombok.test.RpcEntityInnerClass.Dfhcommarea getDfhcommarea() {
    return this.dfhcommarea;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> getActions() {
    return this.actions;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setDfhcommarea(final lombok.test.RpcEntityInnerClass.Dfhcommarea dfhcommarea) {
    this.dfhcommarea = dfhcommarea;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setActions(final java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> actions) {
    this.actions = actions;
  }
}