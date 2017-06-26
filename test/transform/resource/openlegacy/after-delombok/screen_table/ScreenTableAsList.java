package lombok.test;

@org.openlegacy.core.annotations.screen.ScreenEntity
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
public class ScreenTableAsList implements org.openlegacy.core.terminal.ScreenEntity {
	@com.fasterxml.jackson.annotation.JsonIgnore
	@javax.xml.bind.annotation.XmlTransient
	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	private java.util.List<org.openlegacy.core.terminal.definitions.TerminalActionDefinition> tableActions = new java.util.ArrayList<org.openlegacy.core.terminal.definitions.TerminalActionDefinition>();
	@com.fasterxml.jackson.annotation.JsonIgnore
	@javax.xml.bind.annotation.XmlTransient
	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	private java.lang.String pcCommand;
	@com.fasterxml.jackson.annotation.JsonIgnore
	@javax.xml.bind.annotation.XmlTransient
	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	private java.lang.String focusField;
	@com.fasterxml.jackson.annotation.JsonIgnore
	@javax.xml.bind.annotation.XmlTransient
	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	private java.util.List<org.openlegacy.core.terminal.definitions.TerminalActionDefinition> actions = new java.util.ArrayList<org.openlegacy.core.terminal.definitions.TerminalActionDefinition>();


	@org.openlegacy.core.annotations.screen.ScreenTable(startRow = 1, endRow = 1)
	@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
	private static class ScreenTbl {
		@com.fasterxml.jackson.annotation.JsonIgnore
		@javax.xml.bind.annotation.XmlTransient
		private java.lang.String focusField;
		@org.openlegacy.core.annotations.screen.ScreenField(row = 1, column = 1, endRow = 1, endColumn = 5)
		private String tableField;

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public ScreenTbl() {
		}

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public String getTableField() {
			return this.tableField;
		}

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public java.lang.String getFocusField() {
			return this.focusField;
		}

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public void setTableField(final String tableField) {
			this.tableField = tableField;
		}

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public void setFocusField(final java.lang.String focusField) {
			this.focusField = focusField;
		}

		@java.lang.Override
		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public boolean equals(final java.lang.Object o) {
			if (o == this) return true;
			if (!(o instanceof ScreenTableAsList.ScreenTbl)) return false;
			final ScreenTableAsList.ScreenTbl other = (ScreenTableAsList.ScreenTbl) o;
			if (!other.canEqual((java.lang.Object) this)) return false;
			final java.lang.Object this$tableField = this.getTableField();
			final java.lang.Object other$tableField = other.getTableField();
			if (this$tableField == null ? other$tableField != null : !this$tableField.equals(other$tableField)) return false;
			final java.lang.Object this$focusField = this.getFocusField();
			final java.lang.Object other$focusField = other.getFocusField();
			if (this$focusField == null ? other$focusField != null : !this$focusField.equals(other$focusField)) return false;
			return true;
		}

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		protected boolean canEqual(final java.lang.Object other) {
			return other instanceof ScreenTableAsList.ScreenTbl;
		}

		@java.lang.Override
		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public int hashCode() {
			final int PRIME = 59;
			int result = 1;
			final java.lang.Object $tableField = this.getTableField();
			result = result * PRIME + ($tableField == null ? 43 : $tableField.hashCode());
			final java.lang.Object $focusField = this.getFocusField();
			result = result * PRIME + ($focusField == null ? 43 : $focusField.hashCode());
			return result;
		}

		@java.lang.Override
		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public java.lang.String toString() {
			return "ScreenTableAsList.ScreenTbl(tableField=" + this.getTableField() + ", focusField=" + this.getFocusField() + ")";
		}
	}

	@org.openlegacy.core.annotations.screen.ScreenField(row = 1, column = 1, endRow = 1, endColumn = 5)
	private java.util.List<ScreenTableAsList.ScreenTbl> table;

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public java.util.List<ScreenTableAsList.ScreenTbl> getTable() {
		return this.table;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public java.util.List<org.openlegacy.core.terminal.definitions.TerminalActionDefinition> getActions() {
		return this.actions;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public java.lang.String getFocusField() {
		return this.focusField;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public java.lang.String getPcCommand() {
		return this.pcCommand;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public java.util.List<org.openlegacy.core.terminal.definitions.TerminalActionDefinition> getTableActions() {
		return this.tableActions;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setTable(final java.util.List<ScreenTableAsList.ScreenTbl> table) {
		this.table = table;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setActions(final java.util.List<org.openlegacy.core.terminal.definitions.TerminalActionDefinition> actions) {
		this.actions = actions;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setFocusField(final java.lang.String focusField) {
		this.focusField = focusField;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setPcCommand(final java.lang.String pcCommand) {
		this.pcCommand = pcCommand;
	}

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public void setTableActions(final java.util.List<org.openlegacy.core.terminal.definitions.TerminalActionDefinition> tableActions) {
		this.tableActions = tableActions;
	}
}
