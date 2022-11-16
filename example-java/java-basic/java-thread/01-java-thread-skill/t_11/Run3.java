package t_11;

public class Run3 {
    public static void main(String[] args) {
        try {
            MyThread thread = new MyThread();
            thread.start();
            Thread.sleep(1000);
            thread.interrupt();
            System.out.println("is interrupt1? = "+thread.isInterrupted());
            /**
             * isInterrupted just do judgment but not erase the status flag
             */
            System.out.println("is interrupt2? = "+thread.isInterrupted());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end!");
    }
}
