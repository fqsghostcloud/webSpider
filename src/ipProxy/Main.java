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
    public static void main(String[] args) throws InterruptedException{
        BlockingQueue ipQueue = new LinkedBlockingDeque(50);

        IpSpider spider1 = new SSIpSpider(Config.SixSixIpURL,Config.SixSixIpCnt,ipQueue);
        IpSpider spider2 = new XiCiDaiLiSpider(Config.XiCiDaiLiURL, ipQueue);

        VerificateIp consumer1 = new VerificateIp(ipQueue, Config.CUIT);
        VerificateIp consumer2 = new VerificateIp(ipQueue, Config.CUIT);
        VerificateIp consumer3 = new VerificateIp(ipQueue, Config.CUIT);
        VerificateIp consumer4 = new VerificateIp(ipQueue, Config.CUIT);

        //TEst
        VerificateIp consumer5 = new VerificateIp(ipQueue, Config.CUIT);
        VerificateIp consumer6 = new VerificateIp(ipQueue, Config.CUIT);
        VerificateIp consumer7 = new VerificateIp(ipQueue, Config.CUIT);
        VerificateIp consumer8 = new VerificateIp(ipQueue, Config.CUIT);
        VerificateIp consumer9 = new VerificateIp(ipQueue, Config.CUIT);
        VerificateIp consumer10 = new VerificateIp(ipQueue, Config.CUIT);
        VerificateIp consumer11 = new VerificateIp(ipQueue, Config.CUIT);
        VerificateIp consumer12 = new VerificateIp(ipQueue, Config.CUIT);
        VerificateIp consumer13 = new VerificateIp(ipQueue, Config.CUIT);
        VerificateIp consumer14 = new VerificateIp(ipQueue, Config.CUIT);



        ExecutorService service = Executors.newCachedThreadPool();
        //启动生产者
        service.execute(spider1);
//        service.execute(spider2);

        //启动消费者
        service.execute(consumer1);
        service.execute(consumer2);
        service.execute(consumer3);
        service.execute(consumer4);
        service.execute(consumer5);
        service.execute(consumer6);
        service.execute(consumer7);
        service.execute(consumer8);
        service.execute(consumer9);
        service.execute(consumer10);
        service.execute(consumer11);
        service.execute(consumer12);
        service.execute(consumer13);
        service.execute(consumer14);




        System.out.println("---------------------------------------------------------------程序即将停止");
        service.shutdown();
        System.out.println("---------------------------------------------------------------程序停止！！！");

    }
}
