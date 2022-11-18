package t2;

public class Test2 {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable());
        thread.start();
        System.out.println("running done!");
    }
}
