package ipProxy;

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
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by NVID on 2017/8/1.
 */
public class VerificateIp implements Runnable{

    private String ip;
    private int port;
    private String targetURL;
    private RequestConfig requestConfig;
    private Map<String, String> headers; //show headers
    private List<Map<String, String>> proxyList;
    public static double ipCount;
    public static double useableIp;
    //Test
    private BlockingQueue ipQueue;
    private static final int DEFAULT_SLEEP_TIME = 1000;

    //Test
    public VerificateIp(BlockingQueue queue, String testURL){
        ipQueue = queue;
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

    public int download(){
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
            requestConfig = RequestConfig.custom().setSocketTimeout(config.timeOut).setConnectionRequestTimeout(config.timeOut)
                    .setConnectTimeout(config.timeOut).setProxy(proxy).build();

            int statusCode = download();
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
                + "\nUser-Agent: " + headers.get("User-Agent")
                + "\nCurrent ip is : " + ip + " || Port is: " + port + " || Useful rate is: "
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

    @Override
    public void run() {
        System.out.println("启动消费者线程！");
        boolean isRunning = true;

        try {
            while (isRunning) {
                System.out.println("正从队列获取数据...");
                Map<String, String> proxy = (Map<String, String>) ipQueue.poll(2, TimeUnit.SECONDS);
                if (null != proxy) {
                    System.out.println("正在测试数据：" + proxy);
                    this.ip = proxy.get("ip");
                    setPort(proxy.get("port"));
                    ipDownloadTest();
                    showResult();
                    //Thread.sleep(DEFAULT_SLEEP_TIME);
                } else {
                    // 超过2s还没数据，认为所有生产线程都已经退出，自动退出消费线程。
                    isRunning = false;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("-----------------------------------------------------------------退出消费者线程！");
        }

    }
}
