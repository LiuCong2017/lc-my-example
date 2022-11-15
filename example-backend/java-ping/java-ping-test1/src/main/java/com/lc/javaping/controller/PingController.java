package com.lc.javaping.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {
    @Autowired
    NginxService ns;

    //启动请求，触发推流
    @PostMapping("/getRtmp")
    public String Get(@RequestBody RtspEntity re) {
        //请求参数
        String user = re.getUser();
        String password = re.getPassword();
        int channel = re.getChannel();
        String ip = re.getIp();

        String rtspUrl = "rtsp://"+user+":"+password+"@"+ip+"/cam/realmonitor?channel="+channel+"&subtype=0";
        String rtmpUrl = "rtmp://172.30.83.254:1935/live/home";

        //启动nginx(CMD方式注意在waitFor之前消耗标准输入和标准错误流防止阻塞主线程)
//		String start = "cmd /c D:&& cd \\nginx_1.7.11.3_Gryphon && nginx.exe -c conf\\nginx-win-rtmp.conf";
//		//关闭nginx 方式1
//		String stop = "cmd /c D:&& cd \\nginx_1.7.11.3_Gryphon && stop_nginx.bat";
//		//关闭nginx 方式2
////		String stop = "cmd /c taskkill /im nginx.exe /f";

        //重启nginx
        String command = "cmd /c D:&& cd \\nginx_1.7.11.3_Gryphon && nginx.exe -c conf\\nginx-win-rtmp.conf";
        Process p = null;
        try {
            p = Runtime.getRuntime().exec(command);
            ns.startNginx(p);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //new线程实例
        ThreadRun trs = new ThreadRun(rtspUrl,rtmpUrl);
        //重启线程
        if(trs.isAlive()) {
            trs.interrupt();
            trs.start();
        }else {
            trs.start();
        }

        try {
            p.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "开始推流"; //此处应重定向到视频页面 (例如tomcat视频流服务器地址)
    }
}
