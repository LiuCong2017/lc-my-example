package c_thread_sync_03;

/**
 * 使用synchronized：
 *
 * 1. 找出修改共享变量的线程代码块；
 * 2. 选择一个共享实例作为锁；
 * 3. 使用synchronized(lockObject) { ... }。
 */
public class ThreadSync_01 {
    public static void main(String[] args) throws InterruptedException {
        Thread add = new AddThread();
        Thread dec = new DecThread();
        add.start();
        dec.start();
        add.join();
        dec.join();
        System.out.println(Counter1.count);
    }
}

class Counter1{
    public static final Object lock = new Object(); //定义一把锁,用Counter1.lock实例作为锁
    public static int count = 0;
}

class AddThread extends Thread{
    public void run(){
        for (int i=0;i<10000;i++){
            synchronized (Counter1.lock){ //获取锁
                Counter1.count += 1;
            }//释放锁
        }
    }
}

class DecThread extends Thread{
    public void run(){
        for (int i=0;i<10000;i++){
            synchronized (Counter1.lock){
                Counter1.count -= 1;
            }
        }
    }
}