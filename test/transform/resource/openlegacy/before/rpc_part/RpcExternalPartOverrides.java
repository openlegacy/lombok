package lombok.test;

@org.openlegacy.annotations.rpc.RpcPart public class RpcExternalPartOverrides {
	
	@org.openlegacy.annotations.rpc.RpcField(length=10)
	private short shortVar;
	@org.openlegacy.annotations.rpc.RpcField(length=10)
    private int integerVar;
	@org.openlegacy.annotations.rpc.RpcField(length=10)
    private String stringVar = "string";
	@org.openlegacy.annotations.rpc.RpcField(length=10)
    private Object objectVar = new Object();
	@org.openlegacy.annotations.rpc.RpcField(length=10)
	private java.util.List<RpcExternalPartOverrides.Part> part;
	
    @org.openlegacy.annotations.rpc.RpcPart
    private static class Part {
		
		@org.openlegacy.annotations.rpc.RpcField(length=10)
		private String field1;
		@org.openlegacy.annotations.rpc.RpcField(length=10)
		private Integer field2;
	}

}