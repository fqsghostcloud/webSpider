package ipProxy.xicidaili;

import org.apache.http.client.config.RequestConfig;

import ipProxy.Config;
import webspider.downloadpage.DownloadPage;
import webspider.iptest.IpTest;
import webspider.parsepage.ParseIp;

import java.util.List;
import java.util.Map;

/*西刺代理爬取首页代理即可*/
public class XiCiDaiLiSpiser {
    private String[] ipStrings;
    private String url;
    private String htmlContent;
    private List<Map<String, String>> proxyList;

    public XiCiDaiLiSpiser(String rootDomain) {
        this.url = rootDomain;
    }

    public void main() {

        Config config = new Config();
        RequestConfig requestConfigTimeout = RequestConfig.custom().setCircularRedirectsAllowed(config.redirecAllowed)
                .setSocketTimeout(config.socketTimeout).setConnectTimeout(config.connectTimeout).build();


        DownloadPage downloadPage = new DownloadPage(url, config.headers, requestConfigTimeout);
        htmlContent = downloadPage.DownloadByGetMethod();
        System.out.println("\n**Current Url is: " + url);

        ParseIp parseIp = new ParseIp(htmlContent);
        proxyList = parseIp.getXiCiIpProxy();

        //测试Ip
        IpTest ipTest = new IpTest(proxyList);


    }

}
