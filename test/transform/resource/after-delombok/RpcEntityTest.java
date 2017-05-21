@org.openlegacy.annotations.rpc.RpcEntity
public class RpcEntityTest {

}

@org.openlegacy.annotations.rpc.RpcEntity
public class RpcEntityTestWithPrimitives {

    private boolean booleanVar;
    private char charVar;
    private byte byteVar;
    private short shortVar;
    private int integerVar;
    private long longVar;
    private float floatVar;
    private double doubleVar;

    //initialized primitives
    private boolean iBooleanVar = false;
    private char iCharVar = 'c';
    private short iShortVar = 1111;
    private int iIntegerVar = 2222;
    private long iLongVar = 3333L;
    private float iFloatVar = 4444.4f;
    private double iDoubleVar = 5555.5d;

}

@org.openlegacy.annotations.rpc.RpcEntity
public class ScreenEntityTestWithNonPrimitiveFields {

    private String stringVar = "string";
    private Object objectVar = new Object();
    private List<String> stringList = new ArrayList();
    private Set<String> stringSet = new HashSet();
    private Map<String, String> stringMap = new HashMap();

}

@org.openlegacy.annotations.rpc.RpcEntity(name="Itemde", language=Languages.COBOL)
public class Itemde {

    private Dfhcommarea dfhcommarea;

    @org.openlegacy.annotations.rpc.RpcPart(name = "Dfhcommarea", originalName = "DFHCOMMAREA", displayName = "Dfhcommarea")
    public static class Dfhcommarea {

        @org.openlegacy.annotations.rpc.RpcNumericField(minimumValue = -99999999, maximumValue = 99999999, decimalPlaces = 0)
        @org.openlegacy.annotations.rpc.RpcField(length = 4, originalName = "ITEM-NUM", legacyType = "Binary Integer")
        private Integer itemNum;
        private ItemRecord itemRecord;
        private Shipping shipping;
    }
}

@org.openlegacy.annotations.rpc.RpcEntity(name="Itemde", language=Languages.COBOL)
public class Items {

    @org.openlegacy.annotations.rpc.RpcList(count = 5)
    private List<InnerRecord> innerRecord;

    @org.openlegacy.annotations.rpc.RpcPart(name = "InnerRecord", legacyContainerName = "Dfhcommarea", originalName = "INNER-RECORD", displayName = "INNERRECORD")
    public static class InnerRecord {

        @org.openlegacy.annotations.rpc.RpcNumericField(minimumValue = -9999, maximumValue = 9999, decimalPlaces = 0)
        @RpcField(length = 2, originalName = "ITEM-NUMBER", legacyType = "Binary Integer")
        private Integer itemNumber;

        @org.openlegacy.annotations.rpc.RpcField(length = 16, originalName = "ITEM-NAME", legacyType = "Char")
        private String itemName;

        @org.openlegacy.annotations.rpc.RpcField(length = 28, originalName = "DESCRIPTION", legacyType = "Char")
        private String description;
    }

}
