package lombok.test;

@org.openlegacy.core.annotations.screen.ScreenEntity
public class ScreenTableOverrides implements org.openlegacy.core.terminal.ScreenEntity {

  @org.openlegacy.core.annotations.screen.ScreenTable(supportTerminalData = true, startRow = 1, endRow = 2)
  private static class ScreenTable {
    private org.openlegacy.core.terminal.TerminalField nameField;
    private java.lang.String focusField;
    @org.openlegacy.core.annotations.screen.ScreenField(row = 1, column = 1, endRow = 1, endColumn = 5)
    private String name;

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public String getName() {
      return this.name;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public ScreenTable() {
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public org.openlegacy.core.terminal.TerminalField getNameField() {
      return this.nameField;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public java.lang.String getFocusField() {
      return this.focusField;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public void setNameField(final org.openlegacy.core.terminal.TerminalField nameField) {
      this.nameField = nameField;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public void setFocusField(final java.lang.String focusField) {
      this.focusField = focusField;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public void setName(final String name) {
      this.name = name;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public boolean equals(final java.lang.Object o) {
      if ((o == this)) return true;
      if ((!(o instanceof ScreenTableOverrides.ScreenTable))) return false;
      final ScreenTableOverrides.ScreenTable other = (ScreenTableOverrides.ScreenTable) o;
      if ((!other.canEqual((java.lang.Object) this))) return false;
      final java.lang.Object this$nameField = this.getNameField();
      final java.lang.Object other$nameField = other.getNameField();
      if (((this$nameField == null) ? (other$nameField != null) : (!this$nameField.equals(other$nameField)))) return false;
      final java.lang.Object this$focusField = this.getFocusField();
      final java.lang.Object other$focusField = other.getFocusField();
      if (((this$focusField == null) ? (other$focusField != null) : (!this$focusField.equals(other$focusField)))) return false;
      final java.lang.Object this$name = this.getName();
      final java.lang.Object other$name = other.getName();
      if (((this$name == null) ? (other$name != null) : (!this$name.equals(other$name)))) return false;
      return true;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    protected boolean canEqual(final java.lang.Object other) {
      return (other instanceof ScreenTableOverrides.ScreenTable);
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public int hashCode() {
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

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public java.lang.String toString() {
      return (((((("ScreenTableOverrides.ScreenTable(nameField=" + this.getNameField()) + ", focusField=") + this.getFocusField()) + ", name=") + this.getName()) + ")");
    }
  }

  private java.util.List<org.openlegacy.core.terminal.definitions.TerminalActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.terminal.definitions.TerminalActionDefinition>();
  private java.lang.String pcCommand;
  private java.lang.String focusField;
  private ScreenTableOverrides.ScreenTable table;

  public ScreenTableOverrides() {
  }

  @java.lang.SuppressWarnings("all")
  @javax.annotation.Generated("lombok")
  public ScreenTableOverrides.ScreenTable getTable() {
    return this.table;
  }

  @java.lang.SuppressWarnings("all")
  @javax.annotation.Generated("lombok")
  public java.lang.String getFocusField() {
    return this.focusField;
  }

  @java.lang.SuppressWarnings("all")
  @javax.annotation.Generated("lombok")
  public void setPcCommand(final java.lang.String pcCommand) {
    this.pcCommand = pcCommand;
  }

  @java.lang.SuppressWarnings("all")
  @javax.annotation.Generated("lombok")
  public void setActions(final java.util.List<org.openlegacy.core.terminal.definitions.TerminalActionDefinition> actions) {
    this.actions = actions;
  }

  @java.lang.SuppressWarnings("all")
  @javax.annotation.Generated("lombok")
  public java.util.List<org.openlegacy.core.terminal.definitions.TerminalActionDefinition> getActions() {
    return this.actions;
  }

  @java.lang.SuppressWarnings("all")
  @javax.annotation.Generated("lombok")
  public java.lang.String getPcCommand() {
    return this.pcCommand;
  }

  @java.lang.SuppressWarnings("all")
  @javax.annotation.Generated("lombok")
  public void setFocusField(final java.lang.String focusField) {
    this.focusField = focusField;
  }

  @java.lang.SuppressWarnings("all")
  @javax.annotation.Generated("lombok")
  public void setTable(final ScreenTableOverrides.ScreenTable table) {
    this.table = table;
  }
}
