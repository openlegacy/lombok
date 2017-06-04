package lombok.test;
public @org.openlegacy.annotations.screen.ScreenEntity class ScreenTableOverrides implements org.openlegacy.terminal.ScreenEntity {
  private static @org.openlegacy.annotations.screen.ScreenTable(supportTerminalData = true,startRow = 1,endRow = 2) class ScreenTable {
    private org.openlegacy.terminal.TerminalField nameField;
    private java.lang.String focusField;
    private @org.openlegacy.annotations.screen.ScreenField(row = 1,column = 1,endRow = 1,endColumn = 5) String name;
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") String getName() {
      return this.name;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") ScreenTable() {
      super();
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") org.openlegacy.terminal.TerminalField getNameField() {
      return this.nameField;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.lang.String getFocusField() {
      return this.focusField;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setNameField(final org.openlegacy.terminal.TerminalField nameField) {
      this.nameField = nameField;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setFocusField(final java.lang.String focusField) {
      this.focusField = focusField;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setName(final String name) {
      this.name = name;
    }
    public @java.lang.Override @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") boolean equals(final java.lang.Object o) {
      if ((o == this))
          return true;
      if ((! (o instanceof ScreenTableOverrides.ScreenTable)))
          return false;
      final ScreenTableOverrides.ScreenTable other = (ScreenTableOverrides.ScreenTable) o;
      if ((! other.canEqual((java.lang.Object) this)))
          return false;
      final java.lang.Object this$nameField = this.getNameField();
      final java.lang.Object other$nameField = other.getNameField();
      if (((this$nameField == null) ? (other$nameField != null) : (! this$nameField.equals(other$nameField))))
          return false;
      final java.lang.Object this$focusField = this.getFocusField();
      final java.lang.Object other$focusField = other.getFocusField();
      if (((this$focusField == null) ? (other$focusField != null) : (! this$focusField.equals(other$focusField))))
          return false;
      final java.lang.Object this$name = this.getName();
      final java.lang.Object other$name = other.getName();
      if (((this$name == null) ? (other$name != null) : (! this$name.equals(other$name))))
          return false;
      return true;
    }
    protected @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") boolean canEqual(final java.lang.Object other) {
      return (other instanceof ScreenTableOverrides.ScreenTable);
    }
    public @java.lang.Override @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") int hashCode() {
      final int PRIME = 59;
      int result = 1;
      final java.lang.Object $nameField = this.getNameField();
      result = ((result * PRIME) + (($nameField == null) ? 43 : $nameField.hashCode()));
      final java.lang.Object $focusField = this.getFocusField();
      result = ((result * PRIME) + (($focusField == null) ? 43 : $focusField.hashCode()));
      final java.lang.Object $name = this.getName();
      result = ((result * PRIME) + (($name == null) ? 43 : $name.hashCode()));
      return result;
    }
    public @java.lang.Override @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.lang.String toString() {
      return (((((("ScreenTableOverrides.ScreenTable(nameField=" + this.getNameField()) + ", focusField=") + this.getFocusField()) + ", name=") + this.getName()) + ")");
    }
  }
  private java.util.List<org.openlegacy.terminal.definitions.TerminalActionDefinition> actions = new java.util.ArrayList<org.openlegacy.terminal.definitions.TerminalActionDefinition>();
  private java.lang.String pcCommand;
  private java.lang.String focusField;
  private ScreenTableOverrides.ScreenTable table;
  public ScreenTableOverrides() {
    super();
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") ScreenTableOverrides.ScreenTable getTable() {
    return this.table;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.lang.String getFocusField() {
    return this.focusField;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setPcCommand(final java.lang.String pcCommand) {
    this.pcCommand = pcCommand;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setActions(final java.util.List<org.openlegacy.terminal.definitions.TerminalActionDefinition> actions) {
    this.actions = actions;
  }
}