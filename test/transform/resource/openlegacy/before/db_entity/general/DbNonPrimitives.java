package lombok.test;

@org.openlegacy.annotations.db.DbEntity class DbNonPrimitives {

    private String stringVar = "string";
    private Object objectVar = new Object();
    private java.util.List<String> stringList = new java.util.ArrayList<>();
    private java.util.Set<String> stringSet = new java.util.HashSet<>();
    private java.util.Map<String, String> stringMap = new java.util.HashMap<>();

}
