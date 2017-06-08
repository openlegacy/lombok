package lombok.test;

@org.openlegacy.core.annotations.screen.ScreenEntity public class ScreenTableAsList {
	
	@org.openlegacy.core.annotations.screen.ScreenTable(startRow=1, endRow=1)
	private static class ScreenTbl {
		
		@org.openlegacy.core.annotations.screen.ScreenField(row=1, column=1, endRow=1, endColumn=5)
		private String tableField;
	}
	
	@org.openlegacy.core.annotations.screen.ScreenField(row=1, column=1, endRow=1, endColumn=5)
	private java.util.List<ScreenTableAsList.ScreenTbl> table;
}