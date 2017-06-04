package lombok.test;

@org.openlegacy.annotations.screen.ScreenEntity(supportTerminalData=false) public class ScreenEntityFieldDescription {
	
	@org.openlegacy.annotations.screen.ScreenField(row = 6, column = 33, endColumn = 52, labelColumn = 2, displayName = "Item Number", sampleValue = "A")
	@org.openlegacy.annotations.screen.ScreenDescriptionField(column=1, row=1)
	private String itemName;
	
	@org.openlegacy.annotations.screen.ScreenField(row = 6, column = 33, endColumn = 52, labelColumn = 2, displayName = "Item Number", sampleValue = "A")
	@org.openlegacy.annotations.screen.ScreenDescriptionField(column=1, row=3)
	private Integer itemNumber;
	
	@org.openlegacy.annotations.screen.ScreenField(key = true, row = 17, column = 33, endColumn = 37, labelColumn = 2, displayName = "Packing Multiplier", sampleValue = "0")
	@org.openlegacy.annotations.screen.ScreenDescriptionField(column=1, row=5)
	private Double packingMultiplier;
	
	@org.openlegacy.annotations.screen.ScreenField(row = 18, column = 33, endColumn = 37, labelColumn = 2, displayName = "Outer Unit of Measure")
	@org.openlegacy.annotations.screen.ScreenDescriptionField(column=1, row=5)
	private java.math.BigInteger outerUnitOfMeasure;
	
	@org.openlegacy.annotations.screen.ScreenField(row = 19, column = 33, endColumn = 41, labelColumn = 2, displayName = "Outer Quantity", sampleValue = "0")
	@org.openlegacy.annotations.screen.ScreenDescriptionField(column=1, row=7)
	private java.math.BigDecimal outerQuantity;
	
}