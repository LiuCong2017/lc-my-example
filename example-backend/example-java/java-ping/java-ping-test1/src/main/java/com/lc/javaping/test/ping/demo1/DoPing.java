package com.lc.javaping.test.ping.demo1;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class DoPing {

    public static void main(String[] args) {
        try {
            InetAddress ia = InetAddress.getByName("www.baidu.com");
            String name = ia.getHostName();
            String addr = ia.getHostAddress();
            boolean status = ia.isReachable(10000);
            System.out.println(name + "-" + addr + "-"+status);
        } catch (UnknownHostException e) {
            System.err.println("Host does not exists");
        } catch (IOException e) {
            System.err.println("Error in reaching the Host");
        }
    }

}
