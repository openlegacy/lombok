package lombok.test;

@org.openlegacy.annotations.db.DbEntity class DbCKEntity2{
	
	@javax.persistence.Id private String name;
	@javax.persistence.Id private String surname;
	@javax.persistence.Id private byte age; 
	private Integer salary;
}
