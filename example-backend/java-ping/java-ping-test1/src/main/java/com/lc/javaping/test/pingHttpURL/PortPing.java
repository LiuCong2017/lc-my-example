package com.lc.javaping.test.pingHttpURL;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class PortPing {

    public static boolean pingHost(String host, int port, int timeout){
        try (Socket socket = new Socket()){
            socket.connect(new InetSocketAddress(host,port), timeout);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        boolean res = pingHost("127.0.0.1",80,5000);
        System.out.println(res);
    }

}
