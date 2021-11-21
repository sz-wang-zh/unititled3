package com.pear.io;

import org.junit.Test;

import java.io.*;

public class TestIO {
    @Test
    public void test01() {
        
        File file = new File("src\\main\\resources\\Time_Table.txt");
//        File file = new File("C:\\Users\\pear\\IdeaProjects\\untitled3\\src\\main\\resources\\Time_Table.txt");
        try {
            FileReader fileReader = new FileReader(file);
            int data = fileReader.read();
            while (data != -1) {
                System.out.print((char) data);
                data = fileReader.read();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test02(){
        File file1=new File("11.txt");
        try {
            if (file1.createNewFile())
            {
                System.out.println("创建成功");
            }
        } catch (IOException e) {
                e.printStackTrace();
            }
    }

    /**
     *  复制文件
     */
    @Test
    public void test03() throws Exception{
        try(BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("E:\\test\\新建文本文档.txt"));
            BufferedOutputStream buff = new BufferedOutputStream(new FileOutputStream("E:\\test\\a.txt")) )
        {
            byte[] buffer = new byte[2];
            int len;
            while ((len = bufferedInputStream.read(buffer))!=-1) {
                buff.write(buffer,0,len);
            }
            buff.write("循环外的写入".getBytes());
            buff.flush();            buff.write(100);

       } catch (Exception e){

       }

    }

    /**
     * 删除某一路径下所有符合规则的文件或文件夹
     */
    @Test
    public void test04() {
        File file = new File("E:\\Program Files\\JiJiDown\\Download\\【编程不良人】2020最新版Shiro教程,整合SpringBoot项目实战教程");
            File[] files = file.listFiles();
            for (File f: files) {
                if (!f.getName().endsWith("mp4")) {
                    f.delete();
                }
            }
        }


}
