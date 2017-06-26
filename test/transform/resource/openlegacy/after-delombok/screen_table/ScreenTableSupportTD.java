package lombok.test;

@org.openlegacy.core.annotations.screen.ScreenEntity
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
public class ScreenTableSupportTD implements org.openlegacy.core.terminal.ScreenEntity {
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


	@org.openlegacy.core.annotations.screen.ScreenTable(supportTerminalData = true, startRow = 1, endRow = 2)
	@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
	private static class ScreenTable {
		@com.fasterxml.jackson.annotation.JsonIgnore
		@javax.xml.bind.annotation.XmlTransient
		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		private org.openlegacy.core.terminal.TerminalField nationalIdField;
		@com.fasterxml.jackson.annotation.JsonIgnore
		@javax.xml.bind.annotation.XmlTransient
		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		private org.openlegacy.core.terminal.TerminalField ageField;
		@com.fasterxml.jackson.annotation.JsonIgnore
		@javax.xml.bind.annotation.XmlTransient
		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		private org.openlegacy.core.terminal.TerminalField surnameField;
		@com.fasterxml.jackson.annotation.JsonIgnore
		@javax.xml.bind.annotation.XmlTransient
		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		private org.openlegacy.core.terminal.TerminalField nameField;
		@com.fasterxml.jackson.annotation.JsonIgnore
		@javax.xml.bind.annotation.XmlTransient
		private java.lang.String focusField;
		@org.openlegacy.core.annotations.screen.ScreenField(row = 1, column = 1, endRow = 1, endColumn = 5)
		private String name;
		@org.openlegacy.core.annotations.screen.ScreenField(row = 1, column = 1, endRow = 1, endColumn = 5)
		private String surname;
		@org.openlegacy.core.annotations.screen.ScreenField(row = 1, column = 1, endRow = 1, endColumn = 5)
		private Integer age;
		@org.openlegacy.core.annotations.screen.ScreenField(row = 1, column = 1, endRow = 1, endColumn = 5)
		private java.math.BigInteger nationalId;

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public ScreenTable() {
		}

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public String getName() {
			return this.name;
		}

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public String getSurname() {
			return this.surname;
		}

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public Integer getAge() {
			return this.age;
		}

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public java.math.BigInteger getNationalId() {
			return this.nationalId;
		}

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public java.lang.String getFocusField() {
			return this.focusField;
		}

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public org.openlegacy.core.terminal.TerminalField getNameField() {
			return this.nameField;
		}

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public org.openlegacy.core.terminal.TerminalField getSurnameField() {
			return this.surnameField;
		}

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public org.openlegacy.core.terminal.TerminalField getAgeField() {
			return this.ageField;
		}

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public org.openlegacy.core.terminal.TerminalField getNationalIdField() {
			return this.nationalIdField;
		}

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public void setName(final String name) {
			this.name = name;
		}

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public void setSurname(final String surname) {
			this.surname = surname;
		}

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public void setAge(final Integer age) {
			this.age = age;
		}

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public void setNationalId(final java.math.BigInteger nationalId) {
			this.nationalId = nationalId;
		}

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public void setFocusField(final java.lang.String focusField) {
			this.focusField = focusField;
		}

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public void setNameField(final org.openlegacy.core.terminal.TerminalField nameField) {
			this.nameField = nameField;
		}

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public void setSurnameField(final org.openlegacy.core.terminal.TerminalField surnameField) {
			this.surnameField = surnameField;
		}

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public void setAgeField(final org.openlegacy.core.terminal.TerminalField ageField) {
			this.ageField = ageField;
		}

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public void setNationalIdField(final org.openlegacy.core.terminal.TerminalField nationalIdField) {
			this.nationalIdField = nationalIdField;
		}

		@java.lang.Override
		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public boolean equals(final java.lang.Object o) {
			if (o == this) return true;
			if (!(o instanceof ScreenTableSupportTD.ScreenTable)) return false;
			final ScreenTableSupportTD.ScreenTable other = (ScreenTableSupportTD.ScreenTable) o;
			if (!other.canEqual((java.lang.Object) this)) return false;
			final java.lang.Object this$name = this.getName();
			final java.lang.Object other$name = other.getName();
			if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
			final java.lang.Object this$surname = this.getSurname();
			final java.lang.Object other$surname = other.getSurname();
			if (this$surname == null ? other$surname != null : !this$surname.equals(other$surname)) return false;
			final java.lang.Object this$age = this.getAge();
			final java.lang.Object other$age = other.getAge();
			if (this$age == null ? other$age != null : !this$age.equals(other$age)) return false;
			final java.lang.Object this$nationalId = this.getNationalId();
			final java.lang.Object other$nationalId = other.getNationalId();
			if (this$nationalId == null ? other$nationalId != null : !this$nationalId.equals(other$nationalId)) return false;
			final java.lang.Object this$focusField = this.getFocusField();
			final java.lang.Object other$focusField = other.getFocusField();
			if (this$focusField == null ? other$focusField != null : !this$focusField.equals(other$focusField)) return false;
			final java.lang.Object this$nameField = this.getNameField();
			final java.lang.Object other$nameField = other.getNameField();
			if (this$nameField == null ? other$nameField != null : !this$nameField.equals(other$nameField)) return false;
			final java.lang.Object this$surnameField = this.getSurnameField();
			final java.lang.Object other$surnameField = other.getSurnameField();
			if (this$surnameField == null ? other$surnameField != null : !this$surnameField.equals(other$surnameField)) return false;
			final java.lang.Object this$ageField = this.getAgeField();
			final java.lang.Object other$ageField = other.getAgeField();
			if (this$ageField == null ? other$ageField != null : !this$ageField.equals(other$ageField)) return false;
			final java.lang.Object this$nationalIdField = this.getNationalIdField();
			final java.lang.Object other$nationalIdField = other.getNationalIdField();
			if (this$nationalIdField == null ? other$nationalIdField != null : !this$nationalIdField.equals(other$nationalIdField)) return false;
			return true;
		}

		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		protected boolean canEqual(final java.lang.Object other) {
			return other instanceof ScreenTableSupportTD.ScreenTable;
		}

		@java.lang.Override
		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public int hashCode() {
			final int PRIME = 59;
			int result = 1;
			final java.lang.Object $name = this.getName();
			result = result * PRIME + ($name == null ? 43 : $name.hashCode());
			final java.lang.Object $surname = this.getSurname();
			result = result * PRIME + ($surname == null ? 43 : $surname.hashCode());
			final java.lang.Object $age = this.getAge();
			result = result * PRIME + ($age == null ? 43 : $age.hashCode());
			final java.lang.Object $nationalId = this.getNationalId();
			result = result * PRIME + ($nationalId == null ? 43 : $nationalId.hashCode());
			final java.lang.Object $focusField = this.getFocusField();
			result = result * PRIME + ($focusField == null ? 43 : $focusField.hashCode());
			final java.lang.Object $nameField = this.getNameField();
			result = result * PRIME + ($nameField == null ? 43 : $nameField.hashCode());
			final java.lang.Object $surnameField = this.getSurnameField();
			result = result * PRIME + ($surnameField == null ? 43 : $surnameField.hashCode());
			final java.lang.Object $ageField = this.getAgeField();
			result = result * PRIME + ($ageField == null ? 43 : $ageField.hashCode());
			final java.lang.Object $nationalIdField = this.getNationalIdField();
			result = result * PRIME + ($nationalIdField == null ? 43 : $nationalIdField.hashCode());
			return result;
		}

		@java.lang.Override
		@java.lang.SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public java.lang.String toString() {
			return "ScreenTableSupportTD.ScreenTable(name=" + this.getName() + ", surname=" + this.getSurname() + ", age=" + this.getAge() + ", nationalId=" + this.getNationalId() + ", focusField=" + this.getFocusField() + ", nameField=" + this.getNameField() + ", surnameField=" + this.getSurnameField() + ", ageField=" + this.getAgeField() + ", nationalIdField=" + this.getNationalIdField() + ")";
		}
	}

	private ScreenTableSupportTD.ScreenTable table;

	@java.lang.SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public ScreenTableSupportTD.ScreenTable getTable() {
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
	public void setTable(final ScreenTableSupportTD.ScreenTable table) {
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
}
