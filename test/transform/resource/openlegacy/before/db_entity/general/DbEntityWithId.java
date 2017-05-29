package lombok.test;

@org.openlegacy.annotations.db.DbEntity class DbEntityWithId {
	
	@javax.persistence.Id private String stringId;
	private String name;
	private String surname;
	private Integer salary;
}