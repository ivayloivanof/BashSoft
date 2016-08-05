package bg.softuni.io.interfaces;

import java.io.IOException;

/**
 * Created by ivanof on 7/14/16.
 */
public interface DirectoryChanger {

    void changeCurrentDirRelativePath(String relativePath) throws IOException;

    void changeCurrentDirAbsolutePath(String absolutePath);
}
