package com.anningtex.baselibrary.entity;

/**
 * @ClassName: SectionEntity
 * @Description: java类作用描述
 * @Author: alvis
 * @CreateDate: 2020/6/8 17:42
 */
public abstract class SectionEntity<T> {
    public boolean isExpand;
    public T t;
    public String header;


    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public SectionEntity(String header) {
        this.header = header;
        this.t = null;
    }

}
