package l_threadpool_12;

import java.util.concurrent.*;

public class NewCachedThreadPool_03 {
    public static void main(String[] args) {
        //CachedThreadPool会根据任务数量动态调整线程池的大小
        ExecutorService cachedTB = Executors.newCachedThreadPool();
        cachedTB.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("executor");
            }
        });

        //把线程池的大小限制在4～10个之间动态调整
        int min = 4, max = 10;
        ExecutorService es = new ThreadPoolExecutor(min,max,60L, TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
    }
}
