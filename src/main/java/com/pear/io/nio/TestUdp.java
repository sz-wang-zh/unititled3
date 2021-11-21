package com.pear.io.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;

public class TestUdp {
    @Test
    public void sendDatagram() throws IOException, InterruptedException {
        DatagramChannel sendChannel = DatagramChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(19999);
        sendChannel.bind(inetSocketAddress);

        InetSocketAddress sendAddress = new InetSocketAddress("127.0.0.1", 9998);

        while (true) {
            System.out.println("发包端发包");
            sendChannel.send(ByteBuffer.wrap("发包".getBytes("UTF-8")), sendAddress);
            Thread.sleep(1000);
        }
    }


    @Test
    public void receive() throws IOException {
        DatagramChannel receiveChannel= DatagramChannel.open();
        InetSocketAddress receiveAddress= new InetSocketAddress(9999);
        receiveChannel.bind(receiveAddress);

        ByteBuffer receiveBuffer= ByteBuffer.allocate(512);

        while (true) {
            receiveBuffer.clear();
            SocketAddress sendAddress= receiveChannel.receive(receiveBuffer);
            receiveBuffer.flip();
            System.out.print(sendAddress.toString() + " ");
            System.out.println(Charset.forName("UTF-8").decode(receiveBuffer));
        }
    }

    @Test
    public void testConect1() throws IOException {
        DatagramChannel connChannel = DatagramChannel.open();
        connChannel.bind(new InetSocketAddress(9998));

        connChannel.connect(new InetSocketAddress("127.0.0.1", 19999));
        System.out.println(connChannel.isConnected());
//        connChannel.write(ByteBuffer.wrap("发包".getBytes("UTF-8")));

        ByteBuffer readBuffer = ByteBuffer.allocate(512);
        while (true) {
            try {
                readBuffer.clear();
                connChannel.read(readBuffer);
                readBuffer.flip();
                System.out.println(Charset.forName("UTF-8").decode(readBuffer));
            } catch (Exception e) {
            }
        }
    }
}
