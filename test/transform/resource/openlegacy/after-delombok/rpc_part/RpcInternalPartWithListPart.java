package lombok.test;

@org.openlegacy.core.annotations.rpc.RpcEntity
public class RpcInternalPartWithListPart implements org.openlegacy.core.rpc.RpcEntity {
	private java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> partActions = new java.util.ArrayList<org.openlegacy.core.definitions.RpcActionDefinition>();
	private java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.definitions.RpcActionDefinition>();


	@org.openlegacy.core.annotations.rpc.RpcPart
	private static class Part {
		private java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.definitions.RpcActionDefinition>();
		@org.openlegacy.core.annotations.rpc.RpcField(length = 10)
		private String field1;
		@org.openlegacy.core.annotations.rpc.RpcField(length = 10)
		private Integer field2;

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public String getField1() {
			return this.field1;
		}

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public Integer getField2() {
			return this.field2;
		}

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> getActions() {
			return this.actions;
		}

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public void setField1(final String field1) {
			this.field1 = field1;
		}

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public void setField2(final Integer field2) {
			this.field2 = field2;
		}

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public void setActions(final java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> actions) {
			this.actions = actions;
		}
	}

	@org.openlegacy.core.annotations.rpc.RpcField(length = 10)
	private java.util.List<RpcInternalPartWithListPart.Part> part;

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public java.util.List<RpcInternalPartWithListPart.Part> getPart() {
		return this.part;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> getActions() {
		return this.actions;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> getPartActions() {
		return this.partActions;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setPart(final java.util.List<RpcInternalPartWithListPart.Part> part) {
		this.part = part;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setActions(final java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> actions) {
		this.actions = actions;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setPartActions(final java.util.List<org.openlegacy.core.definitions.RpcActionDefinition> partActions) {
		this.partActions = partActions;
	}
}