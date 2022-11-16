package t_13.stop_in_sleep;

public class Run {
    public static void main(String[] args) {
        try {
            MyThread thread = new MyThread();
            thread.start();
            Thread.sleep(200);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }
}
