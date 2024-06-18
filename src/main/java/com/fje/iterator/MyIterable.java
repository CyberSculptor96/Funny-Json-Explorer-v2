package com.fje.iterator;

/**
 * MyIterable 接口使用了迭代器模式
 * 定义了创建迭代器的方法
 */
public interface MyIterable<T> {
    MyIterator<T> createIterator();
}
