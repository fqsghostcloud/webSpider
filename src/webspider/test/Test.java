package webspider.test;

/**
 * Created by Administrator on 2017/9/7.
 */
public class Test {
    public int count = 0;
    int temp = 0;
    boolean isWaiting = true;
    Object lock = new Object();
    //生产者
    public class Product implements Runnable{

        @Override
        public void run() {
            synchronized (lock){
                while(count < 10){
                    count++;
                    System.out.println("开始生产!... Count: " + count);

                    if(count==5){
                        temp = count;
                        isWaiting = false;
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println("仓库以满，停止生产");
                temp = count;
                isWaiting = false;
                lock.notify();
            }

        }
    }

    //消费者
    public class Consumer implements Runnable{

        @Override
        public void run() {
            synchronized (lock){
               while(isWaiting){
                   try {
                       lock.wait();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }

                while(temp > 0){
                    System.out.println("消费者开始消费，Count: " + temp);
                    temp--;
                }

                if(count==5){
                    System.out.println("仓库已经空，停止消费");
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("仓库已经空，停止消费 temp: " + temp);
                while(temp > 0){
                    System.out.println("消费者开始消费，Count: " + temp);
                    temp--;
                }

            }

        }
    }

    public void test(){
        Thread producter = new Thread(new Product());
        producter.setName("producter");
        Thread consumer = new Thread(new Consumer());
        consumer.setName("consumer");
        producter.start();
        consumer.start();
        try {
            producter.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }



    public static void main(String[] args) throws InterruptedException{
        Test test = new Test();
        System.out.println("main线程开始运行！。。。");
        test.test();
        System.out.println("main运行结束！。。。。");
    }
}
