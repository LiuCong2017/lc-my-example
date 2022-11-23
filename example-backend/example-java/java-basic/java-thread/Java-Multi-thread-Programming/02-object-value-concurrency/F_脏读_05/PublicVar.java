package pkg;

/**
 * 脏读一定会出现在操作实例变量的情况下,即不同线程"争夺"实例变量的结果
 */
class Test{
    public static void main(String[] args) throws InterruptedException {
        PublicVar publicVar = new PublicVar();
        ThreadA threadA = new ThreadA(publicVar);
        threadA.start();
        Thread.sleep(200);
        publicVar.getValue(); //main线程出现脏读
    }
}

public class PublicVar {
    public String username = "A";
    public String password = "AA";
    //该方法的锁属于类PublicVar的实例
    synchronized public void setValue(String username,String password) throws InterruptedException {
        this.username = username;
        Thread.sleep(5000);
        this.password = password;
        System.out.println(Thread.currentThread().getName()+" - username="+username+" - password="+password);
    }

    /**
     * 通过synchronized解决脏读
     * 因为线程A持有对象锁,调用setValue方法, 其它线程调用声明了synchronized关键字的非setValue方法则必须等待A线程释放对象锁
     */
    synchronized public void getValue(){
//    public void getValue(){
        System.out.println(Thread.currentThread().getName()+" - username="+username+" - password="+password);
    }
}

class ThreadA extends Thread{
    private PublicVar publicVar;
    public ThreadA(PublicVar publicVar){
        super();
        this.publicVar = publicVar;
    }

    public void run(){
        super.run();
        try {
            publicVar.setValue("B","BB");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

