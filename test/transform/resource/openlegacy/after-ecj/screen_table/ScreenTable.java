package lombok.test;
public @org.openlegacy.core.annotations.screen.ScreenEntity class ScreenTable implements org.openlegacy.core.terminal.ScreenEntity {
  private static @org.openlegacy.core.annotations.screen.ScreenTable(startRow = 1,endRow = 1) class ScreenTbl {
    private java.lang.String focusField;
    private @org.openlegacy.core.annotations.screen.ScreenField(row = 1,column = 1,endRow = 1,endColumn = 5) String tableField;
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") String getTableField() {
      return this.tableField;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.lang.String getFocusField() {
      return this.focusField;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setTableField(final String tableField) {
      this.tableField = tableField;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setFocusField(final java.lang.String focusField) {
      this.focusField = focusField;
    }
    public @java.lang.Override @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") boolean equals(final java.lang.Object o) {
      if ((o == this))
          return true;
      if ((! (o instanceof ScreenTable.ScreenTbl)))
          return false;
      final ScreenTable.ScreenTbl other = (ScreenTable.ScreenTbl) o;
      if ((! other.canEqual((java.lang.Object) this)))
          return false;
      final java.lang.Object this$tableField = this.getTableField();
      final java.lang.Object other$tableField = other.getTableField();
      if (((this$tableField == null) ? (other$tableField != null) : (! this$tableField.equals(other$tableField))))
          return false;
      final java.lang.Object this$focusField = this.getFocusField();
      final java.lang.Object other$focusField = other.getFocusField();
      if (((this$focusField == null) ? (other$focusField != null) : (! this$focusField.equals(other$focusField))))
          return false;
      return true;
    }
    protected @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") boolean canEqual(final java.lang.Object other) {
      return (other instanceof ScreenTable.ScreenTbl);
    }
    public @java.lang.Override @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") int hashCode() {
      final int PRIME = 59;
      int result = 1;
      final java.lang.Object $tableField = this.getTableField();
      result = ((result * PRIME) + (($tableField == null) ? 43 : $tableField.hashCode()));
      final java.lang.Object $focusField = this.getFocusField();
      result = ((result * PRIME) + (($focusField == null) ? 43 : $focusField.hashCode()));
      return result;
    }
    public @java.lang.Override @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.lang.String toString() {
      return (((("ScreenTable.ScreenTbl(tableField=" + this.getTableField()) + ", focusField=") + this.getFocusField()) + ")");
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") ScreenTbl() {
      super();
    }
  }
  private java.util.List<org.openlegacy.core.terminal.definitions.TerminalActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.terminal.definitions.TerminalActionDefinition>();
  private java.lang.String pcCommand;
  private java.lang.String focusField;
  private ScreenTable.ScreenTbl table;
  public ScreenTable() {
    super();
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") ScreenTable.ScreenTbl getTable() {
    return this.table;
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
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setTable(final ScreenTable.ScreenTbl table) {
    this.table = table;
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