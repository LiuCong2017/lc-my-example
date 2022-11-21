package fundament_07;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReadWriteLock_01 {
}

/**
 * ReentrantLock保证了只有一个线程可以执行临界区代码：
 */
class Counter1 {
    private final Lock lock = new ReentrantLock();
    private int[] counts = new int[10];

    public void inc(int index){
        lock.lock();
        try {
            counts[index] += 1;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 但有时ReentrantLock保护有点过头。
     * 因为任何时刻，只允许一个线程修改，也就是调用inc()方法是必须获取锁，
     * 但是，get()方法只读取数据，不修改数据，它实际上允许多个线程同时调用。
     */
    public int[] get(){
        lock.lock();
        try {
            return Arrays.copyOf(counts,counts.length);
        } finally {
            lock.unlock();
        }
    }


}