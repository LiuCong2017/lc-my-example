package C_实例变量与线程安全_04;

/**
 * 非线程安全主要指多个线程对同一个对象中的同一个实例变量进行操作时会出现值被更改,值不同步的情况
 */
class Test4 {
    public static void main(String[] args) {
        /**
         * 多个线程共享数据,可以访问同一个变量
         */
        SharedThread sharedThread = new SharedThread();
        Thread a = new Thread(sharedThread,"A");
        Thread b = new Thread(sharedThread,"B");
        Thread c = new Thread(sharedThread,"C");
        Thread d = new Thread(sharedThread,"D");
        Thread e = new Thread(sharedThread,"E");
        a.start();
        b.start();
        c.start();
        d.start();
        e.start();
    }
}

public class SharedThread extends Thread{
    private int count = 5;
    @Override
    //通过synchronized加锁使多个线程之间同步
//    synchronized public void run(){
    public void run(){
        super.run();
        count--;
        System.out.println(this.currentThread().getName()+": count="+count);
    }

}
