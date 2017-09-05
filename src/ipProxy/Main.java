package ipProxy;

/**
 * Created by Administrator on 2017/9/1.
 */
public class Main {
    public static void main(String[] args){
        double startTime = System.currentTimeMillis();
        new XiCiDaiLiSpider(Config.XiCiDaiLiURL).run();
//        new SSIpSpider(Config.SixSixIpURL,Config.SixSixIpCnt).run();

       /* Thread thread =  new Thread(new SSIpSpider(Config.SixSixIpURL,Config.SixSixIpCnt));
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
//        new Thread(new XiCiDaiLiSpider(Config.XiCiDaiLiURL)).start();

        double endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime-startTime)/60000+ " minutes");
    }
}
