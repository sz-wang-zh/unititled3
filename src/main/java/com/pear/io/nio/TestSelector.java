package com.pear.io.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.Iterator;
import java.util.Set;

public class TestSelector {

    @Test
    public void testReady1() throws IOException {
            Selector selector = Selector.open();
// 274064559
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(18888));
        System.out.println(serverSocketChannel.hashCode());
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);

        ByteBuffer rBuffer = ByteBuffer.allocate(48);

        //  连接，并设置它能读能写,1、连接了就能读能写的就绪，2、连接了需要准备了数据才就绪  3、每次接收要连接?
        // 可接收的key可以连接后连接，依然在selectKeys中，状态还是可连接，因此需要改变状态。
        // 在客户端tcp关闭时，ServerSocket会有读，写，读的状态改变，对应tcp的断开过程

        /**
         *  1、每次搜索到就绪通道后，会将它加入到selectedKeys集合中（可以调用selectedKeys()方法获取）,使用后并不会在集合中移除该通道，
         * 因此我们每次select后应该人为移除该通道。
         *  2、在客户端主动关闭连接时，ServerSocket会有读，写，读的状态改变，对应tcp的断开过程？ 错
         *  3、选择器可以检测到通道的状态，以此通过选择器来操作通道
         *  4、写通道生成后，只要它不再进行写操作，它就是就绪的。
         */

        while (true) {
            System.out.println("本次找到key的数量："+selector.select(100));
            Set<SelectionKey> keys = selector.selectedKeys();
            System.out.println("真正的数量："+ keys.size());
            for(SelectionKey key : keys) {
                System.out.println("key的hash："+key.hashCode());
                keys.remove(key);
                if (key.isAcceptable()){
                    // 第三次迭代中没有连接请求
                    System.out.println("连接");
                    ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel) key.channel();
                    System.out.println("xuanzeqi"+serverSocketChannel1.hashCode());
                    SocketChannel accept = serverSocketChannel.accept(); // 似乎会回收已连接的socket，不是回收，而是错误的从集合取出了元素
                    accept.configureBlocking(false);
                    accept.register(selector,SelectionKey.OP_READ);
                    System.out.println("已连接");
                }
                if (key.isReadable()){
                    System.out.println(key.hashCode()+" 能读");
                    SocketChannel channel = (SocketChannel)key.channel();
                    channel.read(rBuffer);
                    System.out.println(new String(rBuffer.array()));
                    channel.configureBlocking(false);
                    channel.register(selector,SelectionKey.OP_WRITE);
                }
                if (key.isWritable()) {
                    System.out.println(key.hashCode()+" 能写");
                    SocketChannel channel = (SocketChannel)key.channel();
                    channel.write(ByteBuffer.wrap("发送给客户端".getBytes()));
                    channel.configureBlocking(false);
                    channel.register(selector,SelectionKey.OP_READ);
                }
                System.out.println("xiaci ");
                System.out.println();
                System.out.println("一次循环后key的数量:"+ keys.size());
            }
        }
    }

    @Test
    public void testReady2() throws IOException, InterruptedException {
        SocketChannel socketChannel = SocketChannel.open();

        socketChannel.connect(new InetSocketAddress("127.0.0.1",18888));
        System.out.println("连接成功");

        socketChannel.write(ByteBuffer.wrap("发送".getBytes()));

        ByteBuffer buffer = ByteBuffer.allocate(48);
        socketChannel.read(buffer);
        System.out.println(new String(buffer.array()));

        Thread.sleep(1000);
        System.out.println(socketChannel.isConnected());
        System.out.println("连接断开");
        socketChannel.close();
    }


    /**
     * Selector不会检测通道的连接状态，即使通道断开
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testReady3() throws IOException, InterruptedException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8888));
        SocketChannel socketChannel = serverSocketChannel.accept();
        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector,SelectionKey.OP_READ | SelectionKey.OP_WRITE);


        ByteBuffer buffer = ByteBuffer.allocate(48);
        while (true) {
            System.out.println("本次找到的key："+selector.select());
            Thread.sleep(30);
            Set<SelectionKey> keys = selector.selectedKeys();

            System.out.println("集合中keys："+ keys.size());

            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isReadable()) {
                    System.out.println("准备读");
                    SocketChannel channel = (SocketChannel) key.channel();
                    System.out.println("buffer的长度："+channel.read(buffer));
                }
                if (key.isWritable()) {
                    System.out.println("准备写");
                }
                iterator.remove();
            }
            System.out.println("本次的keys："+keys.size()+"下次循环");
            System.out.println();
        }

    }

    @Test
    public void testReady4() throws IOException, InterruptedException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1",8888));
        Thread.sleep(10000);
        socketChannel.close();
    }



    @Test
    public void test01() throws IOException {
        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(InetAddress.getByName("127.0.0.1"),8000));
        serverSocketChannel.configureBlocking(false);

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        socketChannel.register(selector,SelectionKey.OP_WRITE);
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);

        System.out.println(selector.select(100));
//        while (true) {
//            int i = selector.select(10000);
//
//            System.out.println(i);
//
//            Set<SelectionKey> keys = selector.selectedKeys();
//            for (SelectionKey selectionKey : keys) {
//                if (selectionKey.isAcceptable()){
//                    ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel) selectionKey.channel();
//                    serverSocketChannel1.accept();
//                    serverSocketChannel1.configureBlocking(false);
//
//                }
//
//                if (selectionKey.isReadable()) {
//                    SelectableChannel channel = selectionKey.channel();
//                }
//            }
//            break;
//        }

        selector.close();
        serverSocketChannel.close();
        socketChannel.close();
    }

    @Test
    public void test02()throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1",8000));

        socketChannel.write(ByteBuffer.wrap("我是客户端".getBytes()));
        ByteBuffer buffer = ByteBuffer.allocate(48);
        socketChannel.read(buffer);
        System.out.println(new String(buffer.array()));
        socketChannel.close();
    }


    /**
     * 使用后的channel需要再次注册吗？
     * 可写的channel是一直就绪的吗？不是，是连接了就一直可写？
     * select只扫描已经就绪的socket，注册了socket不代表已经就绪了
     * 通过查看哈希码，这几个socket都是同一个对象
     可以设置两个socket，双方连接，查看读写是否就绪

        第一次select，客户端发送连接请求，导致一个accept的serverSocket就绪，从这个socket中获取一个socket，并将它设为对read干训过去
        第二次select，客户端发送信息，导致一个read的socket就绪，从这个socket中获取数据，并将他设为对write感兴趣
        第三次selecy，服务端发送信息，导致一个write的socket就绪，从这个socket中写入数据，并将他设为对write感兴趣

     1  1   accept的socket就绪
     java.nio.channels.SocketChannel[connected local=/127.0.0.1:8000 remote=/127.0.0.1:52778]  1
     2  1   read的socket就绪
     java.nio.channels.SocketChannel[connected local=/127.0.0.1:8000 remote=/127.0.0.1:52778]  2
     received : 我是客户端
     3  1   write的socket就绪
     java.nio.channels.SocketChannel[connected local=/127.0.0.1:8000 remote=/127.0.0.1:52778]  3
     4  1   read的socket就绪
     java.nio.channels.SocketChannel[connected local=/127.0.0.1:8000 remote=/127.0.0.1:52778]  2
     java.io.IOException: 你的主机中的软件中止了一个已建立的连接。
     */

    @Test
    public void ServerDemo() {
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress("127.0.0.1", 8000));
            ssc.configureBlocking(false);
            Selector selector = Selector.open();
            // 注册 channel，并且指定感兴趣的事件是 Accept
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            ByteBuffer readBuff = ByteBuffer.allocate(1024);
            ByteBuffer writeBuff = ByteBuffer.allocate(128);
            writeBuff.put("received".getBytes());
            writeBuff.flip();
            int i= 0;
            while (true) {
                int nReady = selector.select();

                System.out.println(++i+"  "+nReady);

                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();
                    if (key.isAcceptable()) {
                        // 创建新的连接，并且把连接注册到 selector 上，而且，
                        // 声明这个 channel 只对读操作感兴趣。
                        SocketChannel socketChannel = ssc.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);

                        System.out.println(socketChannel.hashCode()+"  1");
                    }
                    else if (key.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) key.channel();

                        System.out.println(socketChannel.hashCode()+"  2");
                        readBuff.clear();
                        socketChannel.read(readBuff);
                        readBuff.flip();
                        System.out.println("received: " + new String(readBuff.array()));
                        key.interestOps(SelectionKey.OP_WRITE);
                    }
                    else if (key.isWritable()) {
                        writeBuff.rewind();
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        System.out.println(socketChannel.hashCode()+"  3");
                        socketChannel.write(writeBuff);
                        key.interestOps(SelectionKey.OP_READ);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test05() throws IOException, InterruptedException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8889));
        SocketChannel socketChannel = serverSocketChannel.accept();
        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector,SelectionKey.OP_READ | SelectionKey.OP_WRITE);

        System.out.println("开始");

        while (true) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isReadable()) {
                    System.out.println("可读");
                }
                if (key.isWritable()) {
                    System.out.println("可写");
                    Thread.sleep(100);
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(48);
                    System.out.println("准备读");
                    int read = channel.read(buffer);
                    System.out.println(read+" "+new String(buffer.array()));

                }
                System.out.println();
            }
        }
    }

    @Test
    public void testReady6() throws IOException, InterruptedException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1",8889));
        socketChannel.write(ByteBuffer.wrap("第一次的信息".getBytes()));
        Thread.sleep(3000);
        socketChannel.write(ByteBuffer.wrap("第2次的信息".getBytes()));
        Thread.sleep(10);
        socketChannel.write(ByteBuffer.wrap("第3次的信息".getBytes()));
        socketChannel.close();
    }

    @Test
    public void test07() throws IOException, InterruptedException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8889));
        SocketChannel socketChannel = serverSocketChannel.accept();
        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector,SelectionKey.OP_READ);

        ByteBuffer buffer = ByteBuffer.allocate(48);
        while (true) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    buffer.clear();
                    int read = channel.read(buffer);
                    if (read == -1){
                        key.cancel();
                    }
                    buffer.flip();
                    System.out.println(new String(buffer.array(),buffer.position(),buffer.limit()));
                    buffer.clear();
                    Thread.sleep(100);
                }
                System.out.println();

            }
        }
    }


    @Test
    public void testConnCli() throws IOException, InterruptedException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1",10001));
        Thread.sleep(3000);
        socketChannel.close();
    }

    @Test
    public void testConnServ() throws IOException, InterruptedException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(10001));

        SocketChannel socketChannel = serverSocketChannel.accept();
        while (true){
            System.out.println(socketChannel.isConnected());
            Thread.sleep(1000);
        }
    }
}
