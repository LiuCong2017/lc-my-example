package j_concurrent_10;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Concurrent_01 {
}

/**
 * BlockingQueue
 * BlockingQueue的意思就是说，
 * 当一个线程调用这个TaskQueue的getTask()方法时，
 * 该方法内部可能会让线程变成等待状态，
 * 直到队列条件满足不为空，线程被唤醒后，
 * getTask()方法才会返回。
 */
class TaskQueue{
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
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

}