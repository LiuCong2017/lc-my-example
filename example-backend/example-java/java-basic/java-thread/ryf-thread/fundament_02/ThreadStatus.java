package fundament_02;

/**
 * Java线程的状态有以下几种：
 *
 * New：新创建的线程，尚未执行；
 * Runnable：运行中的线程，正在执行run()方法的Java代码；
 * Blocked：运行中的线程，因为某些操作被阻塞而挂起；
 * Waiting：运行中的线程，因为某些操作在等待中；
 * Timed Waiting：运行中的线程，因为执行sleep()方法正在计时等待；
 * Terminated：线程已终止，因为run()方法执行完毕。
 */
public class ThreadStatus {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()->{
            System.out.println("running...");
        });
        System.out.println("start...");
        t.start();
        t.join(); //使main线程等待t线程结束后再继续运行
//        t.join(10); //使main线程等待t线程结束后再继续运行
        System.out.println("end");
    }
}
