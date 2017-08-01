package renren66;

import org.apache.http.client.config.RequestConfig;

import webspider.downloadpage.DownloadPage;
import webspider.linksqueue.LinksQueue;

public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        LinksQueue rootQueue = new LinksQueue();
        LinksQueue movieQueue = new LinksQueue();
        rootQueue.addUnvisitedUrl(RootUrl.hotMoviesUrl); // url add to queue
        ParsePage parsePage;

        Config config = new Config();
        RequestConfig requestConfigTimeout = RequestConfig.custom().setCircularRedirectsAllowed(config.redirecAllowed)
                .setSocketTimeout(config.socketTimeout).setConnectTimeout(config.connectTimeout).build();

        while (!rootQueue.unvisitedUrlEmpty()) {

            try {
                Thread.currentThread();
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String pageContent = null;
            String currentUrl = rootQueue.getUnvisitedUrl();
            DownloadPage downloadPage = new DownloadPage(currentUrl, config.userAgent, requestConfigTimeout);

            pageContent = downloadPage.DownloadByGetMethod(); // download movie-item page

            if (pageContent != null) {
                parsePage = new ParsePage(config.rootDomain, movieQueue, currentUrl, rootQueue);
                parsePage.getMoviesUrl(pageContent);
                rootQueue.addVisitedUrl(currentUrl);

                while (!movieQueue.unvisitedUrlEmpty()) {

                    try {
                        Thread.currentThread();
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    String moviePageContent = null;
                    String currentMovieUrl = movieQueue.getUnvisitedUrl();
                    DownloadPage downloadMoviePage = new DownloadPage(currentMovieUrl, config.userAgent, requestConfigTimeout);
                    moviePageContent = downloadMoviePage.DownloadByGetMethod(); // download movie page

                    if (moviePageContent != null) {
                        //ParsePage parseMoviePage = new ParsePage(config.rootDomain, movieQueue, currentMovieUrl);
                        parsePage.count++;
                        parsePage.getMoviesInfo(moviePageContent);
                        movieQueue.addVisitedUrl(currentMovieUrl);
                    }
                }

                parsePage.findHtmlUrls(RootUrl.hotMoviesReg);
                System.out.println("\n***************The Counts Of Download Page Is: " + rootQueue.getNumberOfVisitedUrl() + "\n");

            }
        }

    }

}
