package lombok.test;

@org.openlegacy.core.annotations.db.DbEntity
@javax.persistence.IdClass(CKEntity1.CKEntity1CompositeKey.class)
class CKEntity1 implements org.openlegacy.core.db.DbEntity, java.io.Serializable {

  @java.lang.SuppressWarnings("all")
  @javax.annotation.Generated("lombok")
  public static class CKEntity1CompositeKey implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String surname;

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public String getName() {
      return this.name;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public String getSurname() {
      return this.surname;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public void setName(final String name) {
      this.name = name;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public void setSurname(final String surname) {
      this.surname = surname;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public CKEntity1CompositeKey() {
    }
  }

  private static final long serialVersionUID = 1L;
  private java.util.List<org.openlegacy.core.db.definitions.DbActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.db.definitions.DbActionDefinition>();
  @javax.persistence.Id
  private String name;
  @javax.persistence.Id
  private String surname;

  CKEntity1() {
  }

  @java.lang.SuppressWarnings("all")
  @javax.annotation.Generated("lombok")
  public java.util.List<org.openlegacy.core.db.definitions.DbActionDefinition> getActions() {
    return this.actions;
  }

  @java.lang.SuppressWarnings("all")
  @javax.annotation.Generated("lombok")
  public void setActions(final java.util.List<org.openlegacy.core.db.definitions.DbActionDefinition> actions) {
    this.actions = actions;
  }

  @java.lang.SuppressWarnings("all")
  @javax.annotation.Generated("lombok")
  public String getName() {
    return this.name;
  }

  @java.lang.SuppressWarnings("all")
  @javax.annotation.Generated("lombok")
  public String getSurname() {
    return this.surname;
  }

  @java.lang.SuppressWarnings("all")
  @javax.annotation.Generated("lombok")
  public void setName(final String name) {
    this.name = name;
  }

  @java.lang.SuppressWarnings("all")
  @javax.annotation.Generated("lombok")
  public void setSurname(final String surname) {
    this.surname = surname;
  }
}