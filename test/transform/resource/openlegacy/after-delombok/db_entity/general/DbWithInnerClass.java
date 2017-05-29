package lombok.test;

@org.openlegacy.annotations.db.DbEntity
class DbWithInnerClass implements org.openlegacy.db.DbEntity, java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@javax.persistence.Transient
	private java.util.List<org.openlegacy.db.definitions.DbActionDefinition> actions = new java.util.ArrayList<org.openlegacy.db.definitions.DbActionDefinition>();


	public static class InnerPart {
		public String var;
	}

	lombok.test.DbWithInnerClass.InnerPart inner;

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public lombok.test.DbWithInnerClass.InnerPart getInner() {
		return this.inner;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public java.util.List<org.openlegacy.db.definitions.DbActionDefinition> getActions() {
		return this.actions;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setInner(final lombok.test.DbWithInnerClass.InnerPart inner) {
		this.inner = inner;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setActions(final java.util.List<org.openlegacy.db.definitions.DbActionDefinition> actions) {
		this.actions = actions;
	}
}