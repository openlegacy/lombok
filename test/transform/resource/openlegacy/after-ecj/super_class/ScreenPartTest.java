package lombok.test;
@org.openlegacy.core.annotations.screen.ScreenPart(supportTerminalData = true) class ScreenPartTest {
  private java.lang.String partFieldDescription;
  private org.openlegacy.core.terminal.TerminalField partFieldField;
  private @org.openlegacy.core.annotations.screen.ScreenField(row = 1,column = 1) @org.openlegacy.core.annotations.screen.ScreenDescriptionField(row = 2,column = 2) String partField;
  ScreenPartTest() {
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