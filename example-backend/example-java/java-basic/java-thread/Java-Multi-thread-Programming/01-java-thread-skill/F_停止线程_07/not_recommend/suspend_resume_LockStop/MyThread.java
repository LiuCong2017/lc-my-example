package F_停止线程_07.not_recommend.suspend_resume_LockStop;

public class MyThread  extends Thread{
    private long i =0;

    @Override
    public void run(){
        while (true){
            i++;
            /**
             * 当程序运行到pringln方法内部暂停时，同步锁未被释放,
             * 导致不能执行System.out.println("main end");
             */
            System.out.println(i);
        }
    }
}
