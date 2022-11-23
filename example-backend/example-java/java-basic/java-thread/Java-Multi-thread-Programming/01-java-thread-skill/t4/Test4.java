package t4;

public class Test4 {
    public static void main(String[] args) {
        SharedThread sharedThread = new SharedThread();
        Thread a = new Thread(sharedThread,"A");
        Thread b = new Thread(sharedThread,"B");
        Thread c = new Thread(sharedThread,"C");
        Thread d = new Thread(sharedThread,"D");
        Thread e = new Thread(sharedThread,"E");
        a.start();
        b.start();
        c.start();
        d.start();
        e.start();
    }
}
