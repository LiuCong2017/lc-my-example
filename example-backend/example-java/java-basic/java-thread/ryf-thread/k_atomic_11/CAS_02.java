package k_atomic_11;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class CAS_02 {
    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(()->{
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            spinLockDemo.myUnLock();
        }, "A_继承Thread类_01").start();

        new Thread(()->{
            spinLockDemo.myLock();
            spinLockDemo.myUnLock();
        }, "B_实现Runnable接口_03").start();

    }
}

class SpinLockDemo{
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock(){
        System.out.println(Thread.currentThread().getName()+" running");
        while (!atomicReference.compareAndSet(null,Thread.currentThread())){

        }
        System.out.println(Thread.currentThread().getName()+" got lock");
    }

    public void myUnLock(){
        atomicReference.compareAndSet(Thread.currentThread(),null);
        System.out.println(Thread.currentThread().getName()+" release lock");
    }

}