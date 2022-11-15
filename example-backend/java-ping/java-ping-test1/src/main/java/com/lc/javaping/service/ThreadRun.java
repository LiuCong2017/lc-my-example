package com.lc.javaping.service;

public class ThreadRun extends Thread{
    private String rtspUrl;
    private String rtmpUrl;

    public ThreadRun(String rtspUrl, String rtmpUrl) {
        super();
        this.rtspUrl = rtspUrl;
        this.rtmpUrl = rtmpUrl;
    }

    @Override
    public void run() {

        try {
//			new RecordPush_Service().recordPush4(rtspUrl,rtmpUrl,25);
            new PushStreamService().push_stream(rtspUrl, rtmpUrl);
            while(true) {
                //判断线程是否中断
                if(Thread.currentThread().isInterrupted()) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
