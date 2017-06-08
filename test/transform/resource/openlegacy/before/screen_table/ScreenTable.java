package lombok.test;

@org.openlegacy.core.annotations.screen.ScreenEntity public class ScreenTable {
	
	@org.openlegacy.core.annotations.screen.ScreenTable(startRow=1, endRow=1)
	private static class ScreenTbl {
		
		@org.openlegacy.core.annotations.screen.ScreenField(row=1, column=1, endRow=1, endColumn=5)
		private String tableField;
	}
	
	private ScreenTable.ScreenTbl table;
}