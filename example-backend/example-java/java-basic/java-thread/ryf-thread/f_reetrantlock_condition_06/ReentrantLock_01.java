package f_reetrantlock_condition_06;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock_01 {
}

//class Counter{
//    private int count;
//    public void add(int n){
//        synchronized (this){
//            count += n;
//        }
//    }
//}

//用ReentrantLock替代synchronized
class Counter{
    private final Lock lock = new ReentrantLock();
    private int count;

    public void add(int n){
        /**
         * 必须先获取到锁，再进入try {...}代码块，最后使用finally保证释放锁
         */
        lock.lock();
        try {
            count += n;
        } finally {
            lock.unlock();
        }
    }

    public void getLock() throws InterruptedException {
        /**
         * 顾名思义，ReentrantLock是可重入锁，
         * 它和synchronized一样，一个线程可以多次获取同一个锁。
         * 和synchronized不同的是，ReentrantLock可以尝试获取锁：
         */
        if (lock.tryLock(1, TimeUnit.SECONDS)){
            try {
                /**
                 * 在尝试获取锁的时候，最多等待1秒。
                 * 如果1秒后仍未获取到锁，tryLock()返回false，程序就可以做一些额外处理，而不是无限等待下去。
                 * 所以，使用ReentrantLock比直接使用synchronized更安全，
                 * 线程在tryLock()失败的时候不会导致死锁。
                 */
            }finally {
                lock.unlock();
            }
        }
    }


}