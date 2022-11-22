package l_threadpool_12;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewFixedThreadPool_02 {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(4);
        for (int i=0;i<6;i++){
            es.submit(new Task(""+i));
        }

        //关闭线程池
        es.shutdown();
    }

}

class Task implements Runnable{
    private final String name;
    public Task(String name){
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("start task "+name);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
        System.out.println("end task "+name);
    }
}