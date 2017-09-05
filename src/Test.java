import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/2.
 */
public class Test {
    public static List<String> dic = new LinkedList<>();

    public static void main(String[] args) {
       /* int count = 0;
        while (true){
            count++;
            String url="https://www.lagou.com/jobs/list_Java?px=default&gj=%E5%BA%94%E5%B1%8A%E6%AF%95%E4%B8%9A%E7%94%9F&city=%E5%85%A8%E5%9B%BD#filterBox";
            Config config = new Config();
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(150).setCircularRedirectsAllowed(true)
                    .setConnectionRequestTimeout(150).build();

            DownloadPage downloadPage = new DownloadPage(url,config.headers,requestConfig);
            String html  = downloadPage.DownloadByGetMethod();
            if(html!=null){
                System.out.println("-----------OK");
            }else {
                System.out.println("-----------GG");
            }
            System.out.println("**Count is: "+ count);
        }*/
        System.out.println("Main alive------------------------------------------------------? " + Thread.currentThread().isAlive());
        Test threadTest = new Test();
        threadTest.testThread();

        try {
            Thread.sleep(50);
            Thread.currentThread().interrupt();
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println("Main End--------------------------------------------------------? " + Thread.currentThread().isAlive());

    }

    public void testThread() {
        MyThread myThread = new MyThread();
        myThread.start();
        return;

    }

    public class MyThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println("子进程活着");
            }
        }

        public void start() {
            Thread thread = new Thread(this);
            thread.start();
        }
    }


}
