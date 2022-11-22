/*
 * @author Kavin Liu
 */

package k_atomic_11;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class CAS_01 {
    public static void main(String[] args) {
        AtomicInteger ai = new AtomicInteger(5);
        AtomicBooleanTest ast = new AtomicBooleanTest();
        Thread thread1 = new Thread(ast);
        Thread thread2 = new Thread(ast);
        thread1.start();
        thread2.start();
    }
}

class AtomicBooleanTest implements Runnable{
    private static AtomicBoolean flag = new AtomicBoolean(true);

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" : "+flag.get());
        if (flag.compareAndSet(true,false)){
            System.out.println(Thread.currentThread().getName()+" : "+flag.get());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag.set(true);
        }else {
            System.out.println("重试: "+Thread.currentThread().getName()+" : "+flag.get());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            run();
        }


    }
}