package lombok.test;
public @org.openlegacy.core.annotations.screen.ScreenEntity @javax.xml.bind.annotation.XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD) class ScreenEntity implements org.openlegacy.core.terminal.ScreenEntity {
  private @com.fasterxml.jackson.annotation.JsonIgnore() @javax.xml.bind.annotation.XmlTransient() java.util.List<org.openlegacy.core.terminal.definitions.TerminalActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.terminal.definitions.TerminalActionDefinition>();
  private @com.fasterxml.jackson.annotation.JsonIgnore() @javax.xml.bind.annotation.XmlTransient() java.lang.String pcCommand;
  private @com.fasterxml.jackson.annotation.JsonIgnore() @javax.xml.bind.annotation.XmlTransient() java.lang.String focusField;
  public ScreenEntity() {
    super();
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
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setFocusField(final java.lang.String focusField) {
    this.focusField = focusField;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setPcCommand(final java.lang.String pcCommand) {
    this.pcCommand = pcCommand;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setActions(final java.util.List<org.openlegacy.core.terminal.definitions.TerminalActionDefinition> actions) {
    this.actions = actions;
  }
}
