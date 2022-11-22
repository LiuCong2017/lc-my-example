package d_dead_lock_04;

/**
 * Java的synchronized锁是可重入锁；
 * 死锁产生的条件是多线程各自持有不同的锁，并互相试图获取对方已持有的锁，导致无限等待；
 * 避免死锁的方法是多线程获取锁的顺序要一致。
 */
public class DeadLock_03 {
    static final Object LOCK_A = new Object();
    static final Object LOCK_B = new Object();

    public static void main(String[] args) {
        new Thread1().start();
        new Thread2().start();
    }

    static void sleep1s(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class Thread1 extends Thread{

    public void run(){
        System.out.println(this.getName() + ": getting LOCK_A...");
        synchronized (DeadLock_03.LOCK_A){
            System.out.println(this.getName()+": got LOCK_A");
            DeadLock_03.sleep1s();
            System.out.println(this.getName()+": getting LOCK_B...");
            synchronized (DeadLock_03.LOCK_B){
                System.out.println(this.getName()+": got LOCK_B");
                DeadLock_03.sleep1s();
            }
            System.out.println(this.getName()+": LOCK_B released");
        }
        System.out.println(this.getName()+": LOCK_A released");
    }

}

class Thread2 extends Thread{

    public void run(){
        System.out.println(this.getName()+": getting LOCK_B...");
        synchronized (DeadLock_03.LOCK_B){
            System.out.println(this.getName()+": got LOCK_B");
            DeadLock_03.sleep1s();
            System.out.println(this.getName()+": getting LOCK_A...");
            synchronized (DeadLock_03.LOCK_A){
                System.out.println(this.getName()+": got LOCK_A");
                DeadLock_03.sleep1s();
            }
            System.out.println(this.getName()+": LOCK_A released");
        }
        System.out.println(this.getName()+": LOCK_B released");
    }

}

