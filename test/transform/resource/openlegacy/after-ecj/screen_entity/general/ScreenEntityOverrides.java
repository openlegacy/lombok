package lombok.test;
public @org.openlegacy.core.annotations.screen.ScreenEntity(supportTerminalData = true) class ScreenEntityOverrides implements org.openlegacy.core.terminal.ScreenEntity {
  private static class ScreenTable {
    private ScreenTable() {
      super();
    }
  }
  private org.openlegacy.core.terminal.TerminalField itemNameField;
  private java.util.List<org.openlegacy.core.terminal.definitions.TerminalActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.terminal.definitions.TerminalActionDefinition>();
  private java.util.List<org.openlegacy.core.terminal.definitions.TerminalActionDefinition> tableInstancesActions = new java.util.ArrayList<org.openlegacy.core.terminal.definitions.TerminalActionDefinition>();
  private java.lang.String pcCommand;
  private java.lang.String focusField;
  private java.lang.String itemNameDescription;
  private org.openlegacy.core.terminal.TerminalSnapshot terminalSnapshot;
  private @org.openlegacy.core.annotations.screen.ScreenField(row = 6,column = 33,endColumn = 52,labelColumn = 2,displayName = "Item Name",sampleValue = "A") @org.openlegacy.core.annotations.screen.ScreenDescriptionField(column = 1,row = 1) String itemName;
  private @org.openlegacy.core.annotations.screen.ScreenField(row = 6,column = 33,endColumn = 52,labelColumn = 2,displayName = "Item Number",sampleValue = "A") java.util.List<ScreenEntityOverrides.ScreenTable> tableInstances;
  public ScreenEntityOverrides() {
    super();
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") String getItemName() {
    return this.itemName;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.lang.String getFocusField() {
    return this.focusField;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.lang.String getPcCommand() {
    return this.pcCommand;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<org.openlegacy.core.terminal.definitions.TerminalActionDefinition> getActions() {
    return this.actions;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") org.openlegacy.core.terminal.TerminalField getItemNameField() {
    return this.itemNameField;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setItemName(final String itemName) {
    this.itemName = itemName;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setTerminalSnapshot(final org.openlegacy.core.terminal.TerminalSnapshot terminalSnapshot) {
    this.terminalSnapshot = terminalSnapshot;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setFocusField(final java.lang.String focusField) {
    this.focusField = focusField;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setPcCommand(final java.lang.String pcCommand) {
    this.pcCommand = pcCommand;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setActions(final java.util.List<org.openlegacy.core.terminal.definitions.TerminalActionDefinition> actions) {
    this.actions = actions;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setItemNameField(final org.openlegacy.core.terminal.TerminalField itemNameField) {
    this.itemNameField = itemNameField;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<org.openlegacy.core.terminal.definitions.TerminalActionDefinition> getTableInstancesActions() {
    return this.tableInstancesActions;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.lang.String getItemNameDescription() {
    return this.itemNameDescription;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") org.openlegacy.core.terminal.TerminalSnapshot getTerminalSnapshot() {
    return this.terminalSnapshot;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<ScreenEntityOverrides.ScreenTable> getTableInstances() {
    return this.tableInstances;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setTableInstancesActions(final java.util.List<org.openlegacy.core.terminal.definitions.TerminalActionDefinition> tableInstancesActions) {
    this.tableInstancesActions = tableInstancesActions;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setItemNameDescription(final java.lang.String itemNameDescription) {
    this.itemNameDescription = itemNameDescription;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setTableInstances(final java.util.List<ScreenEntityOverrides.ScreenTable> tableInstances) {
    this.tableInstances = tableInstances;
  }
}
