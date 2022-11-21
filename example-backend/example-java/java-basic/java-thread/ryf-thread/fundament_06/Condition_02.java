package fundament_06;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock使用Condition对象来实现wait和notify的功能。
 */
public class Condition_02 {
}

/**
 * Condition提供的await()、signal()、signalAll()原理和synchronized锁对象的wait()、notify()、notifyAll()是一致的，并且其行为也是一样的：
 * await()会释放当前锁，进入等待状态；
 * signal()会唤醒某个等待线程；
 * signalAll()会唤醒所有等待线程；
 * 唤醒线程从await()返回后需要重新获得锁。
 */
class TaskQueue{
    private final Lock lock = new ReentrantLock();

    /**
     * 使用Condition时，引用的Condition对象必须从Lock实例的newCondition()返回，这样才能获得一个绑定了Lock实例的Condition实例
     */
    private final Condition condition = lock.newCondition(); //Condition对象必须从Lock对象获取
    private Queue<String> queue = new LinkedList<>();

    public void addTask(String s){
        lock.lock();
        try {
            queue.add(s);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public String getTask() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()){
                condition.await();
            }
            return queue.remove();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 此外，和tryLock()类似，await()可以在等待指定时间后，
     * 如果还没有被其他线程通过signal()或signalAll()唤醒，可以自己醒来：
     */
    public void wakeOwn() throws InterruptedException {
        if (condition.await(1, TimeUnit.SECONDS)) {
            // 被其他线程唤醒
        } else {
            // 指定时间内没有被其他线程唤醒
        }
    }

}