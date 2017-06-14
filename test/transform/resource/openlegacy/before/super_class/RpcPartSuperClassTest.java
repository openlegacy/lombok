package lombok.test;


@org.openlegacy.core.annotations.rpc.RpcPartSuperClass 
class RpcPartSuperClassTest {
	
	private String superClassField;
	private Part part;
	
	@org.openlegacy.core.annotations.rpc.RpcPart
	public static class Part {
		
		private String innerField;
	}
}