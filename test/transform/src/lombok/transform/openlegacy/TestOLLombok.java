package lombok.transform.openlegacy;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// You need to add the following vm-parameter to run the test:
// -javaagent:dist/openlegacy-lombok.jar -Dshadow.override.lombok=${project_loc:openlegacy-lombok}/bin;${project_loc:openlegacy-lombok}/lib/runtime/*
// -Ddelombok.bootclasspath=lib/oracleJDK8Environment/rt.jar
//  also you need to attach openlegacy-openlegacy-api.jar to the test launch to get use of openlegacy annotaions
// and javax.persistence.jar to test persistence annotations
// When running inside eclipse's junit tester, you don't actually need to run 'ant dist' after updating code, though.

/**
 * @author Matvey Mitnitsky on 01-Jun-17.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({OLTestWithEcj.class, OLTestWithDelombok.class})
public class TestOLLombok {
}
