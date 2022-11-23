package p_threadlocal_16;

/**
 * ThreadLocal表示线程的“局部变量”，它确保每个线程的ThreadLocal变量都是各自独立的；
 * ThreadLocal适合在一个线程的处理流程中保持上下文（避免了同一参数在所有方法中传递）；
 * 使用ThreadLocal要用try ... finally结构，并在finally中清除。
 */
public class ThreadLocal_01 {
    public static void main(String[] args) throws Exception {
        log("start main...");
        new Thread(() -> {
            log("run task...");
        }).start();
        new Thread(() -> {
            log("print...");
        }).start();
        log("end main.");
    }

    static void log(String s) {
        ThreadLocal tl = new ThreadLocal<>();

        System.out.println(Thread.currentThread().getName() + ": " + s);
    }
}
