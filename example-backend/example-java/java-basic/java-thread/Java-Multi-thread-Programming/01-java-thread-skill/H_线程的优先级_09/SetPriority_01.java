package H_线程的优先级_09;

import java.util.Random;

public class SetPriority_01 {
}

/**
 * 值越大，优先级越高，但线程优先级与执行顺序无关，不过优先级高的线程可能运行耗时小
 */
class Run {
    public static void main(String[] args) {
        for (int i=0;i<5;i++){
            MyThread1 thread1 = new MyThread1();
//            thread1.setPriority(10);
            thread1.setPriority(1);
            thread1.start();

            MyThread2 thread2 = new MyThread2();
//            thread2.setPriority(1);
            thread2.setPriority(10);
            thread2.start();
        }
    }
}

class MyThread1 extends Thread{
    @Override
    public void run(){
        long beginTime = System.currentTimeMillis();
        long addResult = 0;
        for (int j=0;j<10;j++){
            for (int i=0;i<50000;i++){
                Random random = new Random();
                random.nextInt();
                addResult = addResult+i;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("thread1 time="+(endTime-beginTime));
    }
}

class MyThread2 extends Thread{
    @Override
    public void run(){
        long beginTime = System.currentTimeMillis();
        long addResult = 0;
        for (int j=0;j<10;j++){
            for (int i=0;i<50000;i++){
                Random random = new Random();
                random.nextInt();
                addResult = addResult+i;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("thread2 time="+(endTime-beginTime));
    }
}
