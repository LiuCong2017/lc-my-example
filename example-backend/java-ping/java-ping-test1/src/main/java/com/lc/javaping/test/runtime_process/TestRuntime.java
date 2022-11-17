package com.lc.javaping.test.runtime_process;

import java.io.IOException;

public class TestRuntime {

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        long free = runtime.freeMemory();
        long max = runtime.maxMemory();
//        runtime.gc();

//        Process p = null;
//        try {
//            p = runtime.exec("notepad.exe");
//            Thread.sleep(5000);
//            p.destroy();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        try {
            Runtime.getRuntime().exec("cmd /k mkdir tmp");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
