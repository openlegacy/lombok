package lombok.test;

@org.openlegacy.core.annotations.db.DbEntity
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
class DbEntityWithId implements org.openlegacy.core.db.DbEntity, java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@javax.persistence.Transient
	@com.fasterxml.jackson.annotation.JsonIgnore
	@javax.xml.bind.annotation.XmlTransient
	private java.util.List<org.openlegacy.core.db.definitions.DbActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.db.definitions.DbActionDefinition>();
	@javax.persistence.Id
	private String stringId;
	private String name;
	private String surname;
	private Integer salary;

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public String getStringId() {
		return this.stringId;
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
	public Integer getSalary() {
		return this.salary;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public java.util.List<org.openlegacy.core.db.definitions.DbActionDefinition> getActions() {
		return this.actions;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setStringId(final String stringId) {
		this.stringId = stringId;
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
	public void setSalary(final Integer salary) {
		this.salary = salary;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setActions(final java.util.List<org.openlegacy.core.db.definitions.DbActionDefinition> actions) {
		this.actions = actions;
	}
}