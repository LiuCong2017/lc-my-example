package fundament_05;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * wait和notify用于多线程协调运行：
 * 在synchronized内部可以调用wait()使线程进入等待状态；
 * 必须在已获得的锁对象上调用wait()方法；
 * 在synchronized内部可以调用notify()或notifyAll()唤醒其他等待线程；
 * 必须在已获得的锁对象上调用notify()或notifyAll()方法；
 * 已唤醒的线程还需要重新获得锁后才能继续执行。
 */
public class WaitNotify_03 {

    public static void main(String[] args) throws InterruptedException {
        var q = new TaskQueue();
        var ts = new ArrayList<Thread>();

        for (int i=0;i<5;i++){
            var t = new Thread(()->{
                // 执行task:
                while (true){
                    try {
                        String s = q.getTask();
                        System.out.println("execute task: "+s);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            });
            t.start();
            ts.add(t);
        }

        var add = new Thread(()->{
           for (int i=0;i<10;i++){
               // 放入task:
               String s = "t-"+Math.random();
               System.out.println("add task: "+s);
               q.addTask(s);
               try {Thread.sleep(100);} catch (InterruptedException e) {}
           }
        });

        add.start();
        add.join();
        Thread.sleep(100);

        for (var t:ts){
            t.interrupt();
        }

    }

}

class TaskQueue{
    Queue<String> queue = new LinkedList<>();

    public synchronized void addTask(String s){
        this.queue.add(s);
        /**
         * 使用notifyAll()将唤醒所有当前正在this锁等待的线程，而notify()只会唤醒其中一个（具体哪个依赖操作系统，有一定的随机性）。
         * 这是因为可能有多个线程正在getTask()方法内部的wait()中等待，使用notifyAll()将一次性全部唤醒。
         * 通常来说，notifyAll()更安全。
         * 有些时候，如果我们的代码逻辑考虑不周，用notify()会导致只唤醒了一个线程，而其他线程可能永远等待下去醒不过来了。
         */
        this.notifyAll();;
    }

    public synchronized String getTask() throws InterruptedException {
        /**
         * 注意在while()循环中调用wait()，而不是if语句
         * if语句这种写法实际上是错误的，因为线程被唤醒时，需要再次获取this锁。
         * 多个线程被唤醒后，只有一个线程能获取this锁，
         * 此刻，该线程执行queue.remove()可以获取到队列的元素，
         * 然而，剩下的线程如果获取this锁后执行queue.remove()，此刻队列可能已经没有任何元素了，
         * 所以，要始终在while循环中wait()，
         * 并且每次被唤醒后拿到this锁就必须再次判断：
         */
//        if (queue.isEmpty()) {
//            this.wait();
//        }
        while (queue.isEmpty()){
            this.wait();
        }
        return queue.remove();
    }

}