package com.lc.javaping.test.pingHttpURL;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class HttpPing {

//    public static boolean pingURL(String url){
//        try {
//            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//            connection.setRequestMethod("HEAD");
//            int responseCode = connection.getResponseCode();
//                return responseCode = HttpURLConnection.HTTP_OK;
//            if (responseCode != 200){
//                return false;
//            }
//            return true;
//        } catch (IOException e) {
//            return false;
//        }
//    }
//
//    public static void main(String[] args) {
//        boolean res = pingURL("http://127.0.0.1");
//        System.out.println(res);
//    }

    /**
     * Pings a HTTP URL. This effectively sends a HEAD request and returns <code>true</code> if the response code is in
     * the 200-399 range.
     * @param url The HTTP URL to be pinged.
     * @param timeout The timeout in millis for both the connection timeout and the response read timeout. Note that
     * the total timeout is effectively two times the given timeout.
     * @return <code>true</code> if the given HTTP URL has returned response code 200-399 on a HEAD request within the
     * given timeout, otherwise <code>false</code>.
     */
    public static boolean pingURL(String url, int timeout) {
        url = url.replaceFirst("^https", "http"); // Otherwise an exception may be thrown on invalid SSL certificates.

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            return (200 <= responseCode && responseCode <= 399);
        } catch (IOException exception) {
            return false;
        }finally {
            if (connection != null){
                connection.disconnect();
            }
        }
    }

}
