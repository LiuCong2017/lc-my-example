package F_停止线程_07.not_recommend.suspend_resume;

public class Run {
    public static void main(String[] args) throws InterruptedException {
        try {
            MyThread thread = new MyThread();
            thread.start();
            Thread.sleep(5000);
            //A
            thread.suspend();
            System.out.println("A="+System.currentTimeMillis()+" i="+thread.getI());
            Thread.sleep(5000);
            System.out.println("A="+System.currentTimeMillis()+" i="+thread.getI());
            //B
            thread.resume();
            Thread.sleep(5000);
            //C
            thread.suspend();
            System.out.println("B="+System.currentTimeMillis()+" i="+thread.getI());
            Thread.sleep(5000);
            System.out.println("B="+System.currentTimeMillis()+" i="+thread.getI());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
