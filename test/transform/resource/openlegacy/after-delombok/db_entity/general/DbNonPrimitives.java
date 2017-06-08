package lombok.test;

@org.openlegacy.core.annotations.db.DbEntity
class DbNonPrimitives implements org.openlegacy.core.db.DbEntity, java.io.Serializable {
    private static final long serialVersionUID = 1L;
    @javax.persistence.Transient
    private java.util.List<org.openlegacy.core.db.definitions.DbActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.db.definitions.DbActionDefinition>();
    private String stringVar = "string";
    private Object objectVar = new Object();
    private java.util.List<String> stringList = new java.util.ArrayList<>();
    private java.util.Set<String> stringSet = new java.util.HashSet<>();
    private java.util.Map<String, String> stringMap = new java.util.HashMap<>();

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public String getStringVar() {
        return this.stringVar;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public Object getObjectVar() {
        return this.objectVar;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public java.util.List<String> getStringList() {
        return this.stringList;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public java.util.Set<String> getStringSet() {
        return this.stringSet;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public java.util.Map<String, String> getStringMap() {
        return this.stringMap;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public java.util.List<org.openlegacy.core.db.definitions.DbActionDefinition> getActions() {
        return this.actions;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public void setStringVar(final String stringVar) {
        this.stringVar = stringVar;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public void setObjectVar(final Object objectVar) {
        this.objectVar = objectVar;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public void setStringList(final java.util.List<String> stringList) {
        this.stringList = stringList;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public void setStringSet(final java.util.Set<String> stringSet) {
        this.stringSet = stringSet;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public void setStringMap(final java.util.Map<String, String> stringMap) {
        this.stringMap = stringMap;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public void setActions(final java.util.List<org.openlegacy.core.db.definitions.DbActionDefinition> actions) {
        this.actions = actions;
    }
}
