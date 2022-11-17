package com.lc.javaping.test.ping_run_proc_win_linux;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class JavaPing {

    public static boolean isReachable(String url) throws MalformedURLException {
        if (!url.contains("http") && !url.contains("https")){
            url = "http://"+url;
        }
        URL urlObj = new URL(url);
        url = urlObj.getHost();
        url = "ping "+url;

        try {
            Process proc = Runtime.getRuntime().exec(url);
            BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String str = "";
            while ((str=br.readLine())!=null){
                /**
                 * it's means only support english, sucks
                 */
                if (str.contains("Packets: Sent") || str.contains("bytes of data")){
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

}
