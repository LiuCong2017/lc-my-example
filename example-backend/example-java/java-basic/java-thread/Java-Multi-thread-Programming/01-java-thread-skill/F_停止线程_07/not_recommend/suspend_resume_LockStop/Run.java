package F_停止线程_07.not_recommend.suspend_resume_LockStop;

public class Run {
    public static void main(String[] args) throws InterruptedException {
        try {
            MyThread thread = new MyThread();
            thread.start();
            Thread.sleep(1000);
            thread.suspend();
            System.out.println("main end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
