package lombok.test;
public @org.openlegacy.annotations.rpc.RpcEntity class RpcInternalPartWithListPart implements org.openlegacy.rpc.RpcEntity {
  private static @org.openlegacy.annotations.rpc.RpcPart class Part {
    private java.util.List<org.openlegacy.definitions.RpcActionDefinition> actions = new java.util.ArrayList<org.openlegacy.definitions.RpcActionDefinition>();
    private @org.openlegacy.annotations.rpc.RpcField(length = 10) String field1;
    private @org.openlegacy.annotations.rpc.RpcField(length = 10) Integer field2;
    private Part() {
      super();
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") String getField1() {
      return this.field1;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") Integer getField2() {
      return this.field2;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<org.openlegacy.definitions.RpcActionDefinition> getActions() {
      return this.actions;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setField1(final String field1) {
      this.field1 = field1;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setField2(final Integer field2) {
      this.field2 = field2;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setActions(final java.util.List<org.openlegacy.definitions.RpcActionDefinition> actions) {
      this.actions = actions;
    }
  }
  private java.util.List<org.openlegacy.definitions.RpcActionDefinition> partActions = new java.util.ArrayList<org.openlegacy.definitions.RpcActionDefinition>();
  private java.util.List<org.openlegacy.definitions.RpcActionDefinition> actions = new java.util.ArrayList<org.openlegacy.definitions.RpcActionDefinition>();
  private @org.openlegacy.annotations.rpc.RpcField(length = 10) java.util.List<RpcInternalPartWithListPart.Part> part;
  public RpcInternalPartWithListPart() {
    super();
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<RpcInternalPartWithListPart.Part> getPart() {
    return this.part;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<org.openlegacy.definitions.RpcActionDefinition> getActions() {
    return this.actions;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<org.openlegacy.definitions.RpcActionDefinition> getPartActions() {
    return this.partActions;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setPart(final java.util.List<RpcInternalPartWithListPart.Part> part) {
    this.part = part;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setActions(final java.util.List<org.openlegacy.definitions.RpcActionDefinition> actions) {
    this.actions = actions;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setPartActions(final java.util.List<org.openlegacy.definitions.RpcActionDefinition> partActions) {
    this.partActions = partActions;
  }
}