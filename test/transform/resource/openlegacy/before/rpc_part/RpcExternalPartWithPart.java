package lombok.test;

@org.openlegacy.core.annotations.rpc.RpcPart public class RpcExternalPartWithPart {
	
	@org.openlegacy.core.annotations.rpc.RpcPart
	private static class Part {
		
		@org.openlegacy.core.annotations.rpc.RpcField(length=10)
		private String field1;
		@org.openlegacy.core.annotations.rpc.RpcField(length=10)
		private Integer field2;
	}
	
	@org.openlegacy.core.annotations.rpc.RpcField(length=10)
    private Integer integerVar;
	private RpcExternalPartWithPart.Part part;
}