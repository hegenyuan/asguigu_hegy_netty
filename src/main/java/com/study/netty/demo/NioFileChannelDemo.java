package com.study.netty.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;


/**
 * 文件copy
 * **/
public class NioFileChannelDemo {

    public static void main(String[] args) throws IOException {
        FileInputStream  inputStream =  new FileInputStream("D:\\IDE_study_workspace\\netty\\1.txt");
        FileOutputStream  outputStream =  new FileOutputStream("D:\\IDE_study_workspace\\netty\\2.txt");


         //获取各个流对应的filechannel
        FileChannel soureCh =  inputStream.getChannel();
        FileChannel destCh =  outputStream.getChannel();

        //使用transeferFrom完成copy
         destCh.transferFrom(soureCh,0,soureCh.size());

         soureCh.close();
         destCh.close();
         inputStream.close();
         outputStream.close();



    }

}
