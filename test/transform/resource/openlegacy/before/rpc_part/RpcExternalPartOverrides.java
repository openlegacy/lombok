package lombok.test;

@org.openlegacy.core.annotations.rpc.RpcPart public class RpcExternalPartOverrides {
	
	@org.openlegacy.core.annotations.rpc.RpcField(length=10)
	private short shortVar;
	@org.openlegacy.core.annotations.rpc.RpcField(length=10)
    private int integerVar;
	@org.openlegacy.core.annotations.rpc.RpcField(length=10)
    private String stringVar = "string";
	@org.openlegacy.core.annotations.rpc.RpcField(length=10)
    private Object objectVar = new Object();
	@org.openlegacy.core.annotations.rpc.RpcField(length=10)
	private java.util.List<RpcExternalPartOverrides.Part> part;
	
    @org.openlegacy.core.annotations.rpc.RpcPart
    private static class Part {
		
		@org.openlegacy.core.annotations.rpc.RpcField(length=10)
		private String field1;
		@org.openlegacy.core.annotations.rpc.RpcField(length=10)
		private Integer field2;
	}

}