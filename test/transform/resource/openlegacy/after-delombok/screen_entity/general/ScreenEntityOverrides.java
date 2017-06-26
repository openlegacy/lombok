package lombok.test;

@org.openlegacy.core.annotations.screen.ScreenEntity(supportTerminalData = true)
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
public class ScreenEntityOverrides implements org.openlegacy.core.terminal.ScreenEntity {

	private static class ScreenTable {
	}

	private org.openlegacy.core.terminal.TerminalField itemNameField;
	private java.util.List<org.openlegacy.core.terminal.definitions.TerminalActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.terminal.definitions.TerminalActionDefinition>();
	private java.util.List<org.openlegacy.core.terminal.definitions.TerminalActionDefinition> tableInstancesActions = new java.util.ArrayList<org.openlegacy.core.terminal.definitions.TerminalActionDefinition>();
	private java.lang.String pcCommand;
	private java.lang.String focusField;
	private java.lang.String itemNameDescription;
	private org.openlegacy.core.terminal.TerminalSnapshot terminalSnapshot;
	@org.openlegacy.core.annotations.screen.ScreenField(row = 6, column = 33, endColumn = 52, labelColumn = 2, displayName = "Item Name", sampleValue = "A")
	@org.openlegacy.core.annotations.screen.ScreenDescriptionField(column = 1, row = 1)
	private String itemName;
	@org.openlegacy.core.annotations.screen.ScreenField(row = 6, column = 33, endColumn = 52, labelColumn = 2, displayName = "Item Number", sampleValue = "A")
	private java.util.List<ScreenEntityOverrides.ScreenTable> tableInstances;

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public String getItemName() {
		return this.itemName;
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
	public java.util.List<org.openlegacy.core.terminal.definitions.TerminalActionDefinition> getActions() {
		return this.actions;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public org.openlegacy.core.terminal.TerminalField getItemNameField() {
		return this.itemNameField;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setTerminalSnapshot(final org.openlegacy.core.terminal.TerminalSnapshot terminalSnapshot) {
		this.terminalSnapshot = terminalSnapshot;
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

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setActions(final java.util.List<org.openlegacy.core.terminal.definitions.TerminalActionDefinition> actions) {
		this.actions = actions;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setItemNameField(final org.openlegacy.core.terminal.TerminalField itemNameField) {
		this.itemNameField = itemNameField;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public java.util.List<org.openlegacy.core.terminal.definitions.TerminalActionDefinition> getTableInstancesActions() {
		return this.tableInstancesActions;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public java.lang.String getItemNameDescription() {
		return this.itemNameDescription;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public org.openlegacy.core.terminal.TerminalSnapshot getTerminalSnapshot() {
		return this.terminalSnapshot;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public java.util.List<ScreenEntityOverrides.ScreenTable> getTableInstances() {
		return this.tableInstances;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setTableInstancesActions(final java.util.List<org.openlegacy.core.terminal.definitions.TerminalActionDefinition> tableInstancesActions) {
		this.tableInstancesActions = tableInstancesActions;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setItemNameDescription(final java.lang.String itemNameDescription) {
		this.itemNameDescription = itemNameDescription;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setTableInstances(final java.util.List<ScreenEntityOverrides.ScreenTable> tableInstances) {
		this.tableInstances = tableInstances;
	}
}
