package e_wait_notify_05;

import java.util.LinkedList;
import java.util.Queue;

public class WaitNotify_02 {

}

/**
 * 多线程协调运行的原则是：
 * 当条件不满足时，线程进入等待状态；
 * 当条件满足时，线程被唤醒，继续执行任务。
 */
class TaskQueue2{

    Queue<String> queue =  new LinkedList<>();

    /**
     * 在相同的锁对象上调用notify()方法,
     * 让等待的线程被重新唤醒，然后从wait()方法返回
     */
    public synchronized void addTask(String s){
        this.queue.add(s);
        this.notify(); // 唤醒在this锁等待的线程
    }

    public synchronized String getTask() throws InterruptedException {
        while (queue.isEmpty()){
            /**
             * 必须在synchronized块中才能调用wait()方法，
             * 因为wait()方法调用时，会释放线程获得的锁，
             * wait()方法返回后，线程又会重新试图获得锁。
             * 因此，只能在锁对象上调用wait()方法。
             *
             * 因为在getTask()中，我们获得了this锁，
             * 因此，只能在this对象上调用wait()方法
             * wait()方法必须在当前获取的锁对象上调用，
             * 这里获取的是this锁，因此调用this.wait()
             *
             * 当一个线程在this.wait()等待时，它就会释放this锁，从而使得其他线程能够在addTask()方法获得this锁
             */
            this.wait(); //出让CPU，线程进入阻塞态,被唤醒后进入就绪态预备执行
        }
        return  queue.remove();
    }

}
