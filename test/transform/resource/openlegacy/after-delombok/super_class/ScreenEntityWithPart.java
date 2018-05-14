package lombok.test;

@org.openlegacy.core.annotations.screen.ScreenEntity
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
public class ScreenEntityWithPart implements org.openlegacy.core.terminal.ScreenEntity {
	@com.fasterxml.jackson.annotation.JsonIgnore
	@javax.xml.bind.annotation.XmlTransient
	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	private java.lang.String pcCommand;
	@com.fasterxml.jackson.annotation.JsonIgnore
	@javax.xml.bind.annotation.XmlTransient
	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	private java.lang.String focusField;
	@com.fasterxml.jackson.annotation.JsonIgnore
	@javax.xml.bind.annotation.XmlTransient
	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	private java.util.List<org.openlegacy.core.terminal.definitions.TerminalActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.terminal.definitions.TerminalActionDefinition>();
	private ScreenPartTest screenPart;


	@org.openlegacy.core.annotations.screen.ScreenPart(supportTerminalData = true)
	@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
	public static class ScreenPartTest {
		@com.fasterxml.jackson.annotation.JsonIgnore
		@javax.xml.bind.annotation.XmlTransient
		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		private java.lang.String partFieldDescription;
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

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public ScreenPartTest getScreenPart() {
		return this.screenPart;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public java.util.List<org.openlegacy.core.terminal.definitions.TerminalActionDefinition> getActions() {
		return this.actions;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public java.lang.String getFocusField() {
		return this.focusField;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public java.lang.String getPcCommand() {
		return this.pcCommand;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setScreenPart(final ScreenPartTest screenPart) {
		this.screenPart = screenPart;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setActions(final java.util.List<org.openlegacy.core.terminal.definitions.TerminalActionDefinition> actions) {
		this.actions = actions;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setFocusField(final java.lang.String focusField) {
		this.focusField = focusField;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setPcCommand(final java.lang.String pcCommand) {
		this.pcCommand = pcCommand;
	}
}