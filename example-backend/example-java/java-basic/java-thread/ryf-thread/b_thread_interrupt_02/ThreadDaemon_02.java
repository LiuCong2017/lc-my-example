package b_thread_interrupt_02;

public class ThreadDaemon_02 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread1();
        t.setDaemon(true); //设置为守护线程后，main线程结束，则该线程结束
        t.start();
        System.out.println(Thread.currentThread().getName()+": wait 3 sec...");
        Thread.sleep(3000);
        System.out.println(Thread.currentThread().getName()+": end.");
    }
}

class Thread1 extends Thread{

    public void run(){
        for (;;){
            System.out.println(this.getName()+" running...");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }
    }

}