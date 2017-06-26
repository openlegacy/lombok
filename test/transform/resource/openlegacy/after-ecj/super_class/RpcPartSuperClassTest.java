package lombok.test;
@org.openlegacy.core.annotations.rpc.RpcPartSuperClass @javax.xml.bind.annotation.XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD) class RpcPartSuperClassTest {
  public static @org.openlegacy.core.annotations.rpc.RpcPart @javax.xml.bind.annotation.XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD) class Part {
    private @com.fasterxml.jackson.annotation.JsonIgnore() @javax.xml.bind.annotation.XmlTransient() java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.definitions.RpcActionDefinition>();
    private String innerField;
    public Part() {
      super();
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") String getInnerField() {
      return this.innerField;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> getActions() {
      return this.actions;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setInnerField(final String innerField) {
      this.innerField = innerField;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setActions(final java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> actions) {
      this.actions = actions;
    }
  }
  private @com.fasterxml.jackson.annotation.JsonIgnore() @javax.xml.bind.annotation.XmlTransient() java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.definitions.RpcActionDefinition>();
  private String superClassField;
  private Part part;
  RpcPartSuperClassTest() {
    super();
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") String getSuperClassField() {
    return this.superClassField;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") Part getPart() {
    return this.part;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> getActions() {
    return this.actions;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setSuperClassField(final String superClassField) {
    this.superClassField = superClassField;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setPart(final Part part) {
    this.part = part;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setActions(final java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> actions) {
    this.actions = actions;
  }
}
