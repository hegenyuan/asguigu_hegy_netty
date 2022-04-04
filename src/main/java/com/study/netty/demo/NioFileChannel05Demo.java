package com.study.netty.demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;


/**
 * buffer数组实现大文件读取
 * **/
public class NioFileChannel05Demo {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel  serverSocketChannel =  ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);
        /***绑定端口到socket****/
        serverSocketChannel.socket().bind(inetSocketAddress);

       ByteBuffer[]  byteBuffers =  new ByteBuffer[2];
       byteBuffers[0] = ByteBuffer.allocate(5);
       byteBuffers[1] = ByteBuffer.allocate(3);

       /*******等待客户端连接****/
       SocketChannel  socketChannel =  serverSocketChannel.accept();
       int  messageLength = 8;
       while(true){
           long  byteRead = 0;
           while(byteRead<messageLength){
              long length =  socketChannel.read(byteBuffers);
               byteRead = byteRead+length;
               /**打印当前buffer的position 和 limit**/
               System.out.println("byteRead=="+byteRead);
               Arrays.asList(byteBuffers).stream().map(buffer ->"position="+buffer.position()+",limit="+buffer.limit()).
                       forEach(System.out::println);
               /**将所有的buffer进行flip***/
               Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.flip());
               /*****将数据读出显示到客户端*/
               long writeByte = 0 ;
               while(writeByte<messageLength){
                   long l = socketChannel.write(byteBuffers);
                   writeByte+=l;
               }
               /********/
               Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.clear());
               System.out.println("byteRead="+byteRead+";writeByte="+writeByte+";messageLength==="+messageLength);
           }

       }







    }
}
