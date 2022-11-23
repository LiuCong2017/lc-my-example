package D_currentThread方法_05;

class Run {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}

public class MyThread extends Thread{
    public MyThread(){
        System.out.println("constructor: "+ Thread.currentThread().getName());
    }
    @Override
    public void run(){
        System.out.println("run: "+Thread.currentThread().getName());
    }
}
