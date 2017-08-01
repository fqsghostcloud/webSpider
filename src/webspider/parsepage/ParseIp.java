package webspider.parsepage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by NVID on 2017/8/1.
 */
public class ParseIp {
    private Document htmlDoc;
    private String htmlContent;
    private List<Map<String, String>> proxyList = new LinkedList<>();

    public ParseIp(String htmlContent) {
        htmlDoc = Jsoup.parse(htmlContent);
    }

    //解析西刺代理
    public List getXiCiIpProxy() {
        Elements trItems = htmlDoc.getElementsByTag("tr");
        int count = 0;
        int removeIndex = 1;//标记第一次循环获得的tr

        if (trItems != null) {
            for (Element tdItem : trItems) {
                if (removeIndex == 1) {
                    removeIndex = 0;
                } else {
                    Map<String, String> proxy = new HashMap<>();
                    proxy.put("ip", tdItem.child(1).text());
                    proxy.put("port", tdItem.child(2).text());
                    proxyList.add(proxy);
                    count++;
                }
            }
            System.out.println("\n** Test IP Count:" + count);
            return proxyList;
        } else {
            System.out.println("** Parse XiCiDaiLi Faild! tr Tag is Null!");
        }
        return null;
    }
}
