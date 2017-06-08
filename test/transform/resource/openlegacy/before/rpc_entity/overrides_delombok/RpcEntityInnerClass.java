package lombok.test;

@org.openlegacy.core.annotations.rpc.RpcEntity class RpcEntityInnerClass implements org.openlegacy.core.rpc.RpcEntity {
	private java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.definitions.RpcActionDefinition>();

    public static @org.openlegacy.core.annotations.rpc.RpcPart(name = "Dfhcommarea",originalName = "DFHCOMMAREA",displayName = "Dfhcommarea") class Dfhcommarea {
	        public Dfhcommarea() {
	            super();
	        }
	}
	 
	private lombok.test.RpcEntityInnerClass.Dfhcommarea dfhcommarea;
   
    
    @java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public lombok.test.RpcEntityInnerClass.Dfhcommarea getDfhcommarea() {
		return this.dfhcommarea;
	}

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
