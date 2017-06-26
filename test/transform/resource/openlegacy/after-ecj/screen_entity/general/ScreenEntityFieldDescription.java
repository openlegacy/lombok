package lombok.test;
public @org.openlegacy.core.annotations.screen.ScreenEntity(supportTerminalData = false) @javax.xml.bind.annotation.XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD) class ScreenEntityFieldDescription implements org.openlegacy.core.terminal.ScreenEntity {
  private @com.fasterxml.jackson.annotation.JsonIgnore() @javax.xml.bind.annotation.XmlTransient() java.lang.String outerQuantityDescription;
  private @com.fasterxml.jackson.annotation.JsonIgnore() @javax.xml.bind.annotation.XmlTransient() java.lang.String outerUnitOfMeasureDescription;
  private @com.fasterxml.jackson.annotation.JsonIgnore() @javax.xml.bind.annotation.XmlTransient() java.lang.String packingMultiplierDescription;
  private @com.fasterxml.jackson.annotation.JsonIgnore() @javax.xml.bind.annotation.XmlTransient() java.lang.String itemNumberDescription;
  private @com.fasterxml.jackson.annotation.JsonIgnore() @javax.xml.bind.annotation.XmlTransient() java.lang.String itemNameDescription;
  private @com.fasterxml.jackson.annotation.JsonIgnore() @javax.xml.bind.annotation.XmlTransient() java.util.List<org.openlegacy.core.terminal.definitions.TerminalActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.terminal.definitions.TerminalActionDefinition>();
  private @com.fasterxml.jackson.annotation.JsonIgnore() @javax.xml.bind.annotation.XmlTransient() java.lang.String pcCommand;
  private @com.fasterxml.jackson.annotation.JsonIgnore() @javax.xml.bind.annotation.XmlTransient() java.lang.String focusField;
  private @org.openlegacy.core.annotations.screen.ScreenField(row = 6,column = 33,endColumn = 52,labelColumn = 2,displayName = "Item Number",sampleValue = "A") @org.openlegacy.core.annotations.screen.ScreenDescriptionField(column = 1,row = 1) String itemName;
  private @org.openlegacy.core.annotations.screen.ScreenField(row = 6,column = 33,endColumn = 52,labelColumn = 2,displayName = "Item Number",sampleValue = "A") @org.openlegacy.core.annotations.screen.ScreenDescriptionField(column = 1,row = 3) Integer itemNumber;
  private @org.openlegacy.core.annotations.screen.ScreenField(key = true,row = 17,column = 33,endColumn = 37,labelColumn = 2,displayName = "Packing Multiplier",sampleValue = "0") @org.openlegacy.core.annotations.screen.ScreenDescriptionField(column = 1,row = 5) Double packingMultiplier;
  private @org.openlegacy.core.annotations.screen.ScreenField(row = 18,column = 33,endColumn = 37,labelColumn = 2,displayName = "Outer Unit of Measure") @org.openlegacy.core.annotations.screen.ScreenDescriptionField(column = 1,row = 5) java.math.BigInteger outerUnitOfMeasure;
  private @org.openlegacy.core.annotations.screen.ScreenField(row = 19,column = 33,endColumn = 41,labelColumn = 2,displayName = "Outer Quantity",sampleValue = "0") @org.openlegacy.core.annotations.screen.ScreenDescriptionField(column = 1,row = 7) java.math.BigDecimal outerQuantity;
  public ScreenEntityFieldDescription() {
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
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.lang.String getFocusField() {
    return this.focusField;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.lang.String getPcCommand() {
    return this.pcCommand;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<org.openlegacy.core.terminal.definitions.TerminalActionDefinition> getActions() {
    return this.actions;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.lang.String getItemNameDescription() {
    return this.itemNameDescription;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.lang.String getItemNumberDescription() {
    return this.itemNumberDescription;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.lang.String getPackingMultiplierDescription() {
    return this.packingMultiplierDescription;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.lang.String getOuterUnitOfMeasureDescription() {
    return this.outerUnitOfMeasureDescription;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.lang.String getOuterQuantityDescription() {
    return this.outerQuantityDescription;
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
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setFocusField(final java.lang.String focusField) {
    this.focusField = focusField;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setPcCommand(final java.lang.String pcCommand) {
    this.pcCommand = pcCommand;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setActions(final java.util.List<org.openlegacy.core.terminal.definitions.TerminalActionDefinition> actions) {
    this.actions = actions;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setItemNameDescription(final java.lang.String itemNameDescription) {
    this.itemNameDescription = itemNameDescription;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setItemNumberDescription(final java.lang.String itemNumberDescription) {
    this.itemNumberDescription = itemNumberDescription;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setPackingMultiplierDescription(final java.lang.String packingMultiplierDescription) {
    this.packingMultiplierDescription = packingMultiplierDescription;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setOuterUnitOfMeasureDescription(final java.lang.String outerUnitOfMeasureDescription) {
    this.outerUnitOfMeasureDescription = outerUnitOfMeasureDescription;
  }
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setOuterQuantityDescription(final java.lang.String outerQuantityDescription) {
    this.outerQuantityDescription = outerQuantityDescription;
  }
}