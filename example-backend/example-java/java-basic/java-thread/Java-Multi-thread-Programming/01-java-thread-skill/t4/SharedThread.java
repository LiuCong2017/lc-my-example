package t4;

public class SharedThread extends Thread{
    private int count = 5;
    @Override
    synchronized public void run(){
        super.run();
        count--;
        System.out.println(this.currentThread().getName()+": count="+count);
    }

}
