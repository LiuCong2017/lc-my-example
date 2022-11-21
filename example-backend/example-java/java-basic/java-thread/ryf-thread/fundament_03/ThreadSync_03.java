package fundament_03;

public class ThreadSync_03 {
    public static void main(String[] args) {
        var c1 = new Counter();
        var c2 = new Counter();

        //c1
        new Thread(()->{
            c1.add(10);
        }).start();
        new Thread(()->{
            c1.dec(10);
        }).start();
        System.out.println(c1.get());

        //c2
        new Thread(()->{
            c2.add(10);
        }).start();
        new Thread(()->{
            c2.dec(10);
        }).start();
        System.out.println(c2.get());

    }

}
