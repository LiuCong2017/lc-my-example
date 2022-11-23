package F_停止线程_07;

public class StopThread_01  extends Thread{
    @Override
    public void run(){
        super.run();
        for (int i=0;i<50;i++){
            System.out.println("i="+(i+i));
        }
    }
}

class Run1 {
    public static void main(String[] args) {
        try {
            StopThread_01 thread = new StopThread_01();
            thread.start();
            System.out.println(thread.getId());
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

class Run2 {
    public static void main(String[] args) {
        Thread.currentThread().interrupt();
        System.out.println("is interrupt1? = "+Thread.interrupted());
        /**
         * if invoke twice, the second invoke will return false,
         * because the first invoke has erased the status flag into false
         */
        System.out.println("is interrupt2? = "+Thread.interrupted());
        System.out.println("end!");
    }
}

class Run3 {
    public static void main(String[] args) {
        try {
            StopThread_01 thread = new StopThread_01();
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

