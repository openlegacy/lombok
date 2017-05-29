package lombok.test;

@org.openlegacy.annotations.db.DbEntity class DbEnitityTest implements org.openlegacy.db.DbEntity{

}

@org.openlegacy.annotations.db.DbEntity class DbEnitityTest1 implements java.io.Serializable{

}

@org.openlegacy.annotations.db.DbEntity class DbEnitityTest2 implements org.openlegacy.db.DbEntity, java.io.Serializable{

}

@org.openlegacy.annotations.db.DbEntity class DbEnitityTest3 implements org.openlegacy.db.DbEntity, java.io.Serializable{
private static final long serialVersionUID = 1L;
}

@org.openlegacy.annotations.db.DbEntity class DbEnitityTest4 implements org.openlegacy.db.DbEntity, java.io.Serializable{
private static final long serialVersionUID = 1L;
private java.util.List<org.openlegacy.db.definitions.DbActionDefinition> actions = new java.util.ArrayList<org.openlegacy.db.definitions.DbActionDefinition>();
}

@org.openlegacy.annotations.db.DbEntity class DbEnitityTest5 implements org.openlegacy.db.DbEntity, java.io.Serializable {
  private static final long serialVersionUID = 1L;
  private java.util.List<org.openlegacy.db.definitions.DbActionDefinition> actions = new java.util.ArrayList<org.openlegacy.db.definitions.DbActionDefinition>();
  DbEnitityTest() {
    super();
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<org.openlegacy.db.definitions.DbActionDefinition> getActions() {
    return this.actions;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setActions(final java.util.List<org.openlegacy.db.definitions.DbActionDefinition> actions) {
    this.actions = actions;
  }
}