package ipProxy;

import ipProxy.spiders.SSIpSpider;
import ipProxy.spiders.XiCiDaiLiSpider;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Administrator on 2017/9/1.
 */
public class Main {

    public VerificateIp[] consumer(int num, BlockingQueue queue, String testUrl){
        VerificateIp[] consumerArray = new VerificateIp[num];
        for(int i = 0; i<num; i++){
            consumerArray[i] = new VerificateIp(queue, testUrl);
        }
        return consumerArray;
    }

    public static void main(String[] args) throws InterruptedException{
        BlockingQueue ipQueue = new LinkedBlockingDeque(50);

        IpSpider spider1 = new SSIpSpider(Config.SixSixIpURL,Config.SixSixIpCnt,ipQueue);
        IpSpider spider2 = new XiCiDaiLiSpider(Config.XiCiDaiLiURL, ipQueue);

        Main main = new Main();
        VerificateIp[] consumers = main.consumer(14, ipQueue, Config.RENRNE66);

        ExecutorService service = Executors.newCachedThreadPool();
        //启动生产者
        service.execute(spider1);
//        service.execute(spider2);

        //启动消费者
        for(VerificateIp comsumer: consumers){
            service.execute(comsumer);
        }

        System.out.println("---------------------------------------------------------------程序即将停止");
        service.shutdown();
        System.out.println("---------------------------------------------------------------程序停止！！！");

    }
}
