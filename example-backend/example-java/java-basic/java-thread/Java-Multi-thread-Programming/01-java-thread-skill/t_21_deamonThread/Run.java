package t_21_deamonThread;

public class Run {
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
