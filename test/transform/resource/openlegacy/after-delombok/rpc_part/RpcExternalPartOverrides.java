package lombok.test;

@org.openlegacy.annotations.rpc.RpcPart
public class RpcExternalPartOverrides {
	private java.util.List<org.openlegacy.definitions.RpcActionDefinition> partActions = new java.util.ArrayList<org.openlegacy.definitions.RpcActionDefinition>();
	private java.util.List<org.openlegacy.definitions.RpcActionDefinition> actions = new java.util.ArrayList<org.openlegacy.definitions.RpcActionDefinition>();
	@org.openlegacy.annotations.rpc.RpcField(length = 10)
	private short shortVar;
	@org.openlegacy.annotations.rpc.RpcField(length = 10)
	private int integerVar;
	@org.openlegacy.annotations.rpc.RpcField(length = 10)
	private String stringVar = "string";
	@org.openlegacy.annotations.rpc.RpcField(length = 10)
	private Object objectVar = new Object();
	@org.openlegacy.annotations.rpc.RpcField(length = 10)
	private java.util.List<RpcExternalPartOverrides.Part> part;


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

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public short getShortVar() {
		return this.shortVar;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public int getIntegerVar() {
		return this.integerVar;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public String getStringVar() {
		return this.stringVar;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public Object getObjectVar() {
		return this.objectVar;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public java.util.List<RpcExternalPartOverrides.Part> getPart() {
		return this.part;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public java.util.List<org.openlegacy.definitions.RpcActionDefinition> getActions() {
		return this.actions;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public java.util.List<org.openlegacy.definitions.RpcActionDefinition> getPartActions() {
		return this.partActions;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setShortVar(final short shortVar) {
		this.shortVar = shortVar;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setIntegerVar(final int integerVar) {
		this.integerVar = integerVar;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setStringVar(final String stringVar) {
		this.stringVar = stringVar;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setObjectVar(final Object objectVar) {
		this.objectVar = objectVar;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setPart(final java.util.List<RpcExternalPartOverrides.Part> part) {
		this.part = part;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setActions(final java.util.List<org.openlegacy.definitions.RpcActionDefinition> actions) {
		this.actions = actions;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setPartActions(final java.util.List<org.openlegacy.definitions.RpcActionDefinition> partActions) {
		this.partActions = partActions;
	}
}