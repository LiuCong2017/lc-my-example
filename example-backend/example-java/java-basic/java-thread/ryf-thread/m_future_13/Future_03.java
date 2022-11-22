/*
https://baijiahao.baidu.com/s?id=1671811356385033078&wfr=spider&for=pc
https://zhuanlan.zhihu.com/p/364041672
https://zhuanlan.zhihu.com/p/506858141
https://www.zhihu.com/question/334309202
https://www.runoob.com/java/java9-completablefuture-api-improvements.html
https://baijiahao.baidu.com/s?id=1670783360239351244&wfr=spider&for=pc
 */
package m_future_13;

import java.util.concurrent.CompletableFuture;

public class Future_03 {
}

class CompletableFutureDemo{

    public static void main(String[] args) throws InterruptedException {
        //创建异步执行任务
        CompletableFuture.supplyAsync(CompletableFutureDemo::getPrice)//搭配 Lambda 的使用
                //当getPrice()执行完成后，会自动调用thenAccept()中的函数
                .thenAccept(result->{ //当getPrice()执行成功后的后续处理
                    System.out.println("price: "+result);
                })
                //当出现异常时，会自动回调exceptionally()里的函数
                .exceptionally(e->{ //当getPrice()执行异常后的后续处理
                    e.printStackTrace();
                    return null;
                });
        Thread.sleep(200);
    }

    static Double getPrice(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        //模拟异常
        if (Math.random() < 0.3){
            throw new RuntimeException("Error when get price");
        }
        return Math.random() * 20;
    }

}