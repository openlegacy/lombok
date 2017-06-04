package lombok.test;
public @org.openlegacy.annotations.screen.ScreenEntity class ScreenEntity implements org.openlegacy.terminal.ScreenEntity {
  private java.util.List<org.openlegacy.terminal.definitions.TerminalActionDefinition> actions = new java.util.ArrayList<org.openlegacy.terminal.definitions.TerminalActionDefinition>();
  private java.lang.String pcCommand;
  private java.lang.String focusField;
  public ScreenEntity() {
    super();
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
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setFocusField(final java.lang.String focusField) {
    this.focusField = focusField;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setPcCommand(final java.lang.String pcCommand) {
    this.pcCommand = pcCommand;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setActions(final java.util.List<org.openlegacy.terminal.definitions.TerminalActionDefinition> actions) {
    this.actions = actions;
  }
}
