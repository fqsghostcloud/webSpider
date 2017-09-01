package webspider;

import webspider.config.Config;
import webspider.downloadpage.DownloadPage;
import webspider.iptest.IpTest;
import java.util.List;
import java.util.Map;




public abstract class WebSpider{
    protected final String requestUrl;
    protected String pageString;
    protected List<Map<String, String>> proxyList;
    protected Config config;

    public WebSpider(Config config, String requestUrl){
        this.config = config;
        this.requestUrl = requestUrl;
    }

    public String getDomainName(){
        return requestUrl.split("/")[2];
    }

    public String downloadPage(){
        DownloadPage downloadPage = new DownloadPage(requestUrl, config.headers, config.requestConfig);
        return  downloadPage.DownloadByGetMethod();
    }

    public abstract List parseIp();

    public void ipTest(){
        if(proxyList == null){
            System.out.println("**** Proxy List is Null! ****");
        }
        IpTest ipTest = new IpTest(proxyList);
        ipTest.ipListDownloadTest();
        ipTest.showResult();
    }

    public void run(){
        pageString = downloadPage();
        if (pageString != null) {
            proxyList = parseIp();
            ipTest();
        } else {
            System.out.println("\n--------------------------------------------"
                    + "Page String from" + getDomainName() + "is Null");
        }
    }
}
