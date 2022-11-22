package l_threadpool_12;

import t1.Run;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPool_04 {
    public static void main(String[] args) {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(4);
        // 1秒后执行一次性任务(指定延迟后只执行一次):
        ses.schedule(new ScheduledTask("one-time"),1, TimeUnit.SECONDS);
        // 2秒后开始执行定时任务，每3秒执行一次(任务以固定的每3秒执行):
        ses.scheduleAtFixedRate(new ScheduledTask("fixed-rate"),2,3,TimeUnit.SECONDS);
        // 2秒后开始执行定时任务，以5秒为间隔执行(任务以固定的5秒为间隔执行):
        ses.scheduleWithFixedDelay(new ScheduledTask("fixed-delay"),2,5,TimeUnit.SECONDS);
    }
}

class ScheduledTask implements Runnable {
    private final String name;
    public ScheduledTask(String name){
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name);
    }
}