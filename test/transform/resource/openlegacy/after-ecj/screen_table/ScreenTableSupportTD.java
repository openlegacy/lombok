package lombok.test;
public @org.openlegacy.core.annotations.screen.ScreenEntity @javax.xml.bind.annotation.XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD) class ScreenTableSupportTD implements org.openlegacy.core.terminal.ScreenEntity {
  private static @org.openlegacy.core.annotations.screen.ScreenTable(supportTerminalData = true,startRow = 1,endRow = 2) @javax.xml.bind.annotation.XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD) class ScreenTable {
    private org.openlegacy.core.terminal.TerminalField nationalIdField;
    private org.openlegacy.core.terminal.TerminalField ageField;
    private org.openlegacy.core.terminal.TerminalField surnameField;
    private org.openlegacy.core.terminal.TerminalField nameField;
    private @com.fasterxml.jackson.annotation.JsonIgnore() @javax.xml.bind.annotation.XmlTransient() java.lang.String focusField;
    private @org.openlegacy.core.annotations.screen.ScreenField(row = 1,column = 1,endRow = 1,endColumn = 5) String name;
    private @org.openlegacy.core.annotations.screen.ScreenField(row = 1,column = 1,endRow = 1,endColumn = 5) String surname;
    private @org.openlegacy.core.annotations.screen.ScreenField(row = 1,column = 1,endRow = 1,endColumn = 5) Integer age;
    private @org.openlegacy.core.annotations.screen.ScreenField(row = 1,column = 1,endRow = 1,endColumn = 5) java.math.BigInteger nationalId;
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") String getName() {
      return this.name;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") String getSurname() {
      return this.surname;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") Integer getAge() {
      return this.age;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.math.BigInteger getNationalId() {
      return this.nationalId;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.lang.String getFocusField() {
      return this.focusField;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") org.openlegacy.core.terminal.TerminalField getNameField() {
      return this.nameField;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") org.openlegacy.core.terminal.TerminalField getSurnameField() {
      return this.surnameField;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") org.openlegacy.core.terminal.TerminalField getAgeField() {
      return this.ageField;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") org.openlegacy.core.terminal.TerminalField getNationalIdField() {
      return this.nationalIdField;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setName(final String name) {
      this.name = name;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setSurname(final String surname) {
      this.surname = surname;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setAge(final Integer age) {
      this.age = age;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setNationalId(final java.math.BigInteger nationalId) {
      this.nationalId = nationalId;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setFocusField(final java.lang.String focusField) {
      this.focusField = focusField;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setNameField(final org.openlegacy.core.terminal.TerminalField nameField) {
      this.nameField = nameField;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setSurnameField(final org.openlegacy.core.terminal.TerminalField surnameField) {
      this.surnameField = surnameField;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setAgeField(final org.openlegacy.core.terminal.TerminalField ageField) {
      this.ageField = ageField;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setNationalIdField(final org.openlegacy.core.terminal.TerminalField nationalIdField) {
      this.nationalIdField = nationalIdField;
    }
    public @java.lang.Override @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") boolean equals(final java.lang.Object o) {
      if ((o == this))
          return true;
      if ((! (o instanceof ScreenTableSupportTD.ScreenTable)))
          return false;
      final ScreenTableSupportTD.ScreenTable other = (ScreenTableSupportTD.ScreenTable) o;
      if ((! other.canEqual((java.lang.Object) this)))
          return false;
      final java.lang.Object this$name = this.getName();
      final java.lang.Object other$name = other.getName();
      if (((this$name == null) ? (other$name != null) : (! this$name.equals(other$name))))
          return false;
      final java.lang.Object this$surname = this.getSurname();
      final java.lang.Object other$surname = other.getSurname();
      if (((this$surname == null) ? (other$surname != null) : (! this$surname.equals(other$surname))))
          return false;
      final java.lang.Object this$age = this.getAge();
      final java.lang.Object other$age = other.getAge();
      if (((this$age == null) ? (other$age != null) : (! this$age.equals(other$age))))
          return false;
      final java.lang.Object this$nationalId = this.getNationalId();
      final java.lang.Object other$nationalId = other.getNationalId();
      if (((this$nationalId == null) ? (other$nationalId != null) : (! this$nationalId.equals(other$nationalId))))
          return false;
      final java.lang.Object this$focusField = this.getFocusField();
      final java.lang.Object other$focusField = other.getFocusField();
      if (((this$focusField == null) ? (other$focusField != null) : (! this$focusField.equals(other$focusField))))
          return false;
      final java.lang.Object this$nameField = this.getNameField();
      final java.lang.Object other$nameField = other.getNameField();
      if (((this$nameField == null) ? (other$nameField != null) : (! this$nameField.equals(other$nameField))))
          return false;
      final java.lang.Object this$surnameField = this.getSurnameField();
      final java.lang.Object other$surnameField = other.getSurnameField();
      if (((this$surnameField == null) ? (other$surnameField != null) : (! this$surnameField.equals(other$surnameField))))
          return false;
      final java.lang.Object this$ageField = this.getAgeField();
      final java.lang.Object other$ageField = other.getAgeField();
      if (((this$ageField == null) ? (other$ageField != null) : (! this$ageField.equals(other$ageField))))
          return false;
      final java.lang.Object this$nationalIdField = this.getNationalIdField();
      final java.lang.Object other$nationalIdField = other.getNationalIdField();
      if (((this$nationalIdField == null) ? (other$nationalIdField != null) : (! this$nationalIdField.equals(other$nationalIdField))))
          return false;
      return true;
    }
    protected @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") boolean canEqual(final java.lang.Object other) {
      return (other instanceof ScreenTableSupportTD.ScreenTable);
    }
    public @java.lang.Override @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") int hashCode() {
      final int PRIME = 59;
      int result = 1;
      final java.lang.Object $name = this.getName();
      result = ((result * PRIME) + (($name == null) ? 43 : $name.hashCode()));
      final java.lang.Object $surname = this.getSurname();
      result = ((result * PRIME) + (($surname == null) ? 43 : $surname.hashCode()));
      final java.lang.Object $age = this.getAge();
      result = ((result * PRIME) + (($age == null) ? 43 : $age.hashCode()));
      final java.lang.Object $nationalId = this.getNationalId();
      result = ((result * PRIME) + (($nationalId == null) ? 43 : $nationalId.hashCode()));
      final java.lang.Object $focusField = this.getFocusField();
      result = ((result * PRIME) + (($focusField == null) ? 43 : $focusField.hashCode()));
      final java.lang.Object $nameField = this.getNameField();
      result = ((result * PRIME) + (($nameField == null) ? 43 : $nameField.hashCode()));
      final java.lang.Object $surnameField = this.getSurnameField();
      result = ((result * PRIME) + (($surnameField == null) ? 43 : $surnameField.hashCode()));
      final java.lang.Object $ageField = this.getAgeField();
      result = ((result * PRIME) + (($ageField == null) ? 43 : $ageField.hashCode()));
      final java.lang.Object $nationalIdField = this.getNationalIdField();
      result = ((result * PRIME) + (($nationalIdField == null) ? 43 : $nationalIdField.hashCode()));
      return result;
    }
    public @java.lang.Override @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.lang.String toString() {
      return (((((((((((((((((("ScreenTableSupportTD.ScreenTable(name=" + this.getName()) + ", surname=") + this.getSurname()) + ", age=") + this.getAge()) + ", nationalId=") + this.getNationalId()) + ", focusField=") + this.getFocusField()) + ", nameField=") + this.getNameField()) + ", surnameField=") + this.getSurnameField()) + ", ageField=") + this.getAgeField()) + ", nationalIdField=") + this.getNationalIdField()) + ")");
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") ScreenTable() {
      super();
    }
  }
  private @com.fasterxml.jackson.annotation.JsonIgnore() @javax.xml.bind.annotation.XmlTransient() java.util.List<org.openlegacy.core.terminal.definitions.TerminalActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.terminal.definitions.TerminalActionDefinition>();
  private @com.fasterxml.jackson.annotation.JsonIgnore() @javax.xml.bind.annotation.XmlTransient() java.lang.String pcCommand;
  private @com.fasterxml.jackson.annotation.JsonIgnore() @javax.xml.bind.annotation.XmlTransient() java.lang.String focusField;
  private ScreenTableSupportTD.ScreenTable table;
  public ScreenTableSupportTD() {
    super();
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") ScreenTableSupportTD.ScreenTable getTable() {
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
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setTable(final ScreenTableSupportTD.ScreenTable table) {
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
