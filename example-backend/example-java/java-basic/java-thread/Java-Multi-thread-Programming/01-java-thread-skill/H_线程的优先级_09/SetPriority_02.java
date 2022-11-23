package H_线程的优先级_09;

import java.util.Random;

public class SetPriority_02 {
}

/**
 * 优先级具有随机性
 */
class Run {
    public static void main(String[] args) {
        for (int i=0;i<5;i++){
            t_20.MyThread1 thread1 = new t_20.MyThread1();
            thread1.setPriority(5);
            thread1.start();

            t_20.MyThread2 thread2 = new t_20.MyThread2();
            thread2.setPriority(6);
            thread2.start();
        }
    }
}


class MyThread1 extends Thread{
    @Override
    public void run(){
        long beginTime = System.currentTimeMillis();
        for (int i=0;i<1000;i++){
            Random random = new Random();
            random.nextInt();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Thread1 time: "+(endTime-beginTime));
    }
}

class MyThread2 extends Thread{
    @Override
    public void run(){
        long beginTime = System.currentTimeMillis();
        for (int i=0;i<1000;i++){
            Random random = new Random();
            random.nextInt();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Thread2 time: "+(endTime-beginTime));
    }
}
