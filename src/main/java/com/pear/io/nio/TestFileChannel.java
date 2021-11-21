package com.pear.io.nio;


import org.junit.Test;

import java.io.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.FloatBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectableChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Arrays;

/**
 * FileChannel的测试
 * Channel：
 *  真正执行IO操作的对象，有write和read方法
 *  buffer本身具有读写的能力，只是用channel来控制
 *
 * buffer：
 *  是数据的缓存，即是数据的载体，有put
 *  新生成的buffer的position和limit的位置根据具体情况确定
 *
 *  在读的时候position表示开始读取的位置，limit用来表示能够读的最大位置。
 *  在写的时候position表示开始写的位置，limit表示能写的最大位置。
 *
 *
 *  读的时候，position的位置改变。其他不变
 *  写的时候，应该从缓存区的开始写入，即从0开始
 *
 *  在重复读的时候，要重置position给的位置
 *  在写的时候，要人为调整position和limit的位置
 *
 *  filp()  翻转
 */
public class TestFileChannel  {
    String fileName = "C:\\Users\\pear\\IdeaProjects\\untitled3\\src\\main\\resources\\class-info.txt";

    /**
     * 读取数据
     *  clear()不会清空缓冲区，可以实用Arrays的copyOf方法
     *  channel.read(buffer)应该是从buffer的position开始写入数据，第一次读取完数据后position的值为48，所以之后的读取都是无效的
     *  每次读取应该要将position置为0，还要将缓存区的数据清空
     *  英文字母为一个position，汉字为三个position
     */
    @Test
    public void t01() throws IOException {


        RandomAccessFile s1 = new RandomAccessFile(fileName,"r");
        FileChannel channel = s1.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(48);
        buffer.slice();
        int read = channel.read(buffer);

        System.out.println(channel.size());

        while (read!=-1) {
            byte[] array = buffer.array();
            array  = Arrays.copyOf(array, buffer.position());
            System.out.print(new String(array));
            System.out.println(buffer);

            buffer.clear();
            read = channel.read(buffer);
        }

        channel.close();
    }

    /**
     * 写入数据
     * @throws IOException
     */
    @Test
    public void t02() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(fileName,"rws");
        FileChannel channel = randomAccessFile.getChannel();

        channel.position(channel.size()+10);
        System.out.println(channel.position());

        ByteBuffer buffer = ByteBuffer.allocate(48);

        String newData = "this is ajgla";
        int length = newData.length();
        buffer.put(newData.getBytes());
        System.out.println(new String(buffer.array()));
//      buffer.clear();  buffer.limit(length);  === buffer.flip();
        buffer.flip();
        System.out.println(buffer.array());
        channel.write(buffer);

        channel.close();
    }


    @Test
    public void t03() throws IOException {
        String fileName1 = "C:\\Users\\pear\\IdeaProjects\\untitled3\\src\\main\\resources\\nio1.txt";
        String fileName2 = "C:\\Users\\pear\\IdeaProjects\\untitled3\\src\\main\\resources\\nio2.txt";

        RandomAccessFile randomAccessFile1 = new RandomAccessFile(fileName1,"rws");
        RandomAccessFile randomAccessFile2 = new RandomAccessFile(fileName2,"rws");

        FileChannel channel1 = randomAccessFile1.getChannel();
        FileChannel channel2 = randomAccessFile2.getChannel();

        channel1.transferFrom(channel2,0,channel2.size());
        channel1.transferTo(0,channel2.size(),channel2);
        channel1.close();
        channel2.close();
    }

    @Test
    public void copy() throws Exception {
        String startPath = "C:\\Users\\pear\\IdeaProjects\\untitled3\\src\\main\\resources\\nio1.txt";
        String endPath = "C:\\Users\\pear\\IdeaProjects\\untitled3\\src\\main\\resources\\nio2.txt";

        ByteBuffer buffer = ByteBuffer.allocateDirect(24);

        RandomAccessFile r1 = new RandomAccessFile(startPath, "rw");
        RandomAccessFile r2 = new RandomAccessFile(endPath, "rw");
        FileChannel channel1 = r1.getChannel();
        FileChannel channel2 = r2.getChannel();

        while (channel1.read(buffer)!=-1) {
            // 将buffer改为写模式
            buffer.flip();
            System.out.println(buffer);
            // 写入文件
            channel2.write(buffer);
            // 将buffer改为读模式
            buffer.compact();
            System.out.println(buffer);

        }
        channel1.close();
        channel2.close();
    }

    @Test
    public void charSet() throws CharacterCodingException {
        Charset charset = Charset.forName("utf-8");
        CharsetEncoder encoder = charset.newEncoder();
        CharsetDecoder decoder = charset.newDecoder();
        CharBuffer charBuffer = CharBuffer.allocate(48);

        // 写入
        charBuffer.put(new char[]{'这', '是','1','a'});
        System.out.println("写入后的char："+charBuffer.position()+" "+charBuffer.limit());
        charBuffer.flip();

        ByteBuffer byteBuffer = encoder.encode(charBuffer);

        System.out.println(byteBuffer.toString());

        // 解码，需要对缓存进行读写，只改变position的位置，缓存已经存在，所以position的位置需要改变
        CharBuffer decode = decoder.decode(byteBuffer);


        System.out.println(byteBuffer);
        System.out.println(decode);
    }

    @Test
    public void testRead() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(48);
        System.out.println(byteBuffer); // 0 48
        byteBuffer.put("this 我".getBytes());
        System.out.println(byteBuffer); // 8 48

        byteBuffer.flip();
        System.out.println(byteBuffer); // 0 8

        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        System.out.println(byteBuffer); // 8 8
        System.out.println(new String(bytes));
    }

}
