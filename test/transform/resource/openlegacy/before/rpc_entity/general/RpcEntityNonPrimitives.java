package lombok.test;

@org.openlegacy.core.annotations.rpc.RpcEntity class RpcEntityNonPrimitives {

    private String stringVar = "string";
    private Object objectVar = new Object();
    private java.util.List<String> stringList = new java.util.ArrayList<>();
    private java.util.Set<String> stringSet = new java.util.HashSet<>();
    private java.util.Map<String, String> stringMap = new java.util.HashMap<>();
}