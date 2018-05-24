package com.bbear.example.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author junxiongchen
 * @date 2018/05/10
 */
public class SocketServer implements Runnable {
    private Selector selector ;
    private ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    public SocketServer(int port) {
        try {
            //打开复用器
            selector = Selector.open();
            //打开服务器通道
            ServerSocketChannel ssc = ServerSocketChannel.open();
            //设置服务器通道为非阻塞
            ssc.configureBlocking(false);
            //绑定地址
            ssc.bind(new InetSocketAddress(port));
            //把通道注册到多路复用器上，并且进行监听
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("server start port" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                //让复用器开始监听
                selector.select();
                //返回所有注册到复用器上的通道
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    //key是有效的
                    if (key.isValid()) {
                        //key是阻塞状态
                        if (key.isAcceptable()) {
                            accept(key);
                        }
                        //key 是可读状态
                        if (key.isReadable()) {
                            read(key);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void accept(SelectionKey key) {
        //获取服务器通道
        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
        try {
            //执行阻塞方法
            SocketChannel accept = channel.accept();
            //设置阻塞模式是非阻塞
            accept.configureBlocking(false);
            //注册到复用器，并进行读取
            accept.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read(SelectionKey key) {
        //清空缓存区
        byteBuffer.clear();
        //获取服务器通道
        SocketChannel sc = (SocketChannel) key.channel();
        try {
            //读取数据
            int read = sc.read(byteBuffer);
            if (read == -1) {
                //没有数据
                key.channel().close();
                key.cancel();
                return;
            }
            //读取到数据将buffer的poisition复位到0
            byteBuffer.flip();
            byte[] bytes = new byte[byteBuffer.remaining()];
            //将buffer的数据写到bytes中
            byteBuffer.get(bytes);
            System.out.println("socket read byte:" + new String(bytes).trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(new SocketServer(8379)).start();
    }
}
