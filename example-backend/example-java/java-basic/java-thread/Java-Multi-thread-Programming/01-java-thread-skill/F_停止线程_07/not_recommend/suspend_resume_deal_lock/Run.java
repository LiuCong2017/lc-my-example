package F_停止线程_07.not_recommend.suspend_resume_deal_lock;

public class Run {
    public static void main(String[] args) {
        try {
            final SynchronizedObject object = new SynchronizedObject();

            Thread thread1 = new Thread(){
                @Override
                public void run(){
                    object.printString();
                }
            };
            thread1.setName("a");
            thread1.start();
            Thread.sleep(1000);

            Thread thread2 = new Thread(){
                @Override
                public void run(){
                    System.out.println("thread2启动了，但无法进入printString()方法");
                    System.out.println("因为printString()方法被a线程锁定并永远suspend暂停了");
                    object.printString();
                }
            };
            thread2.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
