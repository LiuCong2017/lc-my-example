package com.lc.javaping.service;

import org.springframework.stereotype.Component;

@Component
public class PingService {
    public void startNginx(Process p) {
//		Runtime rt = Runtime.getRuntime();
//		String command = "cmd /c D:&& cd \\nginx_1.7.11.3_Gryphon && nginx.exe -c conf\\nginx-win-rtmp.conf";
        try {
            //获取进程的标准输入流
            final InputStream is1 = p.getInputStream();
            //获取进程的错误流
            final InputStream is2 = p.getErrorStream();
            //启动两个线程，一个线程负责读标准输出流，另一个负责读标准错误流
            new Thread() {
                public void run() {
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(is1));
                    try {
                        String line1 = null;
                        while ((line1 = br1.readLine()) != null) {
                            if (line1 != null){}
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    finally{
                        try {
                            is1.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();

            new Thread() {
                public void  run() {
                    BufferedReader br2 = new  BufferedReader(new  InputStreamReader(is2));
                    try {
                        String line2 = null ;
                        while ((line2 = br2.readLine()) !=  null ) {
                            if (line2 != null){}
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    finally{
                        try {
                            is2.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();

            p.waitFor();
            p.destroy();
            System.out.println("nginx启动成功");
        } catch (Exception e) {
            try{
                p.getErrorStream().close();
                p.getInputStream().close();
                p.getOutputStream().close();
            }
            catch(Exception ee){}
        }
    }

    public void stopNginx() {

    }

}
