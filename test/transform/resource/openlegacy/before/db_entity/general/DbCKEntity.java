package lombok.test;

@org.openlegacy.annotations.db.DbEntity class CKEntity{
	
	@javax.persistence.Id private String name;
	@javax.persistence.Id private String surname;
	private Integer salary;
}
