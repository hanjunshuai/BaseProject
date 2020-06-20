package com.anningtex.baseproject.entity;

import com.anningtex.baselibrary.entity.SectionEntity;

import java.util.List;

/**
 * @ClassName: GroupEntity
 * @Description: java类作用描述
 * @Author: alvis
 * @CreateDate: 2020/6/20 17:06
 */
public class GroupEntity extends SectionEntity<List<GroupEntity.Child>> {

    public GroupEntity(String header) {
        super(header);
    }

    public static class Child {
        private String childName;

        public String getChildName() {
            return childName;
        }

        public void setChildName(String childName) {
            this.childName = childName;
        }
    }
}
