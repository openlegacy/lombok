package lombok.test;

@org.openlegacy.annotations.rpc.RpcEntity
class RpcEntityInnerClass implements org.openlegacy.rpc.RpcEntity {
	private java.util.List<org.openlegacy.definitions.RpcActionDefinition> actions = new java.util.ArrayList<org.openlegacy.definitions.RpcActionDefinition>();


	@org.openlegacy.annotations.rpc.RpcPart(name = "Dfhcommarea", originalName = "DFHCOMMAREA", displayName = "Dfhcommarea")
	public static class Dfhcommarea {
		private java.util.List<org.openlegacy.definitions.RpcActionDefinition> actions = new java.util.ArrayList<org.openlegacy.definitions.RpcActionDefinition>();

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public java.util.List<org.openlegacy.definitions.RpcActionDefinition> getActions() {
			return this.actions;
		}

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public void setActions(final java.util.List<org.openlegacy.definitions.RpcActionDefinition> actions) {
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
	public java.util.List<org.openlegacy.definitions.RpcActionDefinition> getActions() {
		return this.actions;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setDfhcommarea(final Dfhcommarea dfhcommarea) {
		this.dfhcommarea = dfhcommarea;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setActions(final java.util.List<org.openlegacy.definitions.RpcActionDefinition> actions) {
		this.actions = actions;
	}
}
