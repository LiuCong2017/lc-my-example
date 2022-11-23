package B_实现Runnable接口_03;

class Test2 {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable());
        thread.start();
        System.out.println("running done!");
    }
}

public class MyRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("running start!");
        System.out.println(Thread.currentThread().getName()+":"+Thread.currentThread().getId());
    }
}
