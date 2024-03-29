package n_completefuture_14;

import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture
 *
 * CompletableFuture可以指定异步处理流程：
 *
 * thenAccept()处理正常结果；
 * exceptional()处理异常结果；
 * thenApplyAsync()用于串行化另一个CompletableFuture；
 * anyOf()和allOf()用于并行化多个CompletableFuture
 */
public class CompletableFuture_01 {

    public static void main(String[] args) throws InterruptedException {
        //创建异步执行任务
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(CompletableFuture_01::fetchPrice);
        //如果执行成功:
        cf.thenAccept(result->{
            System.out.println("price: "+result);
        });
        //如果执行异常：
        cf.exceptionally(e->{
            e.printStackTrace();
            return null;
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(200);
    }

    static Double fetchPrice(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        if (Math.random()<0.3){
            throw new RuntimeException("fetch failed");
        }
        return 5+Math.random()*20;
    }

}


