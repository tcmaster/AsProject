package com.android.tonight8.dao.entity;

/**
 * Created by LiXiaoSong
 * Date: 2015/8/26 0026
 */
public class Page {
    private long offset;//偏移量
    private int row;//行数

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public String toString() {
        return "Page{" +
                "offset=" + offset +
                ", row=" + row +
                '}';
    }
}
