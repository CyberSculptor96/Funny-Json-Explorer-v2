package com.fje.strategy;

import com.fje.composite.Container;
import com.fje.composite.Leaf;
import java.util.List;

/**
 * DrawStrategy 接口使用了策略模式
 * 定义了绘制不同组件的方法
 */
public interface DrawStrategy {
    void drawContainer(Container container, int level, boolean isFirst, boolean isLast, List<Boolean> parentIsLast);

    void drawLeaf(Leaf leaf, int level, boolean isFirst, boolean isLast, List<Boolean> parentIsLast);
}
