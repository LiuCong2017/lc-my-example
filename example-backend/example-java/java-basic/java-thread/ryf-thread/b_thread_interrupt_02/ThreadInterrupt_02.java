package b_thread_interrupt_02;

public class ThreadInterrupt_02 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new MyThread();
        t.start();
        Thread.sleep(1000);
        t.interrupt();
        t.join();
        System.out.println("end");
    }
}

class MyThread extends Thread{
    public void run(){
        HThread h = new HThread();
        h.start();
        try {
            h.join();
        } catch (InterruptedException e) {
            System.out.println("interrupted");
        }
        h.interrupt();
    }
}

class HThread extends Thread{
    public void run(){
        int n=0;
        while (!isInterrupted()){
            n++;
            System.out.println(n+"running...");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}