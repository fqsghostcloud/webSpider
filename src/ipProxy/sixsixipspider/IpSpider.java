package ipProxy.sixsixipspider;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.config.RequestConfig;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import ipProxy.Config;
import ipProxy.Main;
import webspider.downloadpage.DownloadPage;
import webspider.iptest.IpTest;
import webspider.parsepage.ParseIp;

public class IpSpider {
    private List<Map<String, String>> proxyList;
    private int ipSum;  //设置请求IP数量
    private String Url = "http://www.66ip.cn/nmtq.php?isp=0&anonymoustype=4&start=&ports=&export=&ipaddress=&area=0&proxytype=2&api=66ip&getnum=";

    public IpSpider(int ipSum) {
        this.ipSum = ipSum;

    }

    public void main() {
        Config config = new Config();
        RequestConfig requestConfigTimeout = RequestConfig.custom().setCircularRedirectsAllowed(config.redirecAllowed)
                .setConnectionRequestTimeout(config.connectTimeout).setConnectTimeout(config.connectTimeout).build();
        String requestUrl = Url + ipSum;

        DownloadPage downloadPage = new DownloadPage(requestUrl, config.headers, requestConfigTimeout);

        String pageString = downloadPage.DownloadByGetMethod();

        if (pageString != null) {
            ParseIp parseIp = new ParseIp();
            proxyList = parseIp.getSixSixIpProxy(pageString, ipSum);

            //测试IP
            IpTest ipTest = new IpTest(proxyList);
        } else {
            System.out.println("\n--------------------------------------------"
                    + "Page String from 66ip.com is Null");
        }
    }


}
