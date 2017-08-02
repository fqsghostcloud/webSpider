package webspider.iptest;

import ipProxy.Config;
import ipProxy.Main;
import org.apache.http.HttpHost;
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


    public IpTest(String ip, String port) {
        this.ip = ip;
        setPort(port);
        ipDownloadTest();
    }

    public IpTest(List<Map<String, String>> proxyList) {
        this.proxyList = proxyList;
        for (Map<String, String> proxy : proxyList) {
            this.ip = proxy.get("ip");
            setPort(proxy.get("port"));
            ipDownloadTest();
        }
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
        Main.SumOfIp++;
        HttpHost proxy = new HttpHost(ip, port);
        printInfo();
        Config config = new Config();
        RequestConfig requestConfigTimeout = RequestConfig.custom().setCircularRedirectsAllowed(config.redirecAllowed)
                .setSocketTimeout(config.socketTimeout).setConnectTimeout(config.connectTimeout).setProxy(proxy).build();

        DownloadPage downloadPage = new DownloadPage(testUrl, config.userAgent, requestConfigTimeout);

        if (downloadPage.DownloadByGetMethod() != null) {
//            status = true;
            Main.usebleSumOfIp++;
        }
//        return status;
    }


    public void printInfo() {
        DecimalFormat df = new DecimalFormat("#.0");
        String rate = df.format(Main.usebleSumOfIp / Main.SumOfIp * 100);
        System.out.println("\n\n**********************************************************************\n"
                + "Current ip is : " + ip + " || Port is: " + port + " || Useful rate is: "
                + rate + "%" + " || Useable Sum is: " + Main.usebleSumOfIp
                + " || Sum is: " + Main.SumOfIp
                + "\n**********************************************************************\n\n");
    }

    public static void printresult() {
        DecimalFormat df = new DecimalFormat("#.0");
        String rate = df.format(Main.usebleSumOfIp / Main.SumOfIp * 100);
        System.out.println("\n\n**********************************************************************\n"
                + " || Useful rate is: " + rate
                + "%" + "  || Useable Sum is: " + Main.usebleSumOfIp + " || Sum is: " + Main.SumOfIp
                + "\n**********************************************************************\n\n");
    }
}
