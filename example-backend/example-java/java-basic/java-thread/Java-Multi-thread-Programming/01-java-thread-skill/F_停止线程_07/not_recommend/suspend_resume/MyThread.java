package F_停止线程_07.not_recommend.suspend_resume;

public class MyThread  extends Thread{
    private long i =0;
    public long getI() {
        return i;
    }
    public void setI(long i) {
        this.i = i;
    }

    @Override
    public void run(){
        while (true){
            i++;
        }
    }
}
