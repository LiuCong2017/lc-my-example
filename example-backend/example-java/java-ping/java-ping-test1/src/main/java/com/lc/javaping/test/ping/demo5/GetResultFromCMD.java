package com.lc.javaping.test.ping.demo5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GetResultFromCMD extends Thread{
    InputStream inputStream = null;

    // This abstract class is the superclass of all classes representing an input stream of bytes.
    private GetResultFromCMD(InputStream is, String type) {
        this.inputStream = is;
    }

    public static GetResultFromCMD getResultFromCMD(InputStream inputStream,String type){
        return new GetResultFromCMD(inputStream,type);
    }

    @Override
    public void run(){
        String str = null;
        try {
            // Reads text from a character-input stream, buffering characters so as to provide for the efficient reading of characters, arrays, and lines.
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            while ((str = br.readLine()) != null){
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
