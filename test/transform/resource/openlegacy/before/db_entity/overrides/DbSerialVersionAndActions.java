package lombok.test;

@org.openlegacy.annotations.db.DbEntity class DbSerialVersionAndActions implements org.openlegacy.db.DbEntity, java.io.Serializable{
private static final long serialVersionUID = 1L;
private java.util.List<org.openlegacy.db.definitions.DbActionDefinition> actions = new java.util.ArrayList<org.openlegacy.db.definitions.DbActionDefinition>();
}