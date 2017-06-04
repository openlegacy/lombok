package lombok.test;

@org.openlegacy.annotations.screen.ScreenEntity(supportTerminalData = true) public class ScreenEntityOverrides implements org.openlegacy.terminal.ScreenEntity {
	
	private static class ScreenTable {
	}
	
	private org.openlegacy.terminal.TerminalField itemNameField;
	private java.util.List<org.openlegacy.terminal.definitions.TerminalActionDefinition> actions = new java.util.ArrayList<org.openlegacy.terminal.definitions.TerminalActionDefinition>();
	private java.util.List<org.openlegacy.terminal.definitions.TerminalActionDefinition> tableInstancesActions = new java.util.ArrayList<org.openlegacy.terminal.definitions.TerminalActionDefinition>();
	private java.lang.String pcCommand;
	private java.lang.String focusField;
	private java.lang.String itemNameDescription;
	private org.openlegacy.terminal.TerminalSnapshot terminalSnapshot;
	private @org.openlegacy.annotations.screen.ScreenField(row = 6, column = 33, endColumn = 52, labelColumn = 2, displayName = "Item Name", sampleValue = "A") @org.openlegacy.annotations.screen.ScreenDescriptionField(column = 1, row = 1) String itemName;
	private @org.openlegacy.annotations.screen.ScreenField(row = 6,column = 33,endColumn = 52,labelColumn = 2,displayName = "Item Number",sampleValue = "A") java.util.List<ScreenEntityOverrides.ScreenTable> tableInstances;
	
	public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") String getItemName() {
		return this.itemName;
	}
	
	public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.lang.String getFocusField() {
		return this.focusField;
	}
	
	public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.lang.String getPcCommand() {
		return this.pcCommand;
	}
	
	public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<org.openlegacy.terminal.definitions.TerminalActionDefinition> getActions() {
		return this.actions;
	}
	
	public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") org.openlegacy.terminal.TerminalField getItemNameField() {
		return this.itemNameField;
	}
	
	public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setItemName(final String itemName) {
		this.itemName = itemName;
	}
	
	public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setTerminalSnapshot(final org.openlegacy.terminal.TerminalSnapshot terminalSnapshot) {
		this.terminalSnapshot = terminalSnapshot;
	}
	
	public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setFocusField(final java.lang.String focusField) {
		this.focusField = focusField;
	}
	
	public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setPcCommand(final java.lang.String pcCommand) {
		this.pcCommand = pcCommand;
	}
	
	public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setActions(final java.util.List<org.openlegacy.terminal.definitions.TerminalActionDefinition> actions) {
		this.actions = actions;
	}
	
	public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setItemNameField(final org.openlegacy.terminal.TerminalField itemNameField) {
		this.itemNameField = itemNameField;
	}
	
}