package com.fje.iterator;

/**
 * MyIterator 接口使用了迭代器模式
 * 定义了遍历集合的基本操作
 */
public interface MyIterator<T> {
    boolean hasNext();

    T getNext();
}
