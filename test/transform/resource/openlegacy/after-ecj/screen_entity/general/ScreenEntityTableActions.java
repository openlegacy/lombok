package lombok.test;
public @org.openlegacy.annotations.screen.ScreenEntity(supportTerminalData = false) class ScreenEntityTableActions implements org.openlegacy.terminal.ScreenEntity {
  private static class ScreenTable {
    private ScreenTable() {
      super();
    }
  }
  private java.util.List<org.openlegacy.terminal.definitions.TerminalActionDefinition> tableInstancesActions = new java.util.ArrayList<org.openlegacy.terminal.definitions.TerminalActionDefinition>();
  private java.util.List<org.openlegacy.terminal.definitions.TerminalActionDefinition> actions = new java.util.ArrayList<org.openlegacy.terminal.definitions.TerminalActionDefinition>();
  private java.lang.String pcCommand;
  private java.lang.String focusField;
  private @org.openlegacy.annotations.screen.ScreenField(row = 6,column = 33,endColumn = 52,labelColumn = 2,displayName = "Item Number",sampleValue = "A") String itemName;
  private @org.openlegacy.annotations.screen.ScreenField(row = 6,column = 33,endColumn = 52,labelColumn = 2,displayName = "Item Number",sampleValue = "A") java.util.List<ScreenEntityTableActions.ScreenTable> tableInstances;
  public ScreenEntityTableActions() {
    super();
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") String getItemName() {
    return this.itemName;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<ScreenEntityTableActions.ScreenTable> getTableInstances() {
    return this.tableInstances;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.lang.String getFocusField() {
    return this.focusField;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.lang.String getPcCommand() {
    return this.pcCommand;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<org.openlegacy.terminal.definitions.TerminalActionDefinition> getActions() {
    return this.actions;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<org.openlegacy.terminal.definitions.TerminalActionDefinition> getTableInstancesActions() {
    return this.tableInstancesActions;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setItemName(final String itemName) {
    this.itemName = itemName;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setTableInstances(final java.util.List<ScreenEntityTableActions.ScreenTable> tableInstances) {
    this.tableInstances = tableInstances;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setFocusField(final java.lang.String focusField) {
    this.focusField = focusField;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setPcCommand(final java.lang.String pcCommand) {
    this.pcCommand = pcCommand;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setActions(final java.util.List<org.openlegacy.terminal.definitions.TerminalActionDefinition> actions) {
    this.actions = actions;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setTableInstancesActions(final java.util.List<org.openlegacy.terminal.definitions.TerminalActionDefinition> tableInstancesActions) {
    this.tableInstancesActions = tableInstancesActions;
  }
}