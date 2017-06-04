package lombok.test;

@org.openlegacy.annotations.rpc.RpcPart
public class RpcExternalPartWithPart {
	private java.util.List<org.openlegacy.definitions.RpcActionDefinition> actions = new java.util.ArrayList<org.openlegacy.definitions.RpcActionDefinition>();


	@org.openlegacy.annotations.rpc.RpcPart
	private static class Part {
		private java.util.List<org.openlegacy.definitions.RpcActionDefinition> actions = new java.util.ArrayList<org.openlegacy.definitions.RpcActionDefinition>();
		@org.openlegacy.annotations.rpc.RpcField(length = 10)
		private String field1;
		@org.openlegacy.annotations.rpc.RpcField(length = 10)
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
		public java.util.List<org.openlegacy.definitions.RpcActionDefinition> getActions() {
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
		public void setActions(final java.util.List<org.openlegacy.definitions.RpcActionDefinition> actions) {
			this.actions = actions;
		}
	}

	@org.openlegacy.annotations.rpc.RpcField(length = 10)
	private Integer integerVar;
	private RpcExternalPartWithPart.Part part;

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public Integer getIntegerVar() {
		return this.integerVar;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public RpcExternalPartWithPart.Part getPart() {
		return this.part;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public java.util.List<org.openlegacy.definitions.RpcActionDefinition> getActions() {
		return this.actions;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setIntegerVar(final Integer integerVar) {
		this.integerVar = integerVar;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setPart(final RpcExternalPartWithPart.Part part) {
		this.part = part;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setActions(final java.util.List<org.openlegacy.definitions.RpcActionDefinition> actions) {
		this.actions = actions;
	}
}