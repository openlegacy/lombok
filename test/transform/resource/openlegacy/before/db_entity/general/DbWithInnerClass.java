package lombok.test;

@org.openlegacy.core.annotations.db.DbEntity class DbWithInnerClass {
	public static class InnerPart {
		public String var;
	}
	
	lombok.test.DbWithInnerClass.InnerPart inner;
}