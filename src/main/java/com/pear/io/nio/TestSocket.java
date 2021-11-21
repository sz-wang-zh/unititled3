package com.pear.io.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 *
 */
public class TestSocket {


    @Test
    public void server() throws IOException, InterruptedException {

        String GREETING = "Hello java nio.\r\n";
        ByteBuffer buffer = ByteBuffer.wrap(GREETING.getBytes());

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8888));
        serverSocketChannel.configureBlocking(false);
        while (true) {
            System.out.println("Waiting for connections");
            SocketChannel sc = serverSocketChannel.accept();
            if (sc == null) {
                System.out.println("null");
                Thread.sleep(2000);
            } else {
                System.out.println("Incoming connection from: " +
                        sc.socket().getRemoteSocketAddress());
                buffer.clear();
                sc.write(buffer);
                sc.close();
                break;
            }
        }
    }

    @Test
    public void client() throws IOException, InterruptedException {
        SocketChannel socketChannel = SocketChannel.open();
//        socketChannel.bind(new InetSocketAddress(8889));
        socketChannel.connect(new InetSocketAddress("127.0.0.1",8888));
        System.out.println(socketChannel.validOps());
        ByteBuffer byteBuffer = ByteBuffer.allocate(48);
        socketChannel.read(byteBuffer);

        System.out.println(new String(byteBuffer.array()));
        System.out.println(socketChannel.isConnected());
        socketChannel.close();
    }


    @Test
    public void test01() throws IOException {
        Socket socket = new Socket("127.0.0.1",9880);
        System.out.println(InetAddress.getLocalHost());
//        SocketChannel channel = socket.getChannel();
//
//        channel.close();
//        socket.close();
    }


}
