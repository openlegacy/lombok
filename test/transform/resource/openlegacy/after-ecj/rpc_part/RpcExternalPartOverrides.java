package lombok.test;
public @org.openlegacy.core.annotations.rpc.RpcPart @javax.xml.bind.annotation.XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD) class RpcExternalPartOverrides {
  private static @org.openlegacy.core.annotations.rpc.RpcPart @javax.xml.bind.annotation.XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD) class Part {
    private @com.fasterxml.jackson.annotation.JsonIgnore() @javax.xml.bind.annotation.XmlTransient() java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.definitions.RpcActionDefinition>();
    private @org.openlegacy.core.annotations.rpc.RpcField(length = 10) String field1;
    private @org.openlegacy.core.annotations.rpc.RpcField(length = 10) Integer field2;
    private Part() {
      super();
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") String getField1() {
      return this.field1;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") Integer getField2() {
      return this.field2;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> getActions() {
      return this.actions;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setField1(final String field1) {
      this.field1 = field1;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setField2(final Integer field2) {
      this.field2 = field2;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setActions(final java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> actions) {
      this.actions = actions;
    }
  }
  private @com.fasterxml.jackson.annotation.JsonIgnore() @javax.xml.bind.annotation.XmlTransient() java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.definitions.RpcActionDefinition>();
  private @org.openlegacy.core.annotations.rpc.RpcField(length = 10) short shortVar;
  private @org.openlegacy.core.annotations.rpc.RpcField(length = 10) int integerVar;
  private @org.openlegacy.core.annotations.rpc.RpcField(length = 10) String stringVar = "string";
  private @org.openlegacy.core.annotations.rpc.RpcField(length = 10) Object objectVar = new Object();
  private @org.openlegacy.core.annotations.rpc.RpcField(length = 10) java.util.List<RpcExternalPartOverrides.Part> part;
  public RpcExternalPartOverrides() {
    super();
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") short getShortVar() {
    return this.shortVar;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") int getIntegerVar() {
    return this.integerVar;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") String getStringVar() {
    return this.stringVar;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") Object getObjectVar() {
    return this.objectVar;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<RpcExternalPartOverrides.Part> getPart() {
    return this.part;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> getActions() {
    return this.actions;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setShortVar(final short shortVar) {
    this.shortVar = shortVar;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setIntegerVar(final int integerVar) {
    this.integerVar = integerVar;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setStringVar(final String stringVar) {
    this.stringVar = stringVar;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setObjectVar(final Object objectVar) {
    this.objectVar = objectVar;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setPart(final java.util.List<RpcExternalPartOverrides.Part> part) {
    this.part = part;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setActions(final java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> actions) {
    this.actions = actions;
  }
}