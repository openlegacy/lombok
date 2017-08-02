package lombok.test;

@org.openlegacy.core.annotations.db.DbEntity
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
class DbOneInterface implements org.openlegacy.core.db.DbEntity, java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@javax.persistence.Transient
	@com.fasterxml.jackson.annotation.JsonIgnore
	@javax.xml.bind.annotation.XmlTransient
	private java.util.List<org.openlegacy.core.db.definitions.DbActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.db.definitions.DbActionDefinition>();

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
}