package com.lc.javaping.test.runtime_process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class RuntimeAndProcess {
    public static void main(String[] args) {
        try {
            Runtime run = Runtime.getRuntime();
            Process process = run.exec("netstat -na");
            InputStream in = process.getInputStream();
//            int data;
//            while ((data=in.read())!=-1){
//                System.out.print((char) data);
//            }
            String str = null;
            BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            while ((str=br.readLine())!=null){
                System.out.println(str);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
