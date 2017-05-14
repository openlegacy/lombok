@lombok.OLData(org.openlegacy.terminal.ScreenEntity.class) @org.openlegacy.annotations.screen.ScreenEntity(supportTerminalData = true) class OLDataOnClass {
	private String stringField = "";
	@org.openlegacy.annotations.screen.ScreenField(column=2, row=10)
	private int primitive;
	@org.openlegacy.annotations.screen.ScreenField(column=2, row=11)
	@org.openlegacy.annotations.screen.ScreenDescriptionField(column=2, row=11)
	private java.util.List<String> stringList;
	
	private String focusField;
	private String pcCommand;
	
	public int getPrimitive(){
		return this.primitive;
	}
	
	public String getStringField(){
		return this.stringField;
	}
	
	public void setStringField(String stringField){
		this.stringField = stringField;
	}
	
	@org.openlegacy.annotations.screen.ScreenTable(startRow=1, endRow=3)
	public static class InnerRecord {
		private String innerRecName;
	}
}
