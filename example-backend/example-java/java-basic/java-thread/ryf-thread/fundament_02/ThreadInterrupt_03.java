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
    /**
     * 线程间共享变量
     *
     * volatile关键字的目的是告诉虚拟机：
     * 1. 每次访问变量时，总是获取主内存的最新值；
     * 2. 每次修改变量后，立刻回写到主内存。
     * 即当一个线程修改了某个共享变量的值，其他线程能够立刻看到修改后的值
     */
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