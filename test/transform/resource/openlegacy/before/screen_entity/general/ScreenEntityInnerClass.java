package lombok.test;

@org.openlegacy.core.annotations.screen.ScreenEntity public class ScreenEntityInnerClass {
	
	public static class ScreenTable {
		private String property;
	}
	
	@org.openlegacy.core.annotations.screen.ScreenField(row = 18, endRow = 37, column=1, endColumn=80)
	private ScreenEntityInnerClass.ScreenTable screenTable;
	
}