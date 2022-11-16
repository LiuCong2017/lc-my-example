package t_11;

public class Run1 {
    public static void main(String[] args) {
        try {
            MyThread thread = new MyThread();
            thread.start();
            Thread.sleep(1000);
            thread.interrupt();
            System.out.println("is interrupt1? = "+thread.interrupted());
            System.out.println("is interrupt2? = "+thread.interrupted());
        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end!");
    }
}
