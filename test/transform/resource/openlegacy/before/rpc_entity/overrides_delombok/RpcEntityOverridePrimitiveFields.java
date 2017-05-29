package lombok.test;

@org.openlegacy.annotations.rpc.RpcEntity class RpcEntityOverridePrimitiveFields implements org.openlegacy.rpc.RpcEntity {
    private java.util.List<org.openlegacy.definitions.RpcActionDefinition> actions = new java.util.ArrayList<org.openlegacy.definitions.RpcActionDefinition>();
    private boolean booleanVar;
    private char charVar;
    private byte byteVar;
    private short shortVar;
    private int integerVar;
    private long longVar;
    private float floatVar;
    private double doubleVar;
    private boolean iBooleanVar = false;
    private char iCharVar = 'c';
    private byte iByteVar = 127;
    private short iShortVar = 222;
    private int iIntegerVar = 333;
    private long iLongVar = 444L;
    private float iFloatVar = 555.5f;
    private double iDoubleVar = 666.6d;
    RpcEntityOverridePrimitiveFields() {
        super();
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") boolean isBooleanVar() {
        return this.booleanVar;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") char getCharVar() {
        return this.charVar;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") byte getByteVar() {
        return this.byteVar;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") short getShortVar() {
        return this.shortVar;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") int getIntegerVar() {
        return this.integerVar;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") long getLongVar() {
        return this.longVar;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") float getFloatVar() {
        return this.floatVar;
    }
    public @java.lang.SuppressWarnings("all") @javax.annotation.Generated("lombok") double getDoubleVar() {
        return this.doubleVar;
    }
}