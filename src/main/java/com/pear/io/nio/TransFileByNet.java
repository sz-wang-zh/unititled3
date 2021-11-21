package com.pear.io.nio;

import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TransFileByNet {
    // 服务端传输文件给客户端


    @Test
    public void server() throws IOException {
        Path path = Paths.get("C:\\Users\\pear\\IdeaProjects\\untitled3\\src\\main\\resources\\nio1.txt");

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8888));
        SocketChannel channel = serverSocketChannel.accept();

        RandomAccessFile randomAccessFile = new RandomAccessFile("C:\\Users\\pear\\IdeaProjects\\untitled3\\src\\main\\resources\\nio1.txt","rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        System.out.println("准备传输数据");

        ByteBuffer buffer = ByteBuffer.allocate(48);
        // 先读入缓存区，在写入
        int i = 0;
        while(fileChannel.read(buffer) != -1) {
            System.out.println("第"+(++i)+"读取");
            System.out.println(new String(buffer.array()));
            System.out.println("第"+(i)+"读取成功");
            buffer.flip();
            channel.write(buffer);
            System.out.println("第"+(i)+"写入成功");
            buffer.clear();
        }

        serverSocketChannel.close();
        fileChannel.close();
    }

    @Test
    public void client() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(8888));
        System.out.println("客户端已连接");
        ByteBuffer buffer = ByteBuffer.allocate(48);

        RandomAccessFile randomAccessFile = new RandomAccessFile("C:\\Users\\pear\\IdeaProjects\\untitled3\\src\\main\\resources\\nio2.txt","rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        while (socketChannel.read(buffer) != -1) {
            buffer.flip();
            fileChannel.write(buffer);
            buffer.clear();
        }
        socketChannel.close();
    }

}
