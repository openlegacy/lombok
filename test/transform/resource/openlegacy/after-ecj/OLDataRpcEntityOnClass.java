package lombok.test;
@lombok.OLData(org.openlegacy.rpc.RpcEntity.class) class OLDataRpcEntityOnClass implements org.openlegacy.rpc.RpcEntity {
  private java.util.List<org.openlegacy.definitions.RpcActionDefinition> actions = new java.util.ArrayList<org.openlegacy.definitions.RpcActionDefinition>();
  private String stringField = "";
  OLDataRpcEntityOnClass() {
    super();
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") String getStringField() {
    return this.stringField;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<org.openlegacy.definitions.RpcActionDefinition> getActions() {
    return this.actions;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setStringField(final String stringField) {
    this.stringField = stringField;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setActions(final java.util.List<org.openlegacy.definitions.RpcActionDefinition> actions) {
    this.actions = actions;
  }
}
@lombok.OLData(org.openlegacy.db.DbEntity.class) @javax.persistence.IdClass(value = OLDataDbEntityOnClass.OLDataDbEntityOnClassCompositeKey.class) class OLDataDbEntityOnClass implements org.openlegacy.db.DbEntity, java.io.Serializable {
  public static @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") class OLDataDbEntityOnClassCompositeKey implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private  int field1;
    private  boolean field2;
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") int getField1() {
      return this.field1;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") boolean isField2() {
      return this.field2;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setField1(final int field1) {
      this.field1 = field1;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setField2(final boolean field2) {
      this.field2 = field2;
    }
    public @java.lang.Override @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") boolean equals(final java.lang.Object o) {
      if ((o == this))
          return true;
      if ((! (o instanceof OLDataDbEntityOnClass.OLDataDbEntityOnClassCompositeKey)))
          return false;
      final OLDataDbEntityOnClass.OLDataDbEntityOnClassCompositeKey other = (OLDataDbEntityOnClass.OLDataDbEntityOnClassCompositeKey) o;
      if ((! other.canEqual((java.lang.Object) this)))
          return false;
      if ((this.getField1() != other.getField1()))
          return false;
      if ((this.isField2() != other.isField2()))
          return false;
      return true;
    }
    protected @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") boolean canEqual(final java.lang.Object other) {
      return (other instanceof OLDataDbEntityOnClass.OLDataDbEntityOnClassCompositeKey);
    }
    public @java.lang.Override @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") int hashCode() {
      final int PRIME = 59;
      int result = 1;
      result = ((result * PRIME) + this.getField1());
      result = ((result * PRIME) + (this.isField2() ? 79 : 97));
      return result;
    }
    public @java.lang.Override @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.lang.String toString() {
      return (((("OLDataDbEntityOnClass.OLDataDbEntityOnClassCompositeKey(field1=" + this.getField1()) + ", field2=") + this.isField2()) + ")");
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") OLDataDbEntityOnClassCompositeKey() {
      super();
    }
  }
  private static final long serialVersionUID = 1L;
  private java.util.List<org.openlegacy.db.definitions.DbActionDefinition> actions = new java.util.ArrayList<org.openlegacy.db.definitions.DbActionDefinition>();
  private @javax.persistence.Id int field1;
  private @javax.persistence.Id boolean field2;
  <clinit>() {
  }
  OLDataDbEntityOnClass() {
    super();
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") int getField1() {
    return this.field1;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") boolean isField2() {
    return this.field2;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<org.openlegacy.db.definitions.DbActionDefinition> getActions() {
    return this.actions;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setField1(final int field1) {
    this.field1 = field1;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setField2(final boolean field2) {
    this.field2 = field2;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setActions(final java.util.List<org.openlegacy.db.definitions.DbActionDefinition> actions) {
    this.actions = actions;
  }
}