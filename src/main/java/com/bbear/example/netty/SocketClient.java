package com.bbear.example.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author junxiongchen
 * @date 2018/05/10
 */
public class SocketClient {
    public static void main(String[] args) {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 8379);
        SocketChannel sc = null;
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            //通道打开
            sc = SocketChannel.open();
            //建立连接
            sc.connect(inetSocketAddress);
            while (true) {
                byte[] bytes = new byte[1024];
                System.in.read(bytes);
                buffer.put(bytes);
                buffer.flip();
                //将buffer写入到通道中
                sc.write(buffer);
                //清空缓存
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (sc!=null) {
                try {
                    sc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
