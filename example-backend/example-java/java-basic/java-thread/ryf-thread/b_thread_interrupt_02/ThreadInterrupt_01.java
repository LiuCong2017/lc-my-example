package b_thread_interrupt_02;

public class ThreadInterrupt_01 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("start");
        Thread t = new Thread(()->{
            int n=0;
            while (!Thread.currentThread().isInterrupted()){
                n++;
                System.out.println(Thread.currentThread().getName()+"-"+n+" running...");
            }
        });
        t.start();
        Thread.sleep(1); //暂停1毫秒
        t.interrupt(); //中断t线程
        t.join(); //等待t线程结束
        System.out.println("end");
    }
}
