package k_atomic_11;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 *  CAS（compare and swap) - 无锁思想(乐观锁/自旋锁)
 *  当多个线程尝试使用CAS同时更新同一个变量时，只有其中一个线程能更新变量的值，
 *  而其它线程都失败，失败的线程并不会被挂起，而是被告知这次竞争中失败，并可以再次尝试。
 *  CAS操作的就是乐观锁，每次不加锁而是假设没有冲突而去完成某项操作，如果因为冲突失败就重试，直到成功为止。
 *  CAS需注意：
 *  1. ABA问题
 *  2. 在cas自旋的过程中，对比和写入的过程必须是原子的，不可拆分的。否则仍然会出现数据一致性问题。
 *
 * Java的java.util.concurrent包除了提供底层锁、并发集合外，
 * 还提供了一组原子操作的封装类，
 * 它们位于java.util.concurrent.atomic包。
 *
 * 我们以AtomicInteger为例，它提供的主要操作有：
 * 增加值并返回新值：int addAndGet(int delta)
 * 加1后返回新值：int incrementAndGet()
 * 获取当前值：int get()
 * 用CAS方式设置：int compareAndSet(int expect, int update)
 *
 * Atomic类是通过无锁（lock-free）的方式实现的线程安全（thread-safe）访问。
 * 它的主要原理是利用了CAS：Compare and Set。
 */
public class Atomic_01 {

    /**
     * CAS是指，在这个操作中，
     * 如果AtomicInteger的当前值是prev，那么就更新为next，返回true。
     * 如果AtomicInteger的当前值不是prev，就什么也不干，返回false。
     * 通过CAS操作并配合do ... while循环，
     * 即使其他线程修改了AtomicInteger的值，最终的结果也是正确的。
     */
    //通过CAS手写incrementAndGet()
    public int incrementAndGet(AtomicInteger var){
        int prev, next;
        do {
            prev = var.get();
            next = prev + 1;
        }while (!var.compareAndSet(prev,next));
        return next;
    }

}

/**
 * 使用java.util.concurrent.atomic提供的原子操作可以简化多线程编程：
 * 原子操作实现了无锁的线程安全；
 * 适用于计数器，累加器等。
 */
//利用AtomicLong可以编写一个多线程安全的全局唯一ID生成器：
class IdGenerator{
    AtomicLong var = new AtomicLong(0);

    /**
     * 通常情况下并不需要直接用do ... while循环调用compareAndSet实现复杂的并发操作，
     * 而是用incrementAndGet()这样的封装好的方法。
     * 在高度竞争的情况下，还可使用Java 8提供的LongAdder和LongAccumulator。
     */
    public long getNextId(){
        return var.incrementAndGet();
    }
}