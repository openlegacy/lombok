package lombok.test;

@org.openlegacy.core.annotations.rpc.RpcEntity class RpcEntityIFaceActionsAndGetter implements org.openlegacy.core.rpc.RpcEntity {
    private java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.definitions.RpcActionDefinition>();

    RpcEntityIFaceActionsAndGetter() {
        super();
    }

    public @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> getActions() {
        return this.actions;
    }
}
