package t_18;

import t_17.MyThread;

public class MyThread1 extends Thread{
    @Override
    public void run(){
        System.out.println("MyThread1 priority="+this.getPriority());
        MyThread2 thread2 = new MyThread2();
        thread2.start();
    }
}
