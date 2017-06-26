package lombok.test;
public @org.openlegacy.core.annotations.screen.ScreenEntity(supportTerminalData = true) @javax.xml.bind.annotation.XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD) class ScreenEntitySupportTD implements org.openlegacy.core.terminal.ScreenEntity {
  private @com.fasterxml.jackson.annotation.JsonIgnore() @javax.xml.bind.annotation.XmlTransient() org.openlegacy.core.terminal.TerminalField outerQuantityField;
  private @com.fasterxml.jackson.annotation.JsonIgnore() @javax.xml.bind.annotation.XmlTransient() org.openlegacy.core.terminal.TerminalField outerUnitOfMeasureField;
  private @com.fasterxml.jackson.annotation.JsonIgnore() @javax.xml.bind.annotation.XmlTransient() org.openlegacy.core.terminal.TerminalField packingMultiplierField;
  private @com.fasterxml.jackson.annotation.JsonIgnore() @javax.xml.bind.annotation.XmlTransient() org.openlegacy.core.terminal.TerminalField itemNumberField;
  private @com.fasterxml.jackson.annotation.JsonIgnore() @javax.xml.bind.annotation.XmlTransient() org.openlegacy.core.terminal.TerminalField itemNameField;
  private @com.fasterxml.jackson.annotation.JsonIgnore() @javax.xml.bind.annotation.XmlTransient() java.util.List<org.openlegacy.core.terminal.definitions.TerminalActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.terminal.definitions.TerminalActionDefinition>();
  private @com.fasterxml.jackson.annotation.JsonIgnore() @javax.xml.bind.annotation.XmlTransient() java.lang.String pcCommand;
  private @com.fasterxml.jackson.annotation.JsonIgnore() @javax.xml.bind.annotation.XmlTransient() java.lang.String focusField;
  private @com.fasterxml.jackson.annotation.JsonIgnore() @javax.xml.bind.annotation.XmlTransient() org.openlegacy.core.terminal.TerminalSnapshot terminalSnapshot;
  private @org.openlegacy.core.annotations.screen.ScreenField(row = 6,column = 33,endColumn = 52,labelColumn = 2,displayName = "Item Name",sampleValue = "A") String itemName;
  private @org.openlegacy.core.annotations.screen.ScreenField(row = 6,column = 33,endColumn = 52,labelColumn = 2,displayName = "Item Number",sampleValue = "A") Integer itemNumber;
  private @org.openlegacy.core.annotations.screen.ScreenField(key = true,row = 17,column = 33,endColumn = 37,labelColumn = 2,displayName = "Packing Multiplier",sampleValue = "0") Double packingMultiplier;
  private @org.openlegacy.core.annotations.screen.ScreenField(row = 18,column = 33,endColumn = 37,labelColumn = 2,displayName = "Outer Unit of Measure") java.math.BigInteger outerUnitOfMeasure;
  private @org.openlegacy.core.annotations.screen.ScreenField(row = 19,column = 33,endColumn = 41,labelColumn = 2,displayName = "Outer Quantity",sampleValue = "0") java.math.BigDecimal outerQuantity;
  public ScreenEntitySupportTD() {
    super();
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") String getItemName() {
    return this.itemName;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") Integer getItemNumber() {
    return this.itemNumber;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") Double getPackingMultiplier() {
    return this.packingMultiplier;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.math.BigInteger getOuterUnitOfMeasure() {
    return this.outerUnitOfMeasure;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.math.BigDecimal getOuterQuantity() {
    return this.outerQuantity;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") org.openlegacy.core.terminal.TerminalSnapshot getTerminalSnapshot() {
    return this.terminalSnapshot;
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
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") org.openlegacy.core.terminal.TerminalField getItemNameField() {
    return this.itemNameField;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") org.openlegacy.core.terminal.TerminalField getItemNumberField() {
    return this.itemNumberField;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") org.openlegacy.core.terminal.TerminalField getPackingMultiplierField() {
    return this.packingMultiplierField;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") org.openlegacy.core.terminal.TerminalField getOuterUnitOfMeasureField() {
    return this.outerUnitOfMeasureField;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") org.openlegacy.core.terminal.TerminalField getOuterQuantityField() {
    return this.outerQuantityField;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setItemName(final String itemName) {
    this.itemName = itemName;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setItemNumber(final Integer itemNumber) {
    this.itemNumber = itemNumber;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setPackingMultiplier(final Double packingMultiplier) {
    this.packingMultiplier = packingMultiplier;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setOuterUnitOfMeasure(final java.math.BigInteger outerUnitOfMeasure) {
    this.outerUnitOfMeasure = outerUnitOfMeasure;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setOuterQuantity(final java.math.BigDecimal outerQuantity) {
    this.outerQuantity = outerQuantity;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setTerminalSnapshot(final org.openlegacy.core.terminal.TerminalSnapshot terminalSnapshot) {
    this.terminalSnapshot = terminalSnapshot;
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
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setItemNameField(final org.openlegacy.core.terminal.TerminalField itemNameField) {
    this.itemNameField = itemNameField;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setItemNumberField(final org.openlegacy.core.terminal.TerminalField itemNumberField) {
    this.itemNumberField = itemNumberField;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setPackingMultiplierField(final org.openlegacy.core.terminal.TerminalField packingMultiplierField) {
    this.packingMultiplierField = packingMultiplierField;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setOuterUnitOfMeasureField(final org.openlegacy.core.terminal.TerminalField outerUnitOfMeasureField) {
    this.outerUnitOfMeasureField = outerUnitOfMeasureField;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setOuterQuantityField(final org.openlegacy.core.terminal.TerminalField outerQuantityField) {
    this.outerQuantityField = outerQuantityField;
  }
}
