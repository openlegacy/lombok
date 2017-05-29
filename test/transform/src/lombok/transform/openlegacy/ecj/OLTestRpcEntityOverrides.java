package lombok.transform.openlegacy.ecj;

import lombok.DirectoryRunner;
import org.junit.runner.RunWith;

import java.io.File;

// You need to add the following vm-parameter to run the test:
// -javaagent:dist/openlegacy-lombok.jar -Dshadow.override.lombok=${project_loc:openlegacy-lombok}/bin;${project_loc:openlegacy-lombok}/lib/runtime/*
// -Ddelombok.bootclasspath=lib/oracleJDK8Environment/rt.jar
//  also you need to attach openlegacy-openlegacy-api.jar to the test launch to get use of openlegacy annotaions
// When running inside eclipse's junit tester, you don't actually need to run 'ant dist' after updating code, though.

/**
 * @author Matvey Mitnitsky on 24-May-17.
 */
@RunWith(DirectoryRunner.class)
public class OLTestRpcEntityOverrides extends DirectoryRunner.TestParams {

    public static final String OL_BEFORE_DIRECTORY = "test/transform/resource/openlegacy/before/rpc_entity/overrides";
    public static final String OL_AFTER_DIRECTORY = "test/transform/resource/openlegacy/after-ecj/rpc_entity/overrides";

    @Override
    public DirectoryRunner.Compiler getCompiler() {
        return DirectoryRunner.Compiler.ECJ;
    }

    @Override
    public boolean printErrors() {
        return true;
    }

    @Override
    public File getBeforeDirectory() {
        return new File(OL_BEFORE_DIRECTORY);
    }

    @Override
    public File getAfterDirectory() {
        return new File(OL_AFTER_DIRECTORY);
    }

    @Override
    public File getMessagesDirectory() {
        return new File("test/transform/resource/messages-ecj");
    }

    @Override
    public boolean expectChanges() {
        return true;
    }

}
