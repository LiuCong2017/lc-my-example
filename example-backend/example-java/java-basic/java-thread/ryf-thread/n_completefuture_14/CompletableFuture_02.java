package n_completefuture_14;

import java.util.concurrent.CompletableFuture;

/**
 * 串行执行
 * 多个CompletableFuture可以串行执行，例如，定义两个CompletableFuture，
 * 第一个CompletableFuture根据证券名称查询证券代码，
 * 第二个CompletableFuture根据证券代码查询证券价格
 */
public class CompletableFuture_02 {

    public static void main(String[] args) throws InterruptedException {
        // 第一个任务:
        CompletableFuture<String> cfQuery = CompletableFuture.supplyAsync(()->{
           return queryCode("NASDAQ");
        });
        // cfQuery成功后继续执行下一个任务:
        CompletableFuture<Double> cfFetch = cfQuery.thenApplyAsync((code)->{
            return fetchPrice(code);
        });
        // cfFetch成功后打印结果:
        cfFetch.thenAccept((result)->{
            System.out.println("price: "+result);
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(2000);
    }

    static String queryCode(String name){
        return "9Q0026";
    }

    static Double fetchPrice(String code){
        return 5+Math.random()*20;
    }

}
