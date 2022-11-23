package F_停止线程_07.recommend.useReturnInterrupt;

public class MyThread  extends Thread{
    private int i =0;

    @Override
    public void run(){
        while (true){
            if (this.isInterrupted()){
                System.out.println("stopped!");
                return;
            }
            System.out.println("timer="+System.currentTimeMillis());
        }
    }
}
