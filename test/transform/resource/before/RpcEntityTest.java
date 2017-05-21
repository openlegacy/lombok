@org.openlegacy.annotations.rpc.RpcEntity
public class RpcEntityTest implements org.openlegacy.rpc.RpcEntity {

}


@org.openlegacy.annotations.rpc.RpcEntity(name="Itemde", language=Languages.COBOL)
public class Itemde implements org.openlegacy.rpc.RpcEntity {

    private List<RpcActionDefinition> actions = new ArrayList();

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
public class Itemde implements org.openlegacy.rpc.RpcEntity {

    private List<RpcActionDefinition> actions = new ArrayList();

    private Dfhcommarea dfhcommarea;

    @org.openlegacy.annotations.rpc.RpcPart(name = "Dfhcommarea", originalName = "DFHCOMMAREA", displayName = "Dfhcommarea")
    public static class Dfhcommarea {

        @org.openlegacy.annotations.rpc.RpcNumericField(minimumValue = -99999999, maximumValue = 99999999, decimalPlaces = 0)
        @org.openlegacy.annotations.rpc.RpcField(length = 4, originalName = "ITEM-NUM", legacyType = "Binary Integer")
        private Integer itemNum;
        private ItemRecord itemRecord;
        private Shipping shipping;
    }

    public List<RpcActionDefinition> getActions(){
        return actions;
    }

    public void setActions(List<RpcActionDefinition> actions){
        this.actions = actions;
    }
}

@org.openlegacy.annotations.rpc.RpcEntity(name="Itemde", language=Languages.COBOL)
public class Itemde implements org.openlegacy.rpc.RpcEntity {

    private List<RpcActionDefinition> actions = new ArrayList();

    private Dfhcommarea dfhcommarea;

    @org.openlegacy.annotations.rpc.RpcPart(name = "Dfhcommarea", originalName = "DFHCOMMAREA", displayName = "Dfhcommarea")
    public static class Dfhcommarea {

        @org.openlegacy.annotations.rpc.RpcNumericField(minimumValue = -99999999, maximumValue = 99999999, decimalPlaces = 0)
        @org.openlegacy.annotations.rpc.RpcField(length = 4, originalName = "ITEM-NUM", legacyType = "Binary Integer")
        private Integer itemNum;
        private ItemRecord itemRecord;
        private Shipping shipping;
    }

    public List<RpcActionDefinition> getActions(){
        return actions;
    }

    public Dfhcommarea getDfhcommarea(){
        return dfhcommarea;
    }

    public void setActions(List<RpcActionDefinition> actions){
        this.actions = actions;
    }

    public void setDfhcommarea(Dfhcommarea dfhcommarea){
        this.dfhcommarea = dfhcommarea;
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

@lombok.Getter
@lombok.Setter
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

@lombok.Data
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
