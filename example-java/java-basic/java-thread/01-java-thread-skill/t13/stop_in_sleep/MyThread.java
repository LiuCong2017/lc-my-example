package t13.stop_in_sleep;

public class MyThread  extends Thread{
    @Override
    public void run(){
        super.run();
        try {
            System.out.println("run begin");
            Thread.sleep(200000);
            System.out.println("run end");
        } catch (InterruptedException e) {
            System.out.println("Exception for MyThread run method: "+this.isInterrupted());
            e.printStackTrace();
        }
    }
}
