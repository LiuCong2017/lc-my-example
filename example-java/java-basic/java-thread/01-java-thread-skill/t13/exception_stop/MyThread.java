package t13.exception_stop;

public class MyThread  extends Thread{
    @Override
    public void run(){
        super.run();
        try {
            for (int i=0;i<500000;i++){
                if (this.interrupted()){
                    System.out.println("Already stop!Exit");
                    /**
                     * Recommended for interrupt thread,
                     * so you can do business in catch block
                     */
                    throw new InterruptedException();
                }
                System.out.println("i="+(i+i));
            }
            System.out.println("I'm under the for method");
        } catch (InterruptedException e) {
            System.out.println("Exception for MyThread run method");
            e.printStackTrace();
        }
    }
}
