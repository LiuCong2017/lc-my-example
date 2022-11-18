package t3_twoObjectTwoLock;

public class HasSelfPrivateNum {
    private int num = 0;

    synchronized public void addI(String u){
        try {
            if (u.equals("a")){
                num = 100;
                System.out.println("a set over");
                Thread.sleep(2000);
            }else {
                num = 200;
                System.out.println("b set over");
            }
            System.out.println(u+" num="+num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
