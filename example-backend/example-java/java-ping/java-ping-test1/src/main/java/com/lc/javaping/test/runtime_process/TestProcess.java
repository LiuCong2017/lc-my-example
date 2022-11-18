package com.lc.javaping.test.runtime_process;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TestProcess {
    public static void main(String[] args) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("notepad.exe");
        Process notepad = pb.start();

        notepad.waitFor();
        InputStream is = notepad.getInputStream();
        is = notepad.getErrorStream();
        OutputStream os = notepad.getOutputStream();

//        Process notepad = null; //创建一个Process的实例，用于接受exec（）返回的进程。
//        try {
//            notepad = Runtime.getRuntime().exec("notepad.exe"); //接受返回的进程
//            Thread.sleep(5000);                                 //休眠5s
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        notepad.destroy();    //结束notepad进程。
    }
}
