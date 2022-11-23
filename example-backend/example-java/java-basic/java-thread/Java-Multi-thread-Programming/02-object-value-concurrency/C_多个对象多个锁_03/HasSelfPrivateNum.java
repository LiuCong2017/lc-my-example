package C_多个对象多个锁_03;

class Run {
    public static void main(String[] args) {
        /**
         * synchronized取得的锁都是对象锁，而不是把方法(函数)当做锁
         * 此处创建了多个对象，JVM会创建多个锁，所以运行结果是异步的
         */
        HasSelfPrivateNum numRef1 = new HasSelfPrivateNum();
        HasSelfPrivateNum numRef2 = new HasSelfPrivateNum();

        ThreadA threadA = new ThreadA(numRef1);
        threadA.start();

        ThreadB threadB = new ThreadB(numRef2);
        threadB.start();
    }
}

public class HasSelfPrivateNum {
    private int num = 0;

    synchronized public void addI(String u){
        try {
            if (u.equals("a")){
                num = 100;
                System.out.println("a set over");
                Thread.sleep(2000);
            }else {
                num = 200;
                System.out.println("b set over");
            }
            System.out.println(u+" num="+num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class ThreadA extends Thread{
    private HasSelfPrivateNum numRef;
    public ThreadA(HasSelfPrivateNum numRef){
        super();
        this.numRef = numRef;
    }

    @Override
    public void run(){
        super.run();
        numRef.addI("a");
    }
}

class ThreadB extends Thread{
    private HasSelfPrivateNum numRef;
    public ThreadB(HasSelfPrivateNum numRef){
        super();
        this.numRef = numRef;
    }

    @Override
    public void run(){
        super.run();
        numRef.addI("b");
    }
}
