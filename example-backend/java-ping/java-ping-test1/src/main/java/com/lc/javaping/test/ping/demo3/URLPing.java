package com.lc.javaping.test.ping.demo3;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URL;
import java.nio.channels.SocketChannel;

public class URLPing {

    public static void main(String[] args) throws IOException {
//        InetSocketAddress isa = new InetSocketAddress("127.0.0.1",8080);
//        boolean res = isa.getAddress().isReachable(5000);
//        System.out.println(res);

//        SocketChannel sc = SocketChannel.open();

        String status = getStatus("http://localhost");
        System.out.println(status);
    }

    public static String getStatus(String url){
        String result = "";
        try {
            URL urlObj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            con.connect();

            int code = con.getResponseCode();
            if (code==200){
                result = "ON";
            }
        } catch (Exception e) {
            result = "OFF";
        }
        return result;
    }

}
