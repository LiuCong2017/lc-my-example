package g_readwritelock_07;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock适用于数据读多写少场景
 */
public class ReadWriteLock_02 {
}

/**
 * 实际上我们想要的是：允许多个线程同时读，但只要有一个线程在写，其他线程就必须等待：
 *
 * 使用ReadWriteLock可以解决这个问题，它保证：
 * 只允许一个线程写入（其他线程既不能写入也不能读取）；
 * 没有写入时，多个线程允许同时读（提高性能）。
 */
class Counter2{
    /**
     * 创建一个ReadWriteLock实例，然后分别获取读锁和写锁：
     */
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    private int[] counts = new int[10];

    /**
     * ReadWriteLock只允许一个线程写入；
     */
    public void inc(int index){
        writeLock.lock(); // 加写锁
        try {
            counts[index] += 1;
        } finally {
            writeLock.unlock(); // 释放写锁
        }
    }

    /**
     * 把读写操作分别用读锁和写锁来加锁，在读取时，多个线程可以同时获得读锁，这样就大大提高了并发读的执行效率。
     * 使用ReadWriteLock时，适用条件是同一个数据，有大量线程读取，但仅有少数线程修改。
     * 例如，一个论坛的帖子，回复可以看做写入操作，它是不频繁的，但是，浏览可以看做读取操作，是非常频繁的，这种情况就可以使用ReadWriteLock。
     */
    public int[] get(){
        /**
         * ReadWriteLock允许多个线程在没有写入时同时读取；
         * 潜在的问题：如果有线程正在读，写线程需要等待读线程释放锁后才能获取写锁，即读的过程中不允许写，这是一种悲观的读锁
         */
        readLock.lock(); // 加读锁
        try {
            return Arrays.copyOf(counts,counts.length);
        } finally {
            readLock.unlock(); // 释放读锁
        }
    }

}