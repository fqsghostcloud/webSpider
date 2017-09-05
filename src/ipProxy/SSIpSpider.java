package ipProxy;
import webspider.WebSpider;
import webspider.ParseIp;

import java.util.List;

/**
 * Created by Administrator on 2017/8/31.
 */
public class SSIpSpider extends WebSpider {

    private int ipCnt;  //ip count;
    public SSIpSpider(String requestUrl, int ipCnt){
        super(new Config(), requestUrl);
        this.ipCnt = ipCnt;
    }


    @Override
    public List parseIp() {
        ParseIp parseIp = new ParseIp();
        proxyList = parseIp.getSixSixIpProxy(pageString, ipCnt);
        return proxyList;
    }

    public static void main(String[] args){
        int ipCnt = 5;
        SSIpSpider ssIpSpider = new SSIpSpider("http://www.66ip.cn/nmtq.php?isp=0&anonymoustype=4&start=&ports=&export=&ipaddress=&area=0&proxytype=2&api=66ip&getnum="+ipCnt, ipCnt);
        ssIpSpider.run();
    }
}
