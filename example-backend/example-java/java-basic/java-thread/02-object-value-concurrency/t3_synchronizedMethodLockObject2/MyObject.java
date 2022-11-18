package t3_synchronizedMethodLockObject2;

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
