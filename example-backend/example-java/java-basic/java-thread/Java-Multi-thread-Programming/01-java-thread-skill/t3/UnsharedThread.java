package t3;

public class UnsharedThread extends Thread{
    private int count = 5;
    public UnsharedThread(String name){
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
