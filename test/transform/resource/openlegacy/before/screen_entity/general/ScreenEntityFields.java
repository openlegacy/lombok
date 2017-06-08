package lombok.test;

@org.openlegacy.core.annotations.screen.ScreenEntity public class ScreenEntityFields {
	
	@org.openlegacy.core.annotations.screen.ScreenField(row = 6, column = 33, endColumn = 52, labelColumn = 2, displayName = "Item Number", sampleValue = "A") private String itemName;
	
	@org.openlegacy.core.annotations.screen.ScreenField(row = 6, column = 33, endColumn = 52, labelColumn = 2, displayName = "Item Number", sampleValue = "A") private Integer itemNumber;
	
	@org.openlegacy.core.annotations.screen.ScreenField(key = true, row = 17, column = 33, endColumn = 37, labelColumn = 2, displayName = "Packing Multiplier", sampleValue = "0") private Double packingMultiplier;
	
	@org.openlegacy.core.annotations.screen.ScreenField(row = 18, column = 33, endColumn = 37, labelColumn = 2, displayName = "Outer Unit of Measure") private java.math.BigInteger outerUnitOfMeasure;
	
	@org.openlegacy.core.annotations.screen.ScreenField(row = 19, column = 33, endColumn = 41, labelColumn = 2, displayName = "Outer Quantity", sampleValue = "0") private java.math.BigDecimal outerQuantity;

}