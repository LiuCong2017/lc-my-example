package m_future_13;

import java.util.concurrent.*;

public class Future_04 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        // 定义任务:
        Callable<String> task = new Task();
        // 提交任务并获得Future:
        Future<String> future = executor.submit(task);
        // 从Future获取异步执行返回的结果:
        String result = future.get(); //在调用get()时，如果异步任务已经完成，我们就直接获得结果。如果异步任务还没有完成，那么get()会阻塞，直到任务完成后才返回结果。
        System.out.println(result);
//        executor.close();
    }

}

//class Task implements Runnable{
//    public String result;
//
//    @Override
//    public void run() {
//        this.result = longTimeCalCulation();
//    }
//}

class Task implements Callable<String>{

    @Override
    public String call() throws Exception {
        return longTimeCalculation();
    }

    public String longTimeCalculation() throws InterruptedException {
        Thread.sleep(2000);
        return "result";
    }
}