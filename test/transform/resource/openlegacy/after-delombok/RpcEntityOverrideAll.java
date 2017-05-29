package lombok.test;

@org.openlegacy.annotations.rpc.RpcEntity
class RpcEntityOverrideAll implements org.openlegacy.rpc.RpcEntity {
    private java.util.List<org.openlegacy.definitions.RpcActionDefinition> actions = new java.util.ArrayList<org.openlegacy.definitions.RpcActionDefinition>();

    RpcEntityOverrideAll() {
    }

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