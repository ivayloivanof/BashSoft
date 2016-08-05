package bg.softuni.network.interfaces;

/**
 * Created by ivanof on 7/14/16.
 */
public interface AsynchDownloader {

    void downloadOnNewThread(String fileUrl);
}
