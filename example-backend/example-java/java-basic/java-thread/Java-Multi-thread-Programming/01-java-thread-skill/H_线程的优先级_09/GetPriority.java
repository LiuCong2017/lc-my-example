package H_线程的优先级_09;

public class GetPriority{}

class Run {
    public static void main(String[] args) {
        System.out.println("main begin priority="+Thread.currentThread().getPriority());
        Thread.currentThread().setPriority(6);
        System.out.println("main end priority="+Thread.currentThread().getPriority());
        MyThread1 thread1 = new MyThread1();
        thread1.start();
    }
}

class MyThread1 extends Thread{
    @Override
    public void run(){
        System.out.println("MyThread1 priority="+this.getPriority());
        MyThread2 thread2 = new MyThread2();
        thread2.start();
    }
}

class MyThread2 extends Thread{
    @Override
    public void run(){
        System.out.println("MyThread2 priority="+this.getPriority());
    }
}