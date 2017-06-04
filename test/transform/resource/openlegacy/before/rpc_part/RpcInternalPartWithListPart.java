package lombok.test;

@org.openlegacy.annotations.rpc.RpcEntity public class RpcInternalPartWithListPart {
	
	@org.openlegacy.annotations.rpc.RpcPart
	private static class Part {
		
		@org.openlegacy.annotations.rpc.RpcField(length=10)
		private String field1;
		@org.openlegacy.annotations.rpc.RpcField(length=10)
		private Integer field2;
	}
	
	@org.openlegacy.annotations.rpc.RpcField(length=10)
	private java.util.List<RpcInternalPartWithListPart.Part> part;
}