package lombok.test;

@org.openlegacy.core.annotations.screen.ScreenPart(supportTerminalData = true)
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
class ScreenPartTest {
	@com.fasterxml.jackson.annotation.JsonIgnore
	@javax.xml.bind.annotation.XmlTransient
	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	private java.lang.String partFieldDescription;
	@com.fasterxml.jackson.annotation.JsonIgnore
	@javax.xml.bind.annotation.XmlTransient
	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	private org.openlegacy.core.terminal.TerminalField partFieldField;
	@org.openlegacy.core.annotations.screen.ScreenField(row = 1, column = 1)
	@org.openlegacy.core.annotations.screen.ScreenDescriptionField(row = 2, column = 2)
	private String partField;

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public String getPartField() {
		return this.partField;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public org.openlegacy.core.terminal.TerminalField getPartFieldField() {
		return this.partFieldField;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public java.lang.String getPartFieldDescription() {
		return this.partFieldDescription;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setPartField(final String partField) {
		this.partField = partField;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setPartFieldField(final org.openlegacy.core.terminal.TerminalField partFieldField) {
		this.partFieldField = partFieldField;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setPartFieldDescription(final java.lang.String partFieldDescription) {
		this.partFieldDescription = partFieldDescription;
	}
}
