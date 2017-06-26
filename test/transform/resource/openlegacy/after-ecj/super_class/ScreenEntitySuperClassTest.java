package lombok.test;
@org.openlegacy.core.annotations.screen.ScreenEntitySuperClass(supportTerminalData = true) @javax.xml.bind.annotation.XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD) class ScreenEntitySuperClassTest {
  private @com.fasterxml.jackson.annotation.JsonIgnore() @javax.xml.bind.annotation.XmlTransient() java.lang.String superClassFieldDescription;
  private @com.fasterxml.jackson.annotation.JsonIgnore() @javax.xml.bind.annotation.XmlTransient() org.openlegacy.core.terminal.TerminalField superClassFieldField;
  private @org.openlegacy.core.annotations.screen.ScreenField(row = 1,column = 1) @org.openlegacy.core.annotations.screen.ScreenDescriptionField(row = 2,column = 2) String superClassField;
  ScreenEntitySuperClassTest() {
    super();
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") String getSuperClassField() {
    return this.superClassField;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") org.openlegacy.core.terminal.TerminalField getSuperClassFieldField() {
    return this.superClassFieldField;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.lang.String getSuperClassFieldDescription() {
    return this.superClassFieldDescription;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setSuperClassField(final String superClassField) {
    this.superClassField = superClassField;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setSuperClassFieldField(final org.openlegacy.core.terminal.TerminalField superClassFieldField) {
    this.superClassFieldField = superClassFieldField;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setSuperClassFieldDescription(final java.lang.String superClassFieldDescription) {
    this.superClassFieldDescription = superClassFieldDescription;
  }
}
