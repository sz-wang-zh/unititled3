package com.pear.io.nio.chatHouse;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class demo1 {

    /**
        1、write一直是就绪的如何处理
        2、读和写怎么并行执行


     */
    // 服务器和单一客户端通过控制台通信
    @Test
    public void server1() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8888));
//        serverSocketChannel.configureBlocking(false);
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        socketChannel.register(selector,  SelectionKey.OP_WRITE);

        while (true) {
            // 寻找可用通道
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            for (SelectionKey key : keys) {
                if (key.isWritable()) {
                    ByteBuffer buffer = ByteBuffer.allocate(48);
                    SocketChannel ke = (SocketChannel) key.channel();
                    ke.read(buffer);
                    System.out.println("数据："+new String(buffer.array()));
                }
            }

        }
    }
    // socketChannel.read(buffer)默认是阻塞的, socketChannel.configureBlocking(false)后不是阻塞的。
    @Test
    public void client1() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1",8888));
        System.out.println("连接成功");

        ByteBuffer buffer = ByteBuffer.allocate(48);
        socketChannel.configureBlocking(false);
        socketChannel.read(buffer);
        System.out.println("数据："+new String(buffer.array()));
        socketChannel.close();

    }

}
