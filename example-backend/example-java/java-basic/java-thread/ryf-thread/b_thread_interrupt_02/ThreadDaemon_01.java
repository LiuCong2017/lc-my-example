package b_thread_interrupt_02;

import java.time.LocalTime;

public class ThreadDaemon_01 {
    public static void main(String[] args) {
        TimerThread t = new TimerThread();
//        t.setDaemon(true);
        t.start();
    }

}

class TimerThread extends Thread{
    public void run(){
        while (true){
            System.out.println(LocalTime.now());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }

        }
    }
}
