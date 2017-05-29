package lombok.test;

@lombok.OLData(org.openlegacy.rpc.RpcEntity)
public class OLDataRpcEntityOnClass {
	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	private java.util.List<org.openlegacy.rpc.definitions.RpcActionDefinition> actions = new java.util.ArrayList();
	
	private String stringField;
	
	
	public String getStringField(){
		return this.stringField;
	}
	
	public void setStringField(String stringField){
		this.stringField = stringField;
	}
	
}
