package G_yield方法_08;

class Run {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}

/**
 * yield()方法放弃当前的CPU资源
 */
public class MyThread extends Thread{
    @Override
    public void run(){
        long beginTime = System.currentTimeMillis();
        int count = 0;
        for (int i=0;i<5000000;i++){
            Thread.yield();
            count = count + (i+1);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("用时:"+(endTime-beginTime)+"毫秒");
    }
}
