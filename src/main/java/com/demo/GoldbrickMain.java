package com.demo;

import java.awt.event.KeyEvent;
import java.io.*;
import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * 启动类 ｜ 控制台看小说
 *
 * 1、可设置小说文字颜色（idea）
 * 2、配置自动阅读和换行速度
 * 3、保存阅读进度 前情回顾
 * 4、时钟组件 手动阅读模式自动打开 鼠标选中时钟窗口触发键盘监听
 * 4、手动模式自定义按键下一行(默认键盘右键)
 *
 * @author luzhuang
 * @date 2021/12/14 1:22 下午
 */
public class GoldbrickMain {

    // 小说配置
    private static final String FILE_NAME = "src/main/resources/txt/斗破苍穹.txt"; // 小说路径
    private static final FontColor COLOR = FontColor.GREY; // 字体颜色
    private static final double NEXT_WAIT = 2.5; // 自动下一行时间 单位:秒
    private static final Boolean AUTO_READ = false; // 是否开启 自动阅读 不开启会打开时钟小组件
    private static final Integer PREVIOUS = 6; // 前情回顾 续看时回看行数(包括空行)
    static int nextLine = KeyEvent.VK_RIGHT; // 下一行 默认右键
    static int lineNumber = getReadLineNo(); // 获取已读行数

    /**
     * 启动类
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
         // 读取已读行号
        if(AUTO_READ){
            while(true) {
                try {
                    Thread.sleep((long)(NEXT_WAIT * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                readLineVarFile(); //读取指定行的内容
                writeLineNo(); // 写入行号
                lineNumber ++;
            }
        }else {
            new ClockFrame();
        }
    }


    /**
     * 输出本行内容及字符数
     * @throws IOException
     */
    public static void readLineVarFile() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_NAME))); //使用缓冲区的方法将数据读入到缓冲区中
        String line = reader.readLine(); // 定义行数
        if (lineNumber <= 0 || lineNumber > getTotalLines(FILE_NAME)) {
            throw new RuntimeException("不在文件的行数范围之内");
        }
        int num = 0;
        while (line != null) { // 当行数不为空时，输出该行内容及字符数
            if ( lineNumber == ++num && line.length() > 1 ) {
                System.out.println(COLOR.getColor() + line); // 设置控制台文字颜色
            }
            line = reader.readLine();
        }
        reader.close();
    }

    /**
     * 文件内容的总行数
     * @param fileName 文件路径
     * @return
     * @throws IOException
     */
    private static int getTotalLines(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName))); // 使用缓冲区的方法将数据读入到缓冲区中
        LineNumberReader reader = new LineNumberReader(br);
        String s = reader.readLine(); // 定义行数
        int lines = 0;
        while (s != null) {
            lines++;
            s = reader.readLine();
        }
        reader.close();
        br.close();
        return lines; // 返回行数
    }

    /**
     * 获取 已读行数
     * @return
     */
    private static int getReadLineNo(){
        int line ;
        String name = FILE_NAME.replace(".txt",".line");
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(name)));
            String strLine = reader.readLine();
            line = Integer.valueOf(strLine);
        }catch (IOException e){
            line = 0;
        }
        return line;

    }

    /**
     * 写入 已读行数
     */
    public static void writeLineNo(){
        int line = lineNumber - PREVIOUS >= 0 ? lineNumber - PREVIOUS : lineNumber; // 前情回顾
        String name = FILE_NAME.replace(".txt",".line");
        try( PrintStream stream = new PrintStream(name)){
            stream.print(line); // 写入行数
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断是否为 debug 模式
     */
    private static boolean isDebug(){
        List<String> arguments = ManagementFactory.getRuntimeMXBean().getInputArguments();
        boolean isDebug = false;
        for (String str : arguments) {
            if (str.startsWith("-agentlib")) {
                isDebug = true;
            }
        }
        return isDebug;
    }

}


