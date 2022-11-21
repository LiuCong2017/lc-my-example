package fundament_01;

public class TestMain {
    public static void main(String[] args) {
//        Thread t1 = new Thread(new CreateNewThread());
//        t1.start();

        Thread t2 = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+" run...");
            Thread t1 = new Thread(new CreateNewThread());
            t1.start();
        });
        t2.start();
    }

}
