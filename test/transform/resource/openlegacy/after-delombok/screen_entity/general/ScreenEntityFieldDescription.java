package lombok.test;

@org.openlegacy.core.annotations.screen.ScreenEntity(supportTerminalData = false)
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
public class ScreenEntityFieldDescription implements org.openlegacy.core.terminal.ScreenEntity {
	@com.fasterxml.jackson.annotation.JsonIgnore
	@javax.xml.bind.annotation.XmlTransient
	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	private java.lang.String outerQuantityDescription;
	@com.fasterxml.jackson.annotation.JsonIgnore
	@javax.xml.bind.annotation.XmlTransient
	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	private java.lang.String outerUnitOfMeasureDescription;
	@com.fasterxml.jackson.annotation.JsonIgnore
	@javax.xml.bind.annotation.XmlTransient
	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	private java.lang.String packingMultiplierDescription;
	@com.fasterxml.jackson.annotation.JsonIgnore
	@javax.xml.bind.annotation.XmlTransient
	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	private java.lang.String itemNumberDescription;
	@com.fasterxml.jackson.annotation.JsonIgnore
	@javax.xml.bind.annotation.XmlTransient
	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	private java.lang.String itemNameDescription;
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
	@org.openlegacy.core.annotations.screen.ScreenField(row = 6, column = 33, endColumn = 52, labelColumn = 2, displayName = "Item Number", sampleValue = "A")
	@org.openlegacy.core.annotations.screen.ScreenDescriptionField(column = 1, row = 1)
	private String itemName;
	@org.openlegacy.core.annotations.screen.ScreenField(row = 6, column = 33, endColumn = 52, labelColumn = 2, displayName = "Item Number", sampleValue = "A")
	@org.openlegacy.core.annotations.screen.ScreenDescriptionField(column = 1, row = 3)
	private Integer itemNumber;
	@org.openlegacy.core.annotations.screen.ScreenField(key = true, row = 17, column = 33, endColumn = 37, labelColumn = 2, displayName = "Packing Multiplier", sampleValue = "0")
	@org.openlegacy.core.annotations.screen.ScreenDescriptionField(column = 1, row = 5)
	private Double packingMultiplier;
	@org.openlegacy.core.annotations.screen.ScreenField(row = 18, column = 33, endColumn = 37, labelColumn = 2, displayName = "Outer Unit of Measure")
	@org.openlegacy.core.annotations.screen.ScreenDescriptionField(column = 1, row = 5)
	private java.math.BigInteger outerUnitOfMeasure;
	@org.openlegacy.core.annotations.screen.ScreenField(row = 19, column = 33, endColumn = 41, labelColumn = 2, displayName = "Outer Quantity", sampleValue = "0")
	@org.openlegacy.core.annotations.screen.ScreenDescriptionField(column = 1, row = 7)
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
	public java.lang.String getItemNameDescription() {
		return this.itemNameDescription;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public java.lang.String getItemNumberDescription() {
		return this.itemNumberDescription;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public java.lang.String getPackingMultiplierDescription() {
		return this.packingMultiplierDescription;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public java.lang.String getOuterUnitOfMeasureDescription() {
		return this.outerUnitOfMeasureDescription;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public java.lang.String getOuterQuantityDescription() {
		return this.outerQuantityDescription;
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

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setItemNameDescription(final java.lang.String itemNameDescription) {
		this.itemNameDescription = itemNameDescription;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setItemNumberDescription(final java.lang.String itemNumberDescription) {
		this.itemNumberDescription = itemNumberDescription;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setPackingMultiplierDescription(final java.lang.String packingMultiplierDescription) {
		this.packingMultiplierDescription = packingMultiplierDescription;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setOuterUnitOfMeasureDescription(final java.lang.String outerUnitOfMeasureDescription) {
		this.outerUnitOfMeasureDescription = outerUnitOfMeasureDescription;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setOuterQuantityDescription(final java.lang.String outerQuantityDescription) {
		this.outerQuantityDescription = outerQuantityDescription;
	}
}
