package com.study.netty.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


/**
 * 文件copy
 * **/
public class NioFileChannel03Demo {

    public static void main(String[] args) throws IOException {
        FileInputStream  inputStream =  new FileInputStream("D:\\IDE_study_workspace\\netty\\1.txt");
        FileChannel fileChannel01 = inputStream.getChannel();

        FileOutputStream  outputStream =  new FileOutputStream("D:\\IDE_study_workspace\\netty\\3.txt");
        FileChannel fileChannel02 =  outputStream.getChannel();
        System.out.println(fileChannel02);

        ByteBuffer  byteBuffer =  ByteBuffer.allocate(512);
        while(true){
           byteBuffer.clear();
           int read =  fileChannel01.read(byteBuffer);
            System.out.println("read="+read);
           if (read==-1){
               break;
           }
           /**将buffer中的数据写入目标文件中*/
            byteBuffer.flip();
            fileChannel02.write(byteBuffer);
        }

        inputStream.close();
        outputStream.close();
    }
}
