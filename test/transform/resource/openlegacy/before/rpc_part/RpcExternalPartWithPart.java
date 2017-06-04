package lombok.test;

@org.openlegacy.annotations.rpc.RpcPart public class RpcExternalPartWithPart {
	
	@org.openlegacy.annotations.rpc.RpcPart
	private static class Part {
		
		@org.openlegacy.annotations.rpc.RpcField(length=10)
		private String field1;
		@org.openlegacy.annotations.rpc.RpcField(length=10)
		private Integer field2;
	}
	
	@org.openlegacy.annotations.rpc.RpcField(length=10)
    private Integer integerVar;
	private RpcExternalPartWithPart.Part part;
}