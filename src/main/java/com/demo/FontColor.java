package com.demo;

/**
 * 小说字体enum
 */
public enum FontColor{

    // 小说字体 颜色选择
    GREEN("\033[32m","绿色"),
    BROWN("\033[33m", "棕色"),
    BLUE( "\033[34m"," 蓝色"),
    PURPLE( "\033[35m","紫色"),
    CYAN( "\033[36m","蓝绿色"),
    GREY( "\033[37m","灰色")
    ;

    private String color;
    private String explain;

    FontColor(String color, String explain) {
        this.color = color;
        this.explain = explain;
    }

    public String getColor() {
        return color;
    }
}