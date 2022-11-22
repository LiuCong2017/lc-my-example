package i_semaphore_09;

import java.util.UUID;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Semaphore_01 {
}

/**
 * Semaphore本质上就是一个信号计数器，用于限制同一时间的最大访问数量
 * 如果要对某一受限资源进行限流访问，可以使用Semaphore，保证同一时间最多N个线程访问受限资源。
 */
class AccessLimitControl{
    // 任意时刻仅允许最多3个线程获取许可:
    final Semaphore semaphore = new Semaphore(3);

    /**
     * 使用Semaphore先调用acquire()获取，
     * 然后通过try ... finally保证在finally中释放。
     * 调用acquire()可能会进入等待，直到满足条件为止
     */
    public String access() throws InterruptedException {
        // 如果超过了许可数量,其他线程将在此等待:
        semaphore.acquire();
        try {
            return UUID.randomUUID().toString();
        } finally {
            semaphore.release();
        }

        /**
         * 也可以使用tryAcquire()指定等待时间：
         */
//        if (semaphore.tryAcquire(3, TimeUnit.SECONDS)) {
//            // 指定等待时间3秒内获取到许可:
//            try {
//                // TODO:
//            } finally {
//                semaphore.release();
//            }
//        }
    }

}
