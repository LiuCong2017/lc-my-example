package fundament_04;

/**
 * Java的线程锁是可重入的锁
 * JVM允许同一个线程重复获取同一个锁，这种能被同一个线程反复获取的锁，就叫做可重入锁
 */
public class ReentrantLock_01 {


}
class Counter{
    private int count = 0;

    public synchronized void add(int n){
        if (n<0){
            dec(-n);
        }else {
            count += n;
        }
    }

    public synchronized void dec(int n){
        count += n;
    }

}

