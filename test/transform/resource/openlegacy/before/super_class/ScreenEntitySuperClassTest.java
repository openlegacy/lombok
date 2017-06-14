package lombok.test;

@org.openlegacy.core.annotations.screen.ScreenEntitySuperClass(supportTerminalData=true) 
class ScreenEntitySuperClassTest {
	
	@org.openlegacy.core.annotations.screen.ScreenField(row=1, column=1)
	@org.openlegacy.core.annotations.screen.ScreenDescriptionField(row=2, column=2)
	private String superClassField;
}