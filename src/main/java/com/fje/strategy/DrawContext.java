package com.fje.strategy;

import com.fje.composite.Component;
import com.fje.composite.Container;
import com.fje.composite.Leaf;
import java.util.List;

/**
 * DrawContext 类使用了策略模式
 * 通过设置不同的绘制策略来绘制组件
 */
public class DrawContext {
    private DrawStrategy strategy;

    public void setStrategy(DrawStrategy strategy) {
        this.strategy = strategy;
    }

    public void draw(Component component, int level, boolean isFirst, boolean isLast, List<Boolean> parentIsLast) {
        if (component instanceof Container) {
            strategy.drawContainer((Container) component, level, isFirst, isLast, parentIsLast);
        } else if (component instanceof Leaf) {
            strategy.drawLeaf((Leaf) component, level, isFirst, isLast, parentIsLast);
        }
    }
}
