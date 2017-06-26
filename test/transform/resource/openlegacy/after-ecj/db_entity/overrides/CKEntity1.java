package lombok.test;
@org.openlegacy.core.annotations.db.DbEntity @javax.persistence.IdClass(value = CKEntity1.CKEntity1CompositeKey.class) @javax.xml.bind.annotation.XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD) class CKEntity1 implements org.openlegacy.core.db.DbEntity, java.io.Serializable {
  public static @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") class CKEntity1CompositeKey implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String surname;
    <clinit>() {
    }
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
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") CKEntity1CompositeKey() {
      super();
    }
  }
  private static final long serialVersionUID = 1L;
  private java.util.List<org.openlegacy.core.db.definitions.DbActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.db.definitions.DbActionDefinition>();
  private @javax.persistence.Id String name;
  private @javax.persistence.Id String surname;
  <clinit>() {
  }
  CKEntity1() {
    super();
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<org.openlegacy.core.db.definitions.DbActionDefinition> getActions() {
    return this.actions;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setActions(final java.util.List<org.openlegacy.core.db.definitions.DbActionDefinition> actions) {
    this.actions = actions;
  }
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
}