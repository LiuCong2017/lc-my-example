package F_停止线程_07.not_recommend.suspend_resume_nosameValue;

public class Run {

    public static void main(String[] args) throws InterruptedException {
        final MyObject myObject = new MyObject();
        Thread thread1 = new Thread(){
            public void run(){
                myObject.setValue("a","aa");
            }
        };
        thread1.setName("a");
        thread1.start();

        Thread.sleep(500);

        Thread thread2 = new Thread(){
            public void run(){
                myObject.printUserPwd();
            }
        };
        thread2.start();
    }

}
