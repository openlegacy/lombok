package lombok.test;

@org.openlegacy.annotations.rpc.RpcEntity
public class RpcEntityTest{
	
}

@org.openlegacy.annotations.rpc.RpcEntity class RpcEntityTestWithPimitiveFields {

    private boolean booleanVar;
    private char charVar;
    private byte byteVar;
    private short shortVar;
    private int integerVar;
    private long longVar;
    private float floatVar;
    private double doubleVar;

    //initializations
    private boolean iBooleanVar = false;
    private char iCharVar = 'c';
    private byte iByteVar = 127;
    private short iShortVar = 222;
    private int iIntegerVar = 333;
    private long iLongVar = 444L;
    private float iFloatVar = 555.5f;
    private double iDoubleVar = 666.6d;

}