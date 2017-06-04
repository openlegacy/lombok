package lombok.transform.openlegacy.ecj;

import lombok.DirectoryRunner;
import org.junit.runner.RunWith;

import java.io.File;

/**
 * @author Matvey Mitnitsky on 01-Jun-17.
 */
@RunWith(DirectoryRunner.class)
public class OLTestScreenTable extends DirectoryRunner.TestParams {

    public static final String OL_BEFORE_DIRECTORY = "test/transform/resource/openlegacy/before/screen_table";
    public static final String OL_AFTER_DIRECTORY = "test/transform/resource/openlegacy/after-ecj/screen_table";

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