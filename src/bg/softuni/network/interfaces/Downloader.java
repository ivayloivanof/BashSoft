package bg.softuni.network.interfaces;

/**
 * Created by ivanof on 7/14/16.
 */
public interface Downloader extends AsynchDownloader {

    void download(String fileUrl);
}
