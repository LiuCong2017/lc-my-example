package t3_twoObjectTwoLock;

public class Run {
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
