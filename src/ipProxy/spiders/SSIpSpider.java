package ipProxy.spiders;

import ipProxy.Config;
import ipProxy.IpSpider;
import ipProxy.ParseIp;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Administrator on 2017/8/31.
 */
public class SSIpSpider extends IpSpider {

    private int ipCnt;  //ip count;
    public SSIpSpider(String requestUrl, int ipCnt, BlockingQueue queue){
        super(new Config(), requestUrl, queue);
        this.ipCnt = ipCnt;
    }


    @Override
    public List parsePage() {
        ParseIp parseIp = new ParseIp();
        proxyList = parseIp.getSixSixIpProxy(pageString, ipCnt);
        return proxyList;
    }

}
