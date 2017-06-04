package lombok.test;

@org.openlegacy.annotations.screen.ScreenEntity(supportTerminalData=false) public class ScreenEntityTableActions {
	private static class ScreenTable {
	}
	
	@org.openlegacy.annotations.screen.ScreenField(row = 6, column = 33, endColumn = 52, labelColumn = 2, displayName = "Item Number", sampleValue = "A")
	private String itemName;
	
	@org.openlegacy.annotations.screen.ScreenField(row = 6, column = 33, endColumn = 52, labelColumn = 2, displayName = "Item Number", sampleValue = "A")
	private java.util.List<ScreenEntityTableActions.ScreenTable> tableInstances;
	
}