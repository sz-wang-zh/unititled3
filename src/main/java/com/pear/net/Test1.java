package com.pear.net;

import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Test1 {
    @Test
    public void client() {
        try (Socket socket = new Socket("localhost", 8888);
             OutputStream os = socket.getOutputStream();
             FileInputStream fis = new FileInputStream("E:\\test\\女神.jpg");
        ) {
            byte[] bt = new byte[1024];
            int len = 0;
            while ((len = fis.read(bt)) != -1) {
                os.write(bt,0,len);
            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void server() {
        try ( ServerSocket serverSocket = new ServerSocket(8888);) {
            try (Socket accept = serverSocket.accept()) {

                InputStream inputStream = accept.getInputStream();
                try (FileOutputStream fileOutputStream = new FileOutputStream("E:\\test\\女神2.jpg")) {
                    byte[] bytes = new byte[1024];
                    int len;
                    while ((len = inputStream.read(bytes)) != -1) {
                        fileOutputStream.write(bytes,0,len);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
