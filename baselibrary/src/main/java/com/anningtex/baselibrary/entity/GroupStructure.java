package com.anningtex.baselibrary.entity;

/**
 * @ClassName: GroupStructure
 * @Description: java类作用描述
 * @Author: alvis
 * @CreateDate: 2019/11/12 14:03
 */
public class GroupStructure {
    private boolean hasHeader;
    private boolean hasFooter;
    private int childrenCount;

    public GroupStructure(boolean hasHeader, boolean hasFooter, int childrenCount) {
        this.hasHeader = hasHeader;
        this.hasFooter = hasFooter;
        this.childrenCount = childrenCount;
    }

    public boolean hasHeader() {
        return hasHeader;
    }

    public void setHasHeader(boolean hasHeader) {
        this.hasHeader = hasHeader;
    }

    public boolean hasFooter() {
        return hasFooter;
    }

    public void setHasFooter(boolean hasFooter) {
        this.hasFooter = hasFooter;
    }

    public int getChildrenCount() {
        return childrenCount;
    }

    public void setChildrenCount(int childrenCount) {
        this.childrenCount = childrenCount;
    }
}
