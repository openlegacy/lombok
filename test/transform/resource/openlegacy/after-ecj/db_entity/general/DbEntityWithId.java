package lombok.test;

@org.openlegacy.annotations.db.DbEntity class DbEntityWithId implements org.openlegacy.db.DbEntity, java.io.Serializable {
  private static final long serialVersionUID = 1L;
  private @javax.persistence.Transient() java.util.List<org.openlegacy.db.definitions.DbActionDefinition> actions = new java.util.ArrayList<org.openlegacy.db.definitions.DbActionDefinition>();
  private @javax.persistence.Id String stringId;
  private String name;
  private String surname;
  private Integer salary;
  <clinit>() {
  }
  DbEntityWithId() {
    super();
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") String getStringId() {
    return this.stringId;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") String getName() {
    return this.name;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") String getSurname() {
    return this.surname;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") Integer getSalary() {
    return this.salary;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<org.openlegacy.db.definitions.DbActionDefinition> getActions() {
    return this.actions;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setStringId(final String stringId) {
    this.stringId = stringId;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setName(final String name) {
    this.name = name;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setSurname(final String surname) {
    this.surname = surname;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setSalary(final Integer salary) {
    this.salary = salary;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setActions(final java.util.List<org.openlegacy.db.definitions.DbActionDefinition> actions) {
    this.actions = actions;
  }
}