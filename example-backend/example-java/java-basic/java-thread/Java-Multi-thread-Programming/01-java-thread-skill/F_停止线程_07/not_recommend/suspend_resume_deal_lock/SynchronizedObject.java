package F_停止线程_07.not_recommend.suspend_resume_deal_lock;

/**
 * suspend与resume方法的缺点 - 独占
 */
public class SynchronizedObject {
    synchronized public void printString(){
        System.out.println("begin");
        if (Thread.currentThread().getName().equals("a")){
            System.out.println("a线程永远suspend了！");
            Thread.currentThread().suspend();
        }
        System.out.println("end");
    }
}
