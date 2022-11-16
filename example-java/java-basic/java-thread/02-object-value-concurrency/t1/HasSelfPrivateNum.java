package t1;

/**
 * "非线程安全"存在于"实例变量"中
 * 方法内的变量为线程安全
 */
public class HasSelfPrivateNum {

    public void addI(String u){
        try {
            //方法内部的变量是私有的，是线程安全的
            int num = 0;
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
