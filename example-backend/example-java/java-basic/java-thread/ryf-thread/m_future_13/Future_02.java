package m_future_13;

import java.util.concurrent.*;

public class Future_02 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> c1 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                //模拟子线程任务
                Thread.sleep(1000);
                return "c1 done "+Thread.currentThread().getName();
            }
        };
        FutureTask<String> ft1 = new FutureTask<>(c1);
        new Thread(ft1).start();

        Callable<String> c2 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                //模拟子线程任务
                Thread.sleep(3000);
                System.out.println(ft1.get());
                return "c2 done "+Thread.currentThread().getName();
            }
        };
        FutureTask<String> ft2 = new FutureTask<>(c2);
        new Thread(ft2).start();

        System.out.println("主线程: "+Thread.currentThread().getName());

        System.out.println(ft1.get());
        System.out.println(ft2.get());

    }
}
