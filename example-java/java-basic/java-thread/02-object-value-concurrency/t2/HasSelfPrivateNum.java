package t2;

/**
 * "非线程安全"存在于"实例变量"中
 * 方法内的变量为线程安全
 */
public class HasSelfPrivateNum {
    //对象中的实例变量，非线程安全，多个线程通过非同步方法操作该变量可能出现覆盖
    private int num = 0;

    //通过synchronized使其变成同步方法，则可保证以上变量是线程安全的
    synchronized public void addI(String u){
//    public void addI(String u){ //非同步方法
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
