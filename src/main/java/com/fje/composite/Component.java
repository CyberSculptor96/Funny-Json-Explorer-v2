package com.fje.composite;

import java.util.List;
import com.fje.iterator.MyIterable;
import com.fje.iterator.MyIterator;
import com.fje.strategy.DrawContext;

/**
 * Component 接口使用了组合模式和迭代器模式
 * 该接口定义了 draw 方法，用于绘制组件，以及创建组件迭代器的 createIterator 方法
 */
public interface Component extends MyIterable<Component> {
    void draw(int level, boolean isFirst, boolean isLast, List<Boolean> parentIsLast, DrawContext drawContext);

    MyIterator<Component> createIterator();
}