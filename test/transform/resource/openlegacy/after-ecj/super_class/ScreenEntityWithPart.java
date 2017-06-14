package lombok.test;
public @org.openlegacy.core.annotations.screen.ScreenEntity class ScreenEntityWithPart implements org.openlegacy.core.terminal.ScreenEntity {
  public static @org.openlegacy.core.annotations.screen.ScreenPart(supportTerminalData = true) class ScreenPartTest {
    private java.lang.String partFieldDescription;
    private org.openlegacy.core.terminal.TerminalField partFieldField;
    private @org.openlegacy.core.annotations.screen.ScreenField(row = 1,column = 1) @org.openlegacy.core.annotations.screen.ScreenDescriptionField(row = 2,column = 2) String partField;
    public ScreenPartTest() {
      super();
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") String getPartField() {
      return this.partField;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") org.openlegacy.core.terminal.TerminalField getPartFieldField() {
      return this.partFieldField;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.lang.String getPartFieldDescription() {
      return this.partFieldDescription;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setPartField(final String partField) {
      this.partField = partField;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setPartFieldField(final org.openlegacy.core.terminal.TerminalField partFieldField) {
      this.partFieldField = partFieldField;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setPartFieldDescription(final java.lang.String partFieldDescription) {
      this.partFieldDescription = partFieldDescription;
    }
  }
  private java.util.List<org.openlegacy.core.terminal.definitions.TerminalActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.terminal.definitions.TerminalActionDefinition>();
  private java.lang.String pcCommand;
  private java.lang.String focusField;
  private ScreenPartTest screenPart;
  public ScreenEntityWithPart() {
    super();
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") ScreenPartTest getScreenPart() {
    return this.screenPart;
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
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setScreenPart(final ScreenPartTest screenPart) {
    this.screenPart = screenPart;
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