package lombok.test;

@org.openlegacy.annotations.db.DbEntity @javax.persistence.IdClass(value = CKEntity.CKEntityCompositeKey.class) class CKEntity implements org.openlegacy.db.DbEntity, java.io.Serializable {
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
    public @java.lang.Override @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") boolean equals(final java.lang.Object o) {
      if ((o == this))
          return true;
      if ((! (o instanceof CKEntity.CKEntityCompositeKey)))
          return false;
      final CKEntity.CKEntityCompositeKey other = (CKEntity.CKEntityCompositeKey) o;
      if ((! other.canEqual((java.lang.Object) this)))
          return false;
      final java.lang.Object this$name = this.getName();
      final java.lang.Object other$name = other.getName();
      if (((this$name == null) ? (other$name != null) : (! this$name.equals(other$name))))
          return false;
      final java.lang.Object this$surname = this.getSurname();
      final java.lang.Object other$surname = other.getSurname();
      if (((this$surname == null) ? (other$surname != null) : (! this$surname.equals(other$surname))))
          return false;
      return true;
    }
    protected @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") boolean canEqual(final java.lang.Object other) {
      return (other instanceof CKEntity.CKEntityCompositeKey);
    }
    public @java.lang.Override @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") int hashCode() {
      final int PRIME = 59;
      int result = 1;
      final java.lang.Object $name = this.getName();
      result = ((result * PRIME) + (($name == null) ? 43 : $name.hashCode()));
      final java.lang.Object $surname = this.getSurname();
      result = ((result * PRIME) + (($surname == null) ? 43 : $surname.hashCode()));
      return result;
    }
    public @java.lang.Override @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.lang.String toString() {
      return (((("CKEntity.CKEntityCompositeKey(name=" + this.getName()) + ", surname=") + this.getSurname()) + ")");
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") CKEntityCompositeKey() {
      super();
    }
  }
  private static final long serialVersionUID = 1L;
  private @javax.persistence.Transient() java.util.List<org.openlegacy.db.definitions.DbActionDefinition> actions = new java.util.ArrayList<org.openlegacy.db.definitions.DbActionDefinition>();
  private @javax.persistence.Id String name;
  private @javax.persistence.Id String surname;
  private Integer salary;
  <clinit>() {
  }
  CKEntity() {
    super();
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
