package ipProxy;
import webspider.WebSpider;
import webspider.ParseIp;

import java.util.List;

/**
 * Created by Administrator on 2017/8/31.
 */
public class XiCiDaiLiSpider extends WebSpider{

    public XiCiDaiLiSpider(String requestUrl){
        super(new Config(), requestUrl);
    }

    @Override
    public List parseIp() {
        System.out.println("\nParse ip ...");
        ParseIp parseIp = new ParseIp();
        proxyList = parseIp.getXiCiIpProxy(pageString);
        return proxyList;
    }

    public static void main(String[] args){
        XiCiDaiLiSpider spider = new XiCiDaiLiSpider("http://www.xicidaili.com/nn");
        spider.run();
    }
}
