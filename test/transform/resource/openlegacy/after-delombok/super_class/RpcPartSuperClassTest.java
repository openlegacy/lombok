package lombok.test;

@org.openlegacy.core.annotations.rpc.RpcPartSuperClass
class RpcPartSuperClassTest {
	private java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.definitions.RpcActionDefinition>();
	private String superClassField;
	private Part part;


	@org.openlegacy.core.annotations.rpc.RpcPart
	public static class Part {
		private java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.definitions.RpcActionDefinition>();
		private String innerField;

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public String getInnerField() {
			return this.innerField;
		}

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> getActions() {
			return this.actions;
		}

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public void setInnerField(final String innerField) {
			this.innerField = innerField;
		}

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public void setActions(final java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> actions) {
			this.actions = actions;
		}
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public String getSuperClassField() {
		return this.superClassField;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public Part getPart() {
		return this.part;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> getActions() {
		return this.actions;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setSuperClassField(final String superClassField) {
		this.superClassField = superClassField;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setPart(final Part part) {
		this.part = part;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setActions(final java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> actions) {
		this.actions = actions;
	}
}