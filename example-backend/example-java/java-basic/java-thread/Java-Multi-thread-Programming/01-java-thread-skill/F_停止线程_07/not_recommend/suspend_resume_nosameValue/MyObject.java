package F_停止线程_07.not_recommend.suspend_resume_nosameValue;

/**
 * suspend与resume方法的缺点 - 不同步
 */
public class MyObject {
    private String username = "1";
    private String password = "11";
    public void setValue(String u, String p){
        this.username = u;
        if (Thread.currentThread().getName().equals("a")){
            System.out.println("停止a线程");
            Thread.currentThread().suspend();
        }
        this.password = p;
    }

    public void printUserPwd(){
        System.out.println(username + " : "+ password);
    }

}
