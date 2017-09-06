package webspider;

import java.util.List;

/**
 * Created by Administrator on 2017/9/6.
 */
public abstract class WebSpider {

    public abstract String getDomainName(); //获取域名
    public abstract String downloadPage();  //下载页面
    public abstract List parsePage();   //解析页面
    public abstract void run(); //运行爬虫
}
