package fundament_02;

public class ThreadStatus {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()->{
            System.out.println("running...");
        });
        System.out.println("start...");
        t.start();
        t.join(); //使main线程等待t线程结束后再继续运行
//        t.join(10); //使main线程等待t线程结束后再继续运行
        System.out.println("end");
    }
}
