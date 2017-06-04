package lombok.test;

@org.openlegacy.annotations.screen.ScreenEntity
public class ScreenEntityInnerClass implements org.openlegacy.terminal.ScreenEntity {
	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	private java.lang.String pcCommand;
	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	private java.lang.String focusField;
	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	private java.util.List<org.openlegacy.terminal.definitions.TerminalActionDefinition> actions = new java.util.ArrayList<org.openlegacy.terminal.definitions.TerminalActionDefinition>();


	public static class ScreenTable {
		private String property;
	}

	@org.openlegacy.annotations.screen.ScreenField(row = 18, endRow = 37, column = 1, endColumn = 80)
	private ScreenEntityInnerClass.ScreenTable screenTable;

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public ScreenEntityInnerClass.ScreenTable getScreenTable() {
		return this.screenTable;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public java.util.List<org.openlegacy.terminal.definitions.TerminalActionDefinition> getActions() {
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
	public void setScreenTable(final ScreenEntityInnerClass.ScreenTable screenTable) {
		this.screenTable = screenTable;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setActions(final java.util.List<org.openlegacy.terminal.definitions.TerminalActionDefinition> actions) {
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
