package webspider.iptest;

import ipProxy.Config;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import webspider.downloadpage.DownloadPage;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by NVID on 2017/8/1.
 */
public class IpTest {

    private String ip;
    private int port;
    private String testUrl = "http://www.renren66.com/";
    //    private  String testUrl = "http://www.baidu.com";
    private List<Map<String, String>> proxyList;
    public static double ipCount;
    public static double useableIp;

    public IpTest(String ip, String port) {
        this.ip = ip;
        setPort(port);
    }

    public IpTest(List<Map<String, String>> proxyList) {
        this.proxyList = proxyList;

    }


    public void setPort(String port) {
        try {
            this.port = Integer.valueOf(port).intValue();
        } catch (NumberFormatException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public void ipDownloadTest() {
//        boolean status = false;
        ipCount++;
        //爬取的Ip或者Port可能格式错误
        try {
            HttpHost proxy = new HttpHost(ip, port);
            Config config = new Config();
            RequestConfig requestConfigTimeout = RequestConfig.custom().setCircularRedirectsAllowed(config.redirecAllowed)
                    .setConnectTimeout(config.connectTimeout).setProxy(proxy).build();

            DownloadPage downloadPage = new DownloadPage(testUrl, config.headers, requestConfigTimeout);
            downloadPage.DownloadByGetMethod();
            if (downloadPage.statusCode == HttpStatus.SC_OK) {
                useableIp++;
            }
            printInfo();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void ipListDownloadTest(){
        for (Map<String, String> proxy : proxyList) {
            this.ip = proxy.get("ip");
            setPort(proxy.get("port"));
            ipDownloadTest();
        }
    }


    public void printInfo() {
        DecimalFormat df = new DecimalFormat("#.0");
        String rate = df.format(useableIp / ipCount * 100);
        System.out.println("\n\n**********************************************************************\n"
                + "Current ip is : " + ip + " || Port is: " + port + " || Useful rate is: "
                + rate + "%" + " || Useable Sum is: " + useableIp
                + " || Sum is: " + ipCount
                + "\n**********************************************************************\n\n");
    }

    public void showResult() {
        DecimalFormat df = new DecimalFormat("#.0");
        String rate = df.format(useableIp / ipCount * 100);
        System.out.println("\n\n**********************************************************************\n"
                + " || Useful rate is: " + rate
                + "%" + "  || Useable Sum is: " + useableIp + " || Sum is: " + ipCount
                + "\n**********************************************************************\n\n");
    }
}
