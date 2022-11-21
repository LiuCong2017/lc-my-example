package fundament_02;

public class ThreadInterrupt_03 {
    public static void main(String[] args) throws InterruptedException {
        IThread t = new IThread();
        t.start();
        Thread.sleep(1);
        t.running = false; //设置标志位
    }
}

class IThread extends Thread{
    //线程间共享变量
    public volatile boolean running = true;
    public void run(){
       int n = 0 ;
       while (running){
           n++;
           System.out.println(n);
       }
        System.out.println("end");
    }
}