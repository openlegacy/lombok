package lombok.test;

@org.openlegacy.annotations.screen.ScreenEntity public class ScreenTableSupportTD {
	
	@org.openlegacy.annotations.screen.ScreenTable(supportTerminalData = true, startRow= 1, endRow = 2) private static class ScreenTable {
		
		@org.openlegacy.annotations.screen.ScreenField(row=1, column=1, endRow=1, endColumn=5)
		private String name;
		
		@org.openlegacy.annotations.screen.ScreenField(row=1, column=1, endRow=1, endColumn=5)
		private String surname;
		
		@org.openlegacy.annotations.screen.ScreenField(row=1, column=1, endRow=1, endColumn=5)
		private Integer age;
		
		@org.openlegacy.annotations.screen.ScreenField(row=1, column=1, endRow=1, endColumn=5)
		private java.math.BigInteger nationalId;

	}
	
	private ScreenTableSupportTD.ScreenTable table;
}