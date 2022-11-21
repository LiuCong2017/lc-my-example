package fundament_05;

import java.util.LinkedList;
import java.util.Queue;

public class WaitNotify_01 {

}

/**
 * 想要的执行效果：
 * 线程1可以调用addTask()不断往队列中添加任务；
 * 线程2可以调用getTask()从队列中获取任务。如果队列为空，则getTask()应该等待，直到队列中至少有一个任务时再返回。
 */
class TaskQueue1{
    Queue<String> queue =  new LinkedList<>();

    public synchronized void addTask(String s){
        this.queue.add(s);
    }

    /**
     * getTask()内部先判断队列是否为空，如果为空，就循环等待，直到另一个线程往队列中放入了一个任务，while()循环退出，就可以返回队列的元素了
     */
    public synchronized String getTask(){
        /**
         * 实际上while()循环永远不会退出。
         * 因为线程在执行while()循环时，
         * 已经在getTask()入口获取了this锁，
         * 其他线程根本无法调用addTask()，
         * 因为addTask()执行条件也是获取this锁
         *
         * 因此，执行该代码，线程会在getTask()中因为死循环而100%占用CPU资源
         */
        while (queue.isEmpty()){
        }
        return queue.remove();
    }

}


