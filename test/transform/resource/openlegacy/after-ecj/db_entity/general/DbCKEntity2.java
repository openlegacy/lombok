package lombok.test;

@org.openlegacy.annotations.db.DbEntity @javax.persistence.IdClass(value = DbCKEntity2.DbCKEntity2CompositeKey.class) class DbCKEntity2 implements org.openlegacy.db.DbEntity, java.io.Serializable {
  public static @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") class DbCKEntity2CompositeKey implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private  String name;
    private  String surname;
    private  byte age;
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") String getName() {
      return this.name;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") String getSurname() {
      return this.surname;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") byte getAge() {
      return this.age;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setName(final String name) {
      this.name = name;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setSurname(final String surname) {
      this.surname = surname;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setAge(final byte age) {
      this.age = age;
    }
    public @java.lang.Override @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") boolean equals(final java.lang.Object o) {
      if ((o == this))
          return true;
      if ((! (o instanceof DbCKEntity2.DbCKEntity2CompositeKey)))
          return false;
      final DbCKEntity2.DbCKEntity2CompositeKey other = (DbCKEntity2.DbCKEntity2CompositeKey) o;
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
      if ((this.getAge() != other.getAge()))
          return false;
      return true;
    }
    protected @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") boolean canEqual(final java.lang.Object other) {
      return (other instanceof DbCKEntity2.DbCKEntity2CompositeKey);
    }
    public @java.lang.Override @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") int hashCode() {
      final int PRIME = 59;
      int result = 1;
      final java.lang.Object $name = this.getName();
      result = ((result * PRIME) + (($name == null) ? 43 : $name.hashCode()));
      final java.lang.Object $surname = this.getSurname();
      result = ((result * PRIME) + (($surname == null) ? 43 : $surname.hashCode()));
      result = ((result * PRIME) + this.getAge());
      return result;
    }
    public @java.lang.Override @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.lang.String toString() {
      return (((((("DbCKEntity2.DbCKEntity2CompositeKey(name=" + this.getName()) + ", surname=") + this.getSurname()) + ", age=") + this.getAge()) + ")");
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") DbCKEntity2CompositeKey() {
      super();
    }
  }
  private static final long serialVersionUID = 1L;
  private java.util.List<org.openlegacy.db.definitions.DbActionDefinition> actions = new java.util.ArrayList<org.openlegacy.db.definitions.DbActionDefinition>();
  private @javax.persistence.Id String name;
  private @javax.persistence.Id String surname;
  private @javax.persistence.Id byte age;
  private Integer salary;
  <clinit>() {
  }
  DbCKEntity2() {
    super();
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") String getName() {
    return this.name;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") String getSurname() {
    return this.surname;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") byte getAge() {
    return this.age;
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
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setAge(final byte age) {
    this.age = age;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setSalary(final Integer salary) {
    this.salary = salary;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setActions(final java.util.List<org.openlegacy.db.definitions.DbActionDefinition> actions) {
    this.actions = actions;
  }
}