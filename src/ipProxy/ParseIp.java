package ipProxy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
    private List<Map<String, String>> proxyList = new LinkedList<>();
    public ParseIp(){

    }

    //解析西刺代理
    public List getXiCiIpProxy(String htmlContent) {
        htmlDoc = Jsoup.parse(htmlContent);
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

    //解析66ip
    public List getSixSixIpProxy(String htmlContent ,int ipSum){
        htmlDoc = Jsoup.parse(htmlContent);
        Elements items = htmlDoc.getElementsByTag("body");
        if(items != null){
            for(Element item: items){
                for(int i = 0; i<2*ipSum; i++){
                    Map<String, String> proxy = new HashMap<>();
                    if(i%2==0){
                        String[] proxyString = item.childNode(i).toString().trim().split(":");  //去空格
                        try{
                            proxy.put("ip",proxyString[0]);
                            proxy.put("port",proxyString[1]);
                            proxyList.add(proxy);
                        }catch (ArrayIndexOutOfBoundsException e){
                            e.printStackTrace();
                            System.out.println("** Current ip:" + item.childNode(i).toString());
                        }
                    }
                }
            }
        }else {
            System.out.println("** Parse SixSixProxy Fild! body tag is Null");
        }
        return proxyList;
    }
}
