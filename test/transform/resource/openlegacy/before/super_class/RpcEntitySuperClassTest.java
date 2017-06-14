package lombok.test;

@org.openlegacy.core.annotations.rpc.RpcEntitySuperClass 
class RpcEntitySuperClassTest {
	
	private String superClassField;
	private Part part;
	
	@org.openlegacy.core.annotations.rpc.RpcPart
	public static class Part {
		
		private String innerField;
	}
}