package t2;

public class MyRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("running start!");
        System.out.println(Thread.currentThread().getName()+":"+Thread.currentThread().getId());
    }
}
