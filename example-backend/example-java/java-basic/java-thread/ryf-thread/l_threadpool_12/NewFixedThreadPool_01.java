package l_threadpool_12;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewFixedThreadPool_01 {

    public static void main(String[] args) {
        // 创建固定大小的线程池:
        ExecutorService executor = Executors.newFixedThreadPool(3);
        // 提交任务:
        executor.submit(new task());
        executor.submit(new task());
        executor.submit(new task());
        executor.submit(new task());
        executor.submit(new task());
        executor.submit(new task());
        executor.submit(new task());
    }

}

class task implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
