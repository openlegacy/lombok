import org.openlegacy.rpc.RpcEntity;

@lombok.OLData(RpcEntity.class) class OLDataRpcEntityOnClass {
	
	private String stringField = "";
	
}

@lombok.OLData(org.openlegacy.db.DbEntity.class) class OLDataDbEntityOnClass {
	
	@javax.persistence.Id
	private int field1;
	
	@javax.persistence.Id
	private boolean field2;
}
