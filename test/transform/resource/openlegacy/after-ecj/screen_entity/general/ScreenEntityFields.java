package lombok.test;
public @org.openlegacy.annotations.screen.ScreenEntity class ScreenEntityFields implements org.openlegacy.terminal.ScreenEntity {
  private java.util.List<org.openlegacy.terminal.definitions.TerminalActionDefinition> actions = new java.util.ArrayList<org.openlegacy.terminal.definitions.TerminalActionDefinition>();
  private java.lang.String pcCommand;
  private java.lang.String focusField;
  private @org.openlegacy.annotations.screen.ScreenField(row = 6,column = 33,endColumn = 52,labelColumn = 2,displayName = "Item Number",sampleValue = "A") String itemName;
  private @org.openlegacy.annotations.screen.ScreenField(row = 6,column = 33,endColumn = 52,labelColumn = 2,displayName = "Item Number",sampleValue = "A") Integer itemNumber;
  private @org.openlegacy.annotations.screen.ScreenField(key = true,row = 17,column = 33,endColumn = 37,labelColumn = 2,displayName = "Packing Multiplier",sampleValue = "0") Double packingMultiplier;
  private @org.openlegacy.annotations.screen.ScreenField(row = 18,column = 33,endColumn = 37,labelColumn = 2,displayName = "Outer Unit of Measure") java.math.BigInteger outerUnitOfMeasure;
  private @org.openlegacy.annotations.screen.ScreenField(row = 19,column = 33,endColumn = 41,labelColumn = 2,displayName = "Outer Quantity",sampleValue = "0") java.math.BigDecimal outerQuantity;
  public ScreenEntityFields() {
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
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") java.util.List<org.openlegacy.terminal.definitions.TerminalActionDefinition> getActions() {
    return this.actions;
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
  public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") void setActions(final java.util.List<org.openlegacy.terminal.definitions.TerminalActionDefinition> actions) {
    this.actions = actions;
  }
}