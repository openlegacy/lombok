package lombok.test;

@org.openlegacy.annotations.screen.ScreenEntity(supportTerminalData = true)
public class ScreenEntitySupportTD implements org.openlegacy.terminal.ScreenEntity {
	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	private org.openlegacy.terminal.TerminalField outerQuantityField;
	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	private org.openlegacy.terminal.TerminalField outerUnitOfMeasureField;
	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	private org.openlegacy.terminal.TerminalField packingMultiplierField;
	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	private org.openlegacy.terminal.TerminalField itemNumberField;
	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	private org.openlegacy.terminal.TerminalField itemNameField;
	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	private java.lang.String pcCommand;
	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	private java.lang.String focusField;
	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	private java.util.List<org.openlegacy.terminal.definitions.TerminalActionDefinition> actions = new java.util.ArrayList<org.openlegacy.terminal.definitions.TerminalActionDefinition>();
	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	private org.openlegacy.terminal.TerminalSnapshot terminalSnapshot;
	@org.openlegacy.annotations.screen.ScreenField(row = 6, column = 33, endColumn = 52, labelColumn = 2, displayName = "Item Name", sampleValue = "A")
	private String itemName;
	@org.openlegacy.annotations.screen.ScreenField(row = 6, column = 33, endColumn = 52, labelColumn = 2, displayName = "Item Number", sampleValue = "A")
	private Integer itemNumber;
	@org.openlegacy.annotations.screen.ScreenField(key = true, row = 17, column = 33, endColumn = 37, labelColumn = 2, displayName = "Packing Multiplier", sampleValue = "0")
	private Double packingMultiplier;
	@org.openlegacy.annotations.screen.ScreenField(row = 18, column = 33, endColumn = 37, labelColumn = 2, displayName = "Outer Unit of Measure")
	private java.math.BigInteger outerUnitOfMeasure;
	@org.openlegacy.annotations.screen.ScreenField(row = 19, column = 33, endColumn = 41, labelColumn = 2, displayName = "Outer Quantity", sampleValue = "0")
	private java.math.BigDecimal outerQuantity;

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public String getItemName() {
		return this.itemName;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public Integer getItemNumber() {
		return this.itemNumber;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public Double getPackingMultiplier() {
		return this.packingMultiplier;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public java.math.BigInteger getOuterUnitOfMeasure() {
		return this.outerUnitOfMeasure;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public java.math.BigDecimal getOuterQuantity() {
		return this.outerQuantity;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public org.openlegacy.terminal.TerminalSnapshot getTerminalSnapshot() {
		return this.terminalSnapshot;
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
	public org.openlegacy.terminal.TerminalField getItemNameField() {
		return this.itemNameField;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public org.openlegacy.terminal.TerminalField getItemNumberField() {
		return this.itemNumberField;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public org.openlegacy.terminal.TerminalField getPackingMultiplierField() {
		return this.packingMultiplierField;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public org.openlegacy.terminal.TerminalField getOuterUnitOfMeasureField() {
		return this.outerUnitOfMeasureField;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public org.openlegacy.terminal.TerminalField getOuterQuantityField() {
		return this.outerQuantityField;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setItemNumber(final Integer itemNumber) {
		this.itemNumber = itemNumber;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setPackingMultiplier(final Double packingMultiplier) {
		this.packingMultiplier = packingMultiplier;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setOuterUnitOfMeasure(final java.math.BigInteger outerUnitOfMeasure) {
		this.outerUnitOfMeasure = outerUnitOfMeasure;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setOuterQuantity(final java.math.BigDecimal outerQuantity) {
		this.outerQuantity = outerQuantity;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setTerminalSnapshot(final org.openlegacy.terminal.TerminalSnapshot terminalSnapshot) {
		this.terminalSnapshot = terminalSnapshot;
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

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setItemNameField(final org.openlegacy.terminal.TerminalField itemNameField) {
		this.itemNameField = itemNameField;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setItemNumberField(final org.openlegacy.terminal.TerminalField itemNumberField) {
		this.itemNumberField = itemNumberField;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setPackingMultiplierField(final org.openlegacy.terminal.TerminalField packingMultiplierField) {
		this.packingMultiplierField = packingMultiplierField;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setOuterUnitOfMeasureField(final org.openlegacy.terminal.TerminalField outerUnitOfMeasureField) {
		this.outerUnitOfMeasureField = outerUnitOfMeasureField;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setOuterQuantityField(final org.openlegacy.terminal.TerminalField outerQuantityField) {
		this.outerQuantityField = outerQuantityField;
	}
}
