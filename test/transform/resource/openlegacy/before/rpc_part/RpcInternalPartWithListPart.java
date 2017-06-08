package lombok.test;

@org.openlegacy.core.annotations.rpc.RpcEntity public class RpcInternalPartWithListPart {
	
	@org.openlegacy.core.annotations.rpc.RpcPart
	private static class Part {
		
		@org.openlegacy.core.annotations.rpc.RpcField(length=10)
		private String field1;
		@org.openlegacy.core.annotations.rpc.RpcField(length=10)
		private Integer field2;
	}
	
	@org.openlegacy.core.annotations.rpc.RpcField(length=10)
	private java.util.List<RpcInternalPartWithListPart.Part> part;
}