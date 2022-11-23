package C_实例变量与线程安全_04;

/**
 * 线程间不共享数据,自己减少自己的值,不存在多个线程访问同一个实例变量的情况
 */
class Test3 {
    public static void main(String[] args) {
        /**
         * 1. We create 3 thread, and every thread have own count
         * 2. So thread variables are not shared
         */
        UnSharedThread a = new UnSharedThread("A");
        UnSharedThread b = new UnSharedThread("B");
        UnSharedThread c = new UnSharedThread("C");
        a.start();
        b.start();
        c.start();
    }
}

public class UnSharedThread extends Thread{
    private int count = 5;
    public UnSharedThread(String name){
        super();
        this.setName(name); //set thread name
    }

    @Override
    public void run(){
        super.run();
        while (count>0){
            count--;
            System.out.println(this.currentThread().getName()+": count="+count);
        }
    }

}
