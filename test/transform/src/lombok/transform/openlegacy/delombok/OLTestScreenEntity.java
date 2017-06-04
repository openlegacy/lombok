package lombok.transform.openlegacy.delombok;

import lombok.DirectoryRunner;
import org.junit.runner.RunWith;

import java.io.File;

/**
 * @author Matvey Mitnitsky on 24-May-17.
 */
@RunWith(DirectoryRunner.class)
public class OLTestScreenEntity extends DirectoryRunner.TestParams {

    public static final String OL_BEFORE_DIRECTORY = "test/transform/resource/openlegacy/before/screen_entity/general";
    public static final String OL_AFTER_DIRECTORY = "test/transform/resource/openlegacy/after-delombok/screen_entity/general";

    @Override
    public DirectoryRunner.Compiler getCompiler() {
        return DirectoryRunner.Compiler.DELOMBOK;
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
        return new File("test/transform/resource/messages-delombok");
    }

    @Override
    public boolean expectChanges() {
        return true;
    }

}
