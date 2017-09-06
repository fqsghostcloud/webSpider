package ipProxy;

import webspider.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;


public abstract class IpSpider extends WebSpider implements Runnable{
    protected final String requestUrl;
    protected String pageString;
    protected List<Map<String, String>> proxyList;
    protected webspider.Config config;
    //Test
    private static final int DEFAULT_SLEEP_TIME = 1000;
    private volatile boolean isRunning = true;
    protected BlockingQueue ipQueue;

    public IpSpider(webspider.Config config, String requestUrl, BlockingQueue queue){
        this.config = config;
        this.requestUrl = requestUrl;
        ipQueue = queue;
    }

    @Override
    public String getDomainName(){
        return requestUrl.split("/")[2];
    }

    @Override
    public String downloadPage(){
        DownloadPage downloadPage = new DownloadPage(requestUrl, config.headers, config.requestConfig);
        return  downloadPage.DownloadByGetMethod();
    }


    @Override
    public void run(){
        System.out.println("开始运行爬虫! request: " + requestUrl);   //Test
        System.out.println("启动生产者线程...");

        try{
            while(isRunning){
                System.out.println("正在生产数据...");
                pageString = downloadPage();
                if (pageString != null) {
                    proxyList = parsePage();
                    System.out.println("将数据放入队列...");
                    for(Map<String, String> proxy: proxyList){
                       ipQueue.put(proxy);
                       System.out.println("加入: " + proxy);
                    }
                } else {
                    System.out.println("\n--------------------------------------------"
                            + "Page String from" + getDomainName() + "is Null");
                }
                isRunning = false; //仅仅运行一次
            }
        } catch (InterruptedException e){
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("-------------------------------------------------退初生产者线程!");
        }
    }
}
