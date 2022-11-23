package pkg;

class Run {
    /**
     * 1) A线程先持有锁，B线程可以异步方式调用非synchronized方法
     * 2) A线程先持有锁，B线程若这时调用synchronized方法则需等待,也就是同步
     */
    public static void main(String[] args) {
        MyObject object = new MyObject();
        ThreadA a = new ThreadA(object);
        a.setName("A");

        ThreadB b = new ThreadB(object);
        b.setName("B");

        a.start();
        b.start();
    }
}

/**
 * 证明synchronized获得的线程锁是对象
 */
public class MyObject {
    synchronized public void methodA(){
        try {
            System.out.println("begin methodA: "+ Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println("end endTIme="+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    synchronized public void methodB(){
    public void methodB(){
        try {
            System.out.println("begin methodB: "+ Thread.currentThread().getName() + "begin time="+System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadA extends Thread{
    private MyObject object;
    public ThreadA(MyObject object){
        super();
        this.object = object;
    }
    @Override
    public void run(){
        super.run();
        object.methodA();
    }
}

class ThreadB extends Thread{
    private MyObject object;
    public ThreadB(MyObject object){
        super();
        this.object = object;
    }
    @Override
    public void run(){
        super.run();
        object.methodB();
        object.methodA();
    }
}