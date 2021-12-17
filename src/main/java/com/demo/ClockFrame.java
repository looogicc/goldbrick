package com.demo;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClockFrame extends Frame implements Runnable{

    boolean flag = false;

    Thread clock; 
    public ClockFrame(){
        setTitle("⏰");
        setFont(new Font("TimesRoman",Font.BOLD,60)); // 显示调用时钟的字体
        start();
        setSize(310,100);// 设置界面大小
        setVisible(true); // 窗口可视
        addKeyListener(new MyKeyListener());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void start(){ //开始
        if(clock == null){
            clock = new Thread(this); // 实例化进程
            // jdk提供 一旦调用start方法，则会通过JVM找到run()方法
            clock.start(); // 开始进程
        }
    }

    // 运行进程
    public void run() { 
        while(clock != null){
            repaint(); // 重绘界面
            try {
                Thread.sleep(500); // 线程暂停500毫秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g){ // 重写组件的paint方法
        Date now = new Date(); // 提供日历的系统
        String timeInfo ;
        if(flag){
            timeInfo = new SimpleDateFormat("HH:mm:ss").format(now);
        }else{
            timeInfo = new SimpleDateFormat("HH.mm.ss").format(now);
        }
        flag = !flag;
        g.setColor(Color.DARK_GRAY); // 设置颜色
        Dimension dim = getSize(); // 得到窗口大小
        g.fillRect(0, 0, dim.width, dim.height);
        g.setColor(Color.PINK); // 时钟文字颜色
        g.setXORMode(Color.DARK_GRAY);
        g.drawString(timeInfo, 20, 80);
    }

}
