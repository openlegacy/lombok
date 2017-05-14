import org.openlegacy.rpc.definitions.RpcEntityDefinition;

@lombok.OLData(org.openlegacy.rpc.RpcEntity)
public class OLDataRpcEntityOnClass {
	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	private java.util.List<RpcActionDefinition> actions = new java.util.ArrayList();
	
	private String stringField;
	
	
	public String getStringField(){
		return this.stringField;
	}
	
	public void setStringField(String stringField){
		this.stringField = stringField;
	}
	
}
