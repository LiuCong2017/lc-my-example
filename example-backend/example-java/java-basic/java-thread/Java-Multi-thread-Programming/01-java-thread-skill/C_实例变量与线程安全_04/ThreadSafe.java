package C_实例变量与线程安全_04;

public class ThreadSafe {
    public static void main(String[] args) {
        ALogin a = new ALogin();
        a.start();
        BLogin b = new BLogin();
        b.start();
    }
}

class ALogin extends Thread{
    @Override
    public void run(){
        LoginServlet.doPost("a","aa");
    }

}

class BLogin extends Thread{
    @Override
    public void run(){
        LoginServlet.doPost("b","bb");
    }
}

class LoginServlet {
    private static String usernameRef;
    private static String passwordRef;

    /**
     * If it not adds synchronized and will change to unsafe.
     */
//    synchronized public static void doPost(String username, String password){
    public static void doPost(String username, String password){
        try {
            usernameRef = username;
            if (username.equals("a")){
                Thread.sleep(5000);
            }
            passwordRef = password;
            System.out.println("username="+usernameRef+" password="+password);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}