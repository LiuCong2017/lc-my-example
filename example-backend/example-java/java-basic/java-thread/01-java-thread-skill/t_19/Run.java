package t_19;


/**
 * 值越大，优先级越高，但线程优先级与执行顺序无关，不过优先级高的线程可能运行耗时小
 */
public class Run {
    public static void main(String[] args) {
        for (int i=0;i<5;i++){
            MyThread1 thread1 = new MyThread1();
//            thread1.setPriority(10);
            thread1.setPriority(1);
            thread1.start();

            MyThread2 thread2 = new MyThread2();
//            thread2.setPriority(1);
            thread2.setPriority(10);
            thread2.start();
        }
    }
}
