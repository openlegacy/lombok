package lombok.test;

@org.openlegacy.annotations.db.DbEntity
class DbCKEntity2 implements org.openlegacy.db.DbEntity, java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@javax.persistence.Transient
	private java.util.List<org.openlegacy.db.definitions.DbActionDefinition> actions = new java.util.ArrayList<org.openlegacy.db.definitions.DbActionDefinition>();
	@javax.persistence.Id
	private String name;
	@javax.persistence.Id
	private String surname;
	@javax.persistence.Id
	private byte age;
	private Integer salary;


	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public static class DbCKEntity2CompositeKey implements java.io.Serializable {
		private static final long serialVersionUID = 1L;
		private String name;
		private String surname;
		private byte age;

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public DbCKEntity2CompositeKey() {
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
		public byte getAge() {
			return this.age;
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
		public void setAge(final byte age) {
			this.age = age;
		}

		@java.lang.Override
		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public boolean equals(final java.lang.Object o) {
			if (o == this) return true;
			if (!(o instanceof DbCKEntity2.DbCKEntity2CompositeKey)) return false;
			final DbCKEntity2.DbCKEntity2CompositeKey other = (DbCKEntity2.DbCKEntity2CompositeKey) o;
			if (!other.canEqual((java.lang.Object) this)) return false;
			final java.lang.Object this$name = this.getName();
			final java.lang.Object other$name = other.getName();
			if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
			final java.lang.Object this$surname = this.getSurname();
			final java.lang.Object other$surname = other.getSurname();
			if (this$surname == null ? other$surname != null : !this$surname.equals(other$surname)) return false;
			if (this.getAge() != other.getAge()) return false;
			return true;
		}

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		protected boolean canEqual(final java.lang.Object other) {
			return other instanceof DbCKEntity2.DbCKEntity2CompositeKey;
		}

		@java.lang.Override
		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public int hashCode() {
			final int PRIME = 59;
			int result = 1;
			final java.lang.Object $name = this.getName();
			result = result * PRIME + ($name == null ? 43 : $name.hashCode());
			final java.lang.Object $surname = this.getSurname();
			result = result * PRIME + ($surname == null ? 43 : $surname.hashCode());
			result = result * PRIME + this.getAge();
			return result;
		}

		@java.lang.Override
		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public java.lang.String toString() {
			return "DbCKEntity2.DbCKEntity2CompositeKey(name=" + this.getName() + ", surname=" + this.getSurname() + ", age=" + this.getAge() + ")";
		}
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
	public byte getAge() {
		return this.age;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public Integer getSalary() {
		return this.salary;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public java.util.List<org.openlegacy.db.definitions.DbActionDefinition> getActions() {
		return this.actions;
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
	public void setAge(final byte age) {
		this.age = age;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setSalary(final Integer salary) {
		this.salary = salary;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setActions(final java.util.List<org.openlegacy.db.definitions.DbActionDefinition> actions) {
		this.actions = actions;
	}
}
