package n_completefuture_14;

import java.util.concurrent.CompletableFuture;

/**
 * 并行执行
 * 同时从新浪和网易查询证券代码，
 * 只要任意一个返回结果，就进行下一步查询价格，
 * 查询价格也同时从新浪和网易查询，只要任意一个返回结果，
 * 就完成操作
 */
public class CompletableFuture_03 {
    /**
     * 除了
     * anyOf()可以实现“任意个CompletableFuture只要一个成功”，
     * allOf()可以实现“所有CompletableFuture都必须成功”，
     * 这些组合操作可以实现非常复杂的异步流程控制。
     *
     * 最后注意CompletableFuture的命名规则：
     * xxx()：表示该方法将继续在已有的线程中执行；
     * xxxAsync()：表示将异步在线程池中执行。
     */

    public static void main(String[] args) throws InterruptedException {
        // 两个CompletableFuture执行异步查询:
        CompletableFuture<String> cfQueryFromSina = CompletableFuture.supplyAsync(()->{
           return queryCode("NASDAQ","https://sina.nsdq.com/code");
        });
        CompletableFuture<String> cfQueryFrom163 = CompletableFuture.supplyAsync(()->{
           return queryCode("NASDAQ","https://163.nsdq.com/code");
        });

        // 用anyOf合并为一个新的CompletableFuture:
        CompletableFuture<Object> cfQuery = CompletableFuture.anyOf(cfQueryFromSina,cfQueryFrom163);

        // 两个CompletableFuture执行异步查询:
        CompletableFuture<Double> cfFetchFromSina = cfQuery.thenApplyAsync((code)->{
            return fetchPrice((String) code,"https://sina.nsdq.com/price");
        });
        CompletableFuture<Double> cfFetchFrom163 = cfQuery.thenApplyAsync((code)->{
           return  fetchPrice((String) code,"https://163.nsdq.com/code");
        });

        // 用anyOf合并为一个新的CompletableFuture:
        CompletableFuture<Object> cfFetch = CompletableFuture.anyOf(cfFetchFromSina,cfFetchFrom163);
        // 最终结果:
        cfFetch.thenAccept((result) -> {
            System.out.println("price: " + result);
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(200);

    }

    static String queryCode(String name, String url) {
        System.out.println("query code from " + url + "...");
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
        }
        return "601857";
    }

    static Double fetchPrice(String code, String url) {
        System.out.println("query price from " + url + "...");
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
        }
        return 5 + Math.random() * 20;
    }

}
