package ipProxy.spiders;

import ipProxy.Config;
import ipProxy.IpSpider;
import ipProxy.ParseIp;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Administrator on 2017/8/31.
 */
public class XiCiDaiLiSpider extends IpSpider {

    public XiCiDaiLiSpider(String requestUrl, BlockingQueue queue){
        super(new Config(), requestUrl, queue);
    }

    @Override
    public List parsePage() {
        System.out.println("\nParse ip ...");
        ParseIp parseIp = new ParseIp();
        proxyList = parseIp.getXiCiIpProxy(pageString);
        return proxyList;
    }

}
