package a_create_thread_01;

public class CreateNewThread implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" running...");
    }
}
