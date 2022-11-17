package com.lc.javaping.test.ping.demo5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LinuxPingInJava {

    public static void main(String[] args) {
        // Returns the runtime object associated with the current Java application.
        Runtime run = Runtime.getRuntime();
        GetResultFromCMD error,result;

        try {
            Process proc = run.exec("ping 127.0.0.1");
            error = GetResultFromCMD.getResultFromCMD(proc.getErrorStream(),"ERROR");
            result = GetResultFromCMD.getResultFromCMD(proc.getInputStream(),"OUTPUT");
//            error = new GetResultFromCMD(proc.getErrorStream(),"ERROR");
//            result = new GetResultFromCMD(proc.getInputStream(),"OUTPUT");
            error.start();
            result.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



}
