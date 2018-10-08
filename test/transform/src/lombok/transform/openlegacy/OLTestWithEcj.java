package lombok.transform.openlegacy;

import lombok.transform.openlegacy.ecj.OLTestRpcEntity;
import lombok.transform.openlegacy.ecj.OLTestRpcEntityOverrides;
import lombok.transform.openlegacy.ecj.OLTestRpcPart;
import lombok.transform.openlegacy.ecj.OLTestScreenEntity;
import lombok.transform.openlegacy.ecj.OLTestScreenTable;
import lombok.transform.openlegacy.ecj.OLTestSuperClass;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// You need to add the following vm-parameter to run the test:
// -javaagent:dist/openlegacy-lombok.jar -Dshadow.override.lombok=${project_loc:openlegacy-lombok}/bin;${project_loc:openlegacy-lombok}/lib/runtime/*
// -Ddelombok.bootclasspath=lib/oracleJDK8Environment/rt.jar
//  also you need to attach openlegacy-openlegacy-api.jar to the test launch to get use of openlegacy annotaions
// and javax.persistence.jar to test persistence annotations
// Important! Edit your Eclipse JRE and add tools.jar from JAVA_HOME/lib folder (Otherwise you'll be not able to run Delombok tests)
// When running inside eclipse's junit tester, you don't actually need to run 'ant dist' after updating code, though.

/**
 * @author Matvey Mitnitsky on 24-May-17.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        OLTestRpcEntity.class, OLTestRpcEntityOverrides.class,
        OLTestScreenEntity.class, OLTestRpcPart.class,
        OLTestScreenTable.class, OLTestSuperClass.class
})
public class OLTestWithEcj {
}
