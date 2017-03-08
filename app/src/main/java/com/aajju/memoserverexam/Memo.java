package com.aajju.memoserverexam;

import java.io.Serializable;

/**
 * Created by aajju on 2017-03-02.
 */

public class Memo implements Serializable {
    private int id;
    private String title;
    private String contents;
    private long time;

    public Memo(int id, String title, String contents) {
        this.id = id;
        this.title = title;
        this.contents = contents;
    }

    public Memo(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Memo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", time=" + time +
                '}';
    }
}
