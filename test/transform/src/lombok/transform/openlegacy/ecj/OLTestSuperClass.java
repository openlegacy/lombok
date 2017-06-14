package lombok.transform.openlegacy.ecj;

import lombok.DirectoryRunner;
import org.junit.runner.RunWith;

import java.io.File;

/**
 * @author Matvey Mitnitsky on 14-Jun-17.
 */
@RunWith(DirectoryRunner.class)
public class OLTestSuperClass extends DirectoryRunner.TestParams {

    public static final String OL_BEFORE_DIRECTORY = "test/transform/resource/openlegacy/before/super_class";
    public static final String OL_AFTER_DIRECTORY = "test/transform/resource/openlegacy/after-ecj/super_class";

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
