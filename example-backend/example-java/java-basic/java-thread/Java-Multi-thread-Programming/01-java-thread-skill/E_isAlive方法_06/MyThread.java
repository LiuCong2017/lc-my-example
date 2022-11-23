package E_isAlive方法_06;

class Run {
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread= new MyThread();
        System.out.println("begin:"+ myThread.isAlive());
        myThread.start();
        Thread.sleep(1000);
        System.out.println("end:"+myThread.isAlive());
    }
}

public class MyThread extends Thread{
    @Override
    public void run(){
        System.out.println("run="+this.isAlive());
    }
}
