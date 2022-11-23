package t3;

public class Test3 {
    public static void main(String[] args) {
        /**
         * 1. We create 3 thread, and every thread have own count
         * 2. So thread variables are not shared
         */
        UnsharedThread a = new UnsharedThread("A");
        UnsharedThread b = new UnsharedThread("B");
        UnsharedThread c = new UnsharedThread("C");
        a.start();
        b.start();
        c.start();
    }
}
