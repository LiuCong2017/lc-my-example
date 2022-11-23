package pkg;

class Run {
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
 * 证明synchronized获得的线程锁是对象实例而不是方法
 */
public class MyObject {
    synchronized public void methodA(){
        try {
            System.out.println("begin methodA: "+ Thread.currentThread().getName());
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
        object.methodA();
    }
}