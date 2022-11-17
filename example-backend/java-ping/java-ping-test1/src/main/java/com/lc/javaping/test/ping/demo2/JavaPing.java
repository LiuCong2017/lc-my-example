package com.lc.javaping.test.ping.demo2;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Scanner;

public class JavaPing {

    public static void sendPingRequest(String ipAddr) throws IOException {
        InetAddress ia = InetAddress.getByName(ipAddr);
        System.out.println("ping: "+ipAddr);
        if (ia.isReachable(5000))
            System.out.println(ipAddr+"is reachable");
        else
            System.out.println(ipAddr+"is unreachable");
    }

    public static void main(String[] args) throws IOException {
        String ipAddr;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ip");
        ipAddr = sc.next();
        sendPingRequest(ipAddr);
    }

}
