package lombok.test;

@org.openlegacy.core.annotations.db.DbEntity class DbSerialVersionAndActions implements org.openlegacy.core.db.DbEntity, java.io.Serializable{
private static final long serialVersionUID = 1L;
private java.util.List<org.openlegacy.core.db.definitions.DbActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.db.definitions.DbActionDefinition>();
}