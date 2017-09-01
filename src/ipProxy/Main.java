package ipProxy;

/**
 * Created by Administrator on 2017/9/1.
 */
public class Main {
    public static void main(String[] args){
        double startTime = System.currentTimeMillis();

        new SSIpSpider(Config.SixSixIpURL,Config.SixSixIpCnt).run();
//        new XiCiDaiLiSpider(Config.XiCiDaiLiURL).run();

        double endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime-startTime)/60000+ " minutes");
    }
}
