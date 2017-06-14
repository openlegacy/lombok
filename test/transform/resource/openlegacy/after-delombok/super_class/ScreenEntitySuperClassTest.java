package lombok.test;

@org.openlegacy.core.annotations.screen.ScreenEntitySuperClass(supportTerminalData = true)
class ScreenEntitySuperClassTest {
	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	private java.lang.String superClassFieldDescription;
	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	private org.openlegacy.core.terminal.TerminalField superClassFieldField;
	@org.openlegacy.core.annotations.screen.ScreenField(row = 1, column = 1)
	@org.openlegacy.core.annotations.screen.ScreenDescriptionField(row = 2, column = 2)
	private String superClassField;

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public String getSuperClassField() {
		return this.superClassField;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public org.openlegacy.core.terminal.TerminalField getSuperClassFieldField() {
		return this.superClassFieldField;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public java.lang.String getSuperClassFieldDescription() {
		return this.superClassFieldDescription;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setSuperClassField(final String superClassField) {
		this.superClassField = superClassField;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setSuperClassFieldField(final org.openlegacy.core.terminal.TerminalField superClassFieldField) {
		this.superClassFieldField = superClassFieldField;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setSuperClassFieldDescription(final java.lang.String superClassFieldDescription) {
		this.superClassFieldDescription = superClassFieldDescription;
	}
}
