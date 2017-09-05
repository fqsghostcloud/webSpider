package webspider;

import java.util.List;
import java.util.Map;




public abstract class WebSpider implements Runnable{
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

    //验证Ip是否可用
    public void verificateIp(){
        if(proxyList == null){
            System.out.println("**** Proxy List is Null! ****");
            return;
        }
        VerificateIp verification = new VerificateIp(proxyList, Config.IQIYI);
        verification.ipListDownloadTest();
        verification.showResult();
    }

    @Override
    public void run(){
        System.out.println("开始运行爬虫! request: " + requestUrl);   //Test
        pageString = downloadPage();
        if (pageString != null) {
            proxyList = parseIp();
            verificateIp();
        } else {
            System.out.println("\n--------------------------------------------"
                    + "Page String from" + getDomainName() + "is Null");
        }
    }
}
