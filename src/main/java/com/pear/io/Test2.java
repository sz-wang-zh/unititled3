package com.pear.io;

import com.fasterxml.jackson.core.util.BufferRecycler;
import org.junit.Test;

import java.io.*;

public class Test2 {
    @Test
    public void ediu2() {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        File file = new File("src/step2/output");
        if (!file.exists()) {
            file.mkdir();
        }
        try{
            bis = new BufferedInputStream(new FileInputStream("src/step2/input/"));
            bos = new BufferedOutputStream(new FileOutputStream(("src/step2/output/output.txt")));

            byte[] buffer = new byte[8];
            bis.read(buffer);
            System.out.println(buffer.toString());


            bos.write("learning practice".getBytes());
        } catch (Exception e) {
                e.printStackTrace();
        } finally {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void ediu3() {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        File file = new File("src/step2/output");
        if (!file.exists()) {
            file.mkdir();
        }
        try {
            bufferedReader = new BufferedReader(new FileReader("src/step3/input/input.txt"));
            bufferedWriter = new BufferedWriter(new FileWriter("src/step3/output/out.txt"));

            char[] buffer = new char[24];
            int len;
            while (-1 != (len = bufferedReader.read(buffer))) {
                bufferedWriter.write(String.valueOf(buffer),0,len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
