package I_守护线程_10;

class Run {
    public static void main(String[] args) {
        try {
            MyThread thread = new MyThread();
            thread.setDaemon(true);
            thread.start();
            Thread.sleep(5000);
            System.out.println("when thread end, and daemon will finally quited");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class MyThread extends Thread{
    private int i = 0;
    public void run(){
        try {
            while (true){
                i++;
                System.out.println("i="+i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
