package q_virtualthread_17;

public class VirtualThread_01 {
    public static void main(String[] args) {
        Thread vt = Thread.startVirtualThread(()->{
            System.out.println("Start virtual thread...");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("End virtual thread.");
        });

    }
}
