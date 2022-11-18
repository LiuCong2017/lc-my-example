package com.lc.javaping.test.ping_run_proc_win_linux;

import java.net.MalformedURLException;

public class TestJavaPing {
    public static void main(String[] args) throws MalformedURLException {
        boolean res = JavaPing.isReachable("127.0.0.1");
        System.out.println(res);
    }
}
