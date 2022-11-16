package t6;

public class MyThread extends Thread{
    public MyThread(){
        System.out.println("constructor: "+ Thread.currentThread().getName());
    }
    @Override
    public void run(){
        System.out.println("run: "+Thread.currentThread().getName());
    }
}
