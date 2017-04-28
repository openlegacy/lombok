@lombok.OLData(org.openlegacy.terminal.ScreenEntity.class) class OLDataOnClass implements org.openlegacy.terminal.ScreenEntity {
  private @lombok.Setter(value = lombok.AccessLevel.NONE) java.util.List<org.openlegacy.terminal.definitions.TerminalActionDefinition> actions = new java.util.ArrayList<org.openlegacy.terminal.definitions.TerminalActionDefinition>();
  private String pcCommand;
  private String focusField;
  private String stringField = "";
  OLDataOnClass() {
    super();
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") String getStringField() {
    return this.stringField;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") String getFocusField() {
    return this.focusField;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") String getPcCommand() {
    return this.pcCommand;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<org.openlegacy.terminal.definitions.TerminalActionDefinition> getActions() {
    return this.actions;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setStringField(final String stringField) {
    this.stringField = stringField;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setFocusField(final String focusField) {
    this.focusField = focusField;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setPcCommand(final String pcCommand) {
    this.pcCommand = pcCommand;
  }
}