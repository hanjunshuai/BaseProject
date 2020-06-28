package com.anningtex.baselibrary.entity

/**
 * @ClassName: SectionEntity
 * @Description: java类作用描述
 * @Author: alvis
 * @CreateDate: 2020/6/8 17:42
 */
abstract class SectionEntity<T>(var header: String) {
    var isExpand = false
    var t: T? = null

}