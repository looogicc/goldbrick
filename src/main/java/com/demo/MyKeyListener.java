package com.demo;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

//监听键盘类
public class MyKeyListener extends KeyAdapter  {

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == GoldbrickMain.nextLine){
            System.out.print(".");
            try {
                GoldbrickMain.readLineVarFile(); //读取指定行的内容
            } catch (IOException e) {
                e.printStackTrace();
            }
            GoldbrickMain.writeLineNo(); // 写入行号
            GoldbrickMain.lineNumber ++;
        }else {
            System.exit(0);
        }
    }

}