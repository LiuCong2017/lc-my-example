package m_future_13;

import java.util.concurrent.*;

/**
 * 实现一个简单的Future模式(代理模式)
 */
public class Future_01 {
    /**
     * 5. 最后一个Main函数，把所有一切都串起来：
     */
    public static void main(String[] args) throws InterruptedException {
        Client client = new Client();
        //这里会立即返回，因为得到的是FutureData而不是RealData
        Data data = client.request("name");
        System.out.println("请求完毕");
        //这里可以用一个sleep代替了对其他业务逻辑的处理
        //在处理这些业务逻辑的过程中，RealData被创建，从而充分利用了等待时间
        Thread.sleep(2000);
        //使用真实的数据，如果到这里数据还没有准备好，getResult()会等待数据准备完，再返回
        System.out.println("数据= "+data.getResult());
    }
}

/**
 * 1. 首先是Data接口，代表数据：
 */
interface Data{
    public String getResult();
}

/**
 * 2. 接着是FutureData，也是整个Future模式的核心：
 */
class FutureData implements Data {
    // 内部需要维护RealData
    protected RealData realData = null;
    protected boolean isReady = false;

    public synchronized void setRealData(RealData realData){
        if (isReady){
            return;
        }
        this.realData = realData;
        isReady = true;
        //RealData已经被注入，通知getResult()
        notifyAll();
    }

    //会等待RealData构造完成
    @Override
    public synchronized String getResult() {
        while (!isReady){
            try {
                //一直等待，直到RealData被注入
                wait();
            } catch (InterruptedException e) {
            }
        }
        //真正需要的数据从RealData获取
        return realData.result;
    }

}

/**
 * 3. 下面是RealData：
 */
class RealData implements Data, Callable<String> {
    protected final String result;
    public RealData(String para){
        StringBuffer sb = new StringBuffer(para);
        //假设这里很慢很慢，构造RealData不是一个容易的事
        result = sb.toString();
    }

    @Override
    public String getResult() {
        return result;
    }

    @Override
    public String call() throws Exception {
        return getResult();
    }
}

/**
 * 4. 然后从Client得到Data：
 */
class Client{
    //这是一个异步方法，返回的Data接口是一个Future
    public Data request(final String queryStr){
        final FutureData future = new FutureData();
        new Thread(()->{
            // RealData的构建很慢，所以在单独的线程中进行
            RealData realData = new RealData(queryStr);
            //setRealData()的时候会notify()等待在这个future上的对象
            future.setRealData(realData);
        }).start();
        // FutureData会被立即返回，不会等待RealData被构造完
        return future;
    }

}

/**
 * java Future的使用
 */
class TestMain{
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //异步操作 可以用一个线程池
        ExecutorService executor = Executors.newFixedThreadPool(1);
        //执行FutureTask，相当于上例中的 client.request("name") 发送请求
        //在这里开启线程进行RealData的call()执行
        Future<String> future = executor.submit(new RealData("name"));
        System.out.println("请求完毕，数据准备中");
        try {
            //这里依然可以做额外的数据操作，这里使用sleep代替其他业务逻辑的处理
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        //如果此时call()方法没有执行完成，则依然会等待
        System.out.println("数据 = " + future.get());
    }
}