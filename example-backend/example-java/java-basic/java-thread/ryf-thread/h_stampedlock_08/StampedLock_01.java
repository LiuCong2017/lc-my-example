package h_stampedlock_08;

import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock和ReadWriteLock相比，改进之处在于：
 * 读的过程中也允许获取写锁后写入！这样一来，读的数据就可能不一致，
 * 所以，需要一点额外的代码来判断读的过程中是否有写入，这种读锁是一种乐观锁。
 *
 * 乐观锁的意思就是乐观地估计读的过程中大概率不会有写入，因此被称为乐观锁。
 * 反过来，悲观锁则是读的过程中拒绝有写入，也就是写入必须等待。
 * 显然乐观锁的并发效率更高，但一旦有小概率的写入导致读取的数据不一致，需要能检测出来，再读一遍就行。
 */
public class StampedLock_01 {
}

/**
 * StampedLock提供了乐观读锁，可取代ReadWriteLock以进一步提升并发性能；
 * StampedLock是不可重入锁。
 */
class Point{
    private final StampedLock stampedLock = new StampedLock();

    private double x;
    private double y;

    public void move(double deltaX, double deltaY){
        long stamp = stampedLock.writeLock(); //获取写锁
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            stampedLock.unlockWrite(stamp); //释放写锁
        }
    }

    public double distanceFromOrigin(){
        /**1. 首先通过tryOptimisticRead()获取一个乐观读锁，并返回版本号 */
        long stamp = stampedLock.tryOptimisticRead(); //获得一个乐观读锁

        /**
         *  注意下面两行代码不是原子操作
         *  假设x,y = (100,200)
         */
        /**2. 接着进行读取*/
        double currentX = x; //此处已读取到x=100，但x,y可能被写线程修改为(300,400)
        double currentY = y;

        /**
         * 此处已读取到y，如果没有写入，读取是正确的(100,200)
         * 如果有写入，读取是错误的(100,400)
         */
        /**3.读取完成后通过validate()去验证版本号，
         * 若在读取过程中没有写入，版本号不变，验证成功，就可以放心地继续后续操作。
         * 如果在读取过程中有写入，版本号会发生变化，验证将失败。
         * 在失败的时候，再通过获取悲观读锁再次读取。
         */
        if (!stampedLock.validate(stamp)){ // 检查乐观读锁后是否有其他写锁发生
            stamp = stampedLock.readLock(); //获取一个悲观读锁
            try { //验证失败，说明有其它线程获得写锁在写，则通过悲观锁再次读取
                currentX = x;
                currentY = y;
            } finally {
                stampedLock.unlockRead(stamp); // 释放悲观读锁
            }
        }
        return Math.sqrt(currentX * currentX + currentY*currentY);
    }

}

