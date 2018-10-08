package lombok.transform.openlegacy;

import lombok.transform.openlegacy.delombok.OLTestRpcEntity;
import lombok.transform.openlegacy.delombok.OLTestRpcEntityOverrides;
import lombok.transform.openlegacy.delombok.OLTestRpcPart;
import lombok.transform.openlegacy.delombok.OLTestScreenEntity;
import lombok.transform.openlegacy.delombok.OLTestScreenTable;
import lombok.transform.openlegacy.delombok.OLTestSuperClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Matvey Mitnitsky on 24-May-17.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        OLTestRpcEntity.class, OLTestRpcEntityOverrides.class, OLTestScreenEntity.class,
        OLTestRpcPart.class, OLTestScreenTable.class, OLTestSuperClass.class
})
public class OLTestWithDelombok {
}
