package lombok.test;

@org.openlegacy.core.annotations.rpc.RpcEntitySuperClass
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
class RpcEntitySuperClassTest {
	@com.fasterxml.jackson.annotation.JsonIgnore
	@javax.xml.bind.annotation.XmlTransient
	private java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.definitions.RpcActionDefinition>();
	private String superClassField;
	private Part part;


	@org.openlegacy.core.annotations.rpc.RpcPart
	@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
	public static class Part {
		@com.fasterxml.jackson.annotation.JsonIgnore
		@javax.xml.bind.annotation.XmlTransient
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
