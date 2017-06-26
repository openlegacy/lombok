package lombok.test;
@org.openlegacy.core.annotations.rpc.RpcEntity @javax.xml.bind.annotation.XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD) class RpcEntityNonPrimitives implements org.openlegacy.core.rpc.RpcEntity {
  private @com.fasterxml.jackson.annotation.JsonIgnore() @javax.xml.bind.annotation.XmlTransient() java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.definitions.RpcActionDefinition>();
  private String stringVar = "string";
  private Object objectVar = new Object();
  private java.util.List<String> stringList = new java.util.ArrayList<>();
  private java.util.Set<String> stringSet = new java.util.HashSet<>();
  private java.util.Map<String, String> stringMap = new java.util.HashMap<>();
  RpcEntityNonPrimitives() {
    super();
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") String getStringVar() {
    return this.stringVar;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") Object getObjectVar() {
    return this.objectVar;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<String> getStringList() {
    return this.stringList;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.Set<String> getStringSet() {
    return this.stringSet;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.Map<String, String> getStringMap() {
    return this.stringMap;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> getActions() {
    return this.actions;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setStringVar(final String stringVar) {
    this.stringVar = stringVar;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setObjectVar(final Object objectVar) {
    this.objectVar = objectVar;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setStringList(final java.util.List<String> stringList) {
    this.stringList = stringList;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setStringSet(final java.util.Set<String> stringSet) {
    this.stringSet = stringSet;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setStringMap(final java.util.Map<String, String> stringMap) {
    this.stringMap = stringMap;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setActions(final java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> actions) {
    this.actions = actions;
  }
}