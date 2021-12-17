package com.demo;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    public MyFrame(){
        this.setSize(500,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Goldbrick");
        this.setVisible(true);
        this.addKeyListener(new MyKeyListener());
        this.getContentPane().setBackground(Color.DARK_GRAY);
    }
}
