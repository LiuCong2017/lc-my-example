package t_18;

public class MyThread1 extends Thread{
    @Override
    public void run(){
        System.out.println("MyThread1 priority="+this.getPriority());
        MyThread2 thread2 = new MyThread2();
        thread2.start();
    }
}
