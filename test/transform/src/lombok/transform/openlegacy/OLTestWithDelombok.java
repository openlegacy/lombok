package lombok.transform.openlegacy;

/**
 * @author Matvey Mitnitsky on 24-May-17.
 */

import lombok.transform.openlegacy.delombok.OLTestDbEntity;
import lombok.transform.openlegacy.delombok.OLTestDbEntityOverrides;
import lombok.transform.openlegacy.delombok.OLTestRpcEntity;
import lombok.transform.openlegacy.delombok.OLTestRpcEntityOverrides;
import lombok.transform.openlegacy.delombok.OLTestRpcPart;
import lombok.transform.openlegacy.delombok.OLTestScreenEntity;
import lombok.transform.openlegacy.delombok.OLTestScreenTable;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        OLTestRpcEntity.class, OLTestRpcEntityOverrides.class, OLTestDbEntity.class,
        OLTestDbEntityOverrides.class, OLTestScreenEntity.class, OLTestRpcPart.class,
        OLTestScreenTable.class
})
public class OLTestWithDelombok {
}
