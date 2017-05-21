@org.openlegacy.annotations.screen.ScreenEntity
public class ScreenEntityTest {

}

@org.openlegacy.annotations.screen.ScreenEntity
public class ScreenEntityTestWithPrimitiveFields {

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

@org.openlegacy.annotations.screen.ScreenEntity
public class ScreenEntityTestWithNonPrimitiveFields {

    private String stringVar = "string";
    private Object objectVar = new Object();
    private List<String> stringList = new ArrayList();
    private Set<String> stringSet = new HashSet();
    private Map<String, String> stringMap = new HashMap();

}


@org.openlegacy.annotations.screen.ScreenEntity
public class ScreenEntityTestWithScreenField {

    @ScreenField(row = 6, column = 33, endColumn= 52 ,labelColumn= 2 ,displayName = "Item Number", sampleValue = "A")
    private String itemNumber;

    @ScreenField(key = true, row = 17, column = 33, endColumn= 37 ,labelColumn= 2 ,displayName = "Packing Multiplier", sampleValue = "0")
    private Integer packingMultiplier;

    @ScreenField(row = 18, column = 33, endColumn= 37 ,labelColumn= 2 ,displayName = "Outer Unit of Measure")
    private BigInteger outerUnitOfMeasure;

    @ScreenField(row = 19, column = 33, endColumn= 41 ,labelColumn= 2 ,displayName = "Outer Quantity", sampleValue = "0")
    private Integer outerQuantity;

}


@org.openlegacy.annotations.screen.ScreenEntity(supportTerminalData = true)
public class ScreenEntityWithScreenFieldAndTerminalData {

    @ScreenField(row = 6, column = 33, endColumn= 52 ,labelColumn= 2 ,displayName = "Item Number", sampleValue = "A")
    private String itemNumber;

    @ScreenField(key = true, row = 17, column = 33, endColumn= 37 ,labelColumn= 2 ,displayName = "Packing Multiplier", sampleValue = "0")
    private Integer packingMultiplier;

    @ScreenField(row = 18, column = 33, endColumn= 37 ,labelColumn= 2 ,displayName = "Outer Unit of Measure")
    private BigInteger outerUnitOfMeasure;

    @ScreenField(row = 19, column = 33, endColumn= 41 ,labelColumn= 2 ,displayName = "Outer Quantity", sampleValue = "0")
    private BigDecimal outerQuantity;

}















