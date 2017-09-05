package webspider;

import ipProxy.Config;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by NVID on 2017/8/1.
 */
public class VerificateIp {

    private String ip;
    private int port;
    private String targetURL;
    private RequestConfig requestConfig;

    private Map<String, String> headers; //show headers

    private List<Map<String, String>> proxyList;
    public static double ipCount;
    public static double useableIp;


    public VerificateIp(List<Map<String, String>> proxyList, String testURL) {
        this.proxyList = proxyList;
        targetURL = testURL;

    }


    public void setPort(String port) {
        try {
            this.port = Integer.valueOf(port).intValue();
        } catch (NumberFormatException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public int download(RequestConfig requestConfig){
        int statusCode = 0;
        HttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(targetURL);
        request.setConfig(requestConfig);

        //设置http header
        if(headers!=null && headers.size()>0){
            for(String key: headers.keySet()){
                request.addHeader(key, headers.get(key));
            }
        }

        try{
            response = httpClient.execute(request);
            statusCode = response.getStatusLine().getStatusCode();
        } catch (Exception e){
            System.out.println("Ip test Error: " + e);
        }finally {
            request.releaseConnection();
        }
        return statusCode;
    }

    public void ipDownloadTest() {
        System.out.println("\nIP Download Test....");
        ipCount++;
        //爬取的Ip或者Port可能格式错误
        try {
            HttpHost proxy = new HttpHost(ip, port);
            Config config = new Config();
            headers = config.headers;
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(config.timeOut).setConnectionRequestTimeout(config.timeOut)
                    .setConnectTimeout(config.timeOut).setProxy(proxy).build();

            int statusCode = download(requestConfig);
            if (statusCode == HttpStatus.SC_OK) {
                useableIp++;
            }else {
                System.out.println("Ip:" + ip + " Port:" + port + " statusCode: " + statusCode);
            }
            printInfo();
        } catch (Exception e) {
            System.out.println("Ip Test Error: " + e);
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
                + "Target URL: " + targetURL
                + "User-Agent: " + headers.get("User-Agent")
                + "Current ip is : " + ip + " || Port is: " + port + " || Useful rate is: "
                + rate + "%" + " || Useable Sum is: " + useableIp + " || Sum is: " + ipCount
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
