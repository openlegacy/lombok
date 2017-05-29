package lombok.test;

@org.openlegacy.annotations.db.DbEntity
class DbOneInterface2 implements java.io.Serializable, org.openlegacy.db.DbEntity {
	private static final long serialVersionUID = 1L;
	@javax.persistence.Transient
	private java.util.List<org.openlegacy.db.definitions.DbActionDefinition> actions = new java.util.ArrayList<org.openlegacy.db.definitions.DbActionDefinition>();

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public java.util.List<org.openlegacy.db.definitions.DbActionDefinition> getActions() {
		return this.actions;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setActions(final java.util.List<org.openlegacy.db.definitions.DbActionDefinition> actions) {
		this.actions = actions;
	}
}