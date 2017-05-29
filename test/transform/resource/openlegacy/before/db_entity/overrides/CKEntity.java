package lombok.test;

@org.openlegacy.annotations.db.DbEntity class CKEntity implements org.openlegacy.db.DbEntity, java.io.Serializable {
 public static @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") class CKEntityCompositeKey implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private  String name;
    private  String surname;
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") String getName() {
      return this.name;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") String getSurname() {
      return this.surname;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setName(final String name) {
      this.name = name;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setSurname(final String surname) {
      this.surname = surname;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") CKEntityCompositeKey() {
      super();
    }
  }
  private static final long serialVersionUID = 1L;
  private java.util.List<org.openlegacy.db.definitions.DbActionDefinition> actions = new java.util.ArrayList<org.openlegacy.db.definitions.DbActionDefinition>();
  @javax.persistence.Id private String name;
  @javax.persistence.Id private String surname;
  CKEntity() {
    super();
  }
  
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<org.openlegacy.db.definitions.DbActionDefinition> getActions() {
    return this.actions;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setActions(final java.util.List<org.openlegacy.db.definitions.DbActionDefinition> actions) {
    this.actions = actions;
  }
}