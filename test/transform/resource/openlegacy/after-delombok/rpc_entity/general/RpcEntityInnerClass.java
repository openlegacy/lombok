package lombok.test;

@org.openlegacy.core.annotations.rpc.RpcEntity
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
class RpcEntityInnerClass implements org.openlegacy.core.rpc.RpcEntity {
	@com.fasterxml.jackson.annotation.JsonIgnore
	@javax.xml.bind.annotation.XmlTransient
	private java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.definitions.RpcActionDefinition>();


	@org.openlegacy.core.annotations.rpc.RpcPart(name = "Dfhcommarea", originalName = "DFHCOMMAREA", displayName = "Dfhcommarea")
	@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
	public static class Dfhcommarea {
		@com.fasterxml.jackson.annotation.JsonIgnore
		@javax.xml.bind.annotation.XmlTransient
		private java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.definitions.RpcActionDefinition>();

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> getActions() {
			return this.actions;
		}

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public void setActions(final java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> actions) {
			this.actions = actions;
		}
	}

	private Dfhcommarea dfhcommarea;

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public Dfhcommarea getDfhcommarea() {
		return this.dfhcommarea;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> getActions() {
		return this.actions;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setDfhcommarea(final Dfhcommarea dfhcommarea) {
		this.dfhcommarea = dfhcommarea;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setActions(final java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> actions) {
		this.actions = actions;
	}
}
