package fundament_04;

/**
 * 死锁的产生和解决
 */
public class DeadLock_02 {

    static final Object lockA = new Object();
    static final Object lockB = new Object();

    private int value = 0;
    private int another = 0;

    public void add(int m) {
        synchronized(lockA) { // 获得lockA的锁
            this.value += m;
            synchronized(lockB) { // 获得lockB的锁
                this.another += m;
            } // 释放lockB的锁
        } // 释放lockA的锁
    }

//    public void dec(int m) {
//        synchronized(lockB) { // 获得lockB的锁
//            this.another -= m;
//            synchronized(lockA) { // 获得lockA的锁
//                this.value -= m;
//            } // 释放lockA的锁
//        } // 释放lockB的锁
//    }

    /**
     * 避免死锁:
     * 线程获取锁的顺序要一致。即严格按照先获取lockA，再获取lockB的顺序
     */
    public void dec(int m) {
        synchronized(lockA) { // 获得lockA的锁
            this.value -= m;
            synchronized(lockB) { // 获得lockB的锁
                this.another -= m;
            } // 释放lockB的锁
        } // 释放lockA的锁
    }

}
