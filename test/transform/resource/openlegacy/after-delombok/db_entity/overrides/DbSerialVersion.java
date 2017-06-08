package lombok.test;

@org.openlegacy.core.annotations.db.DbEntity
class DbSerialVersion implements org.openlegacy.core.db.DbEntity, java.io.Serializable {
	@javax.persistence.Transient
	private java.util.List<org.openlegacy.core.db.definitions.DbActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.db.definitions.DbActionDefinition>();
	private static final long serialVersionUID = 1L;

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