package t_18;

import t_17.MyThread;

public class Run {
    public static void main(String[] args) {
        System.out.println("main begin priority="+Thread.currentThread().getPriority());
        Thread.currentThread().setPriority(6);
        System.out.println("main end priority="+Thread.currentThread().getPriority());
        MyThread1 thread1 = new MyThread1();
        thread1.start();
    }
}
