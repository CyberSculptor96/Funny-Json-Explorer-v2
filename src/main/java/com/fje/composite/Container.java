package com.fje.composite;

import java.util.ArrayList;
import java.util.List;

import com.fje.Icon.Icon;
import com.fje.iterator.MyIterator;
import com.fje.iterator.ComponentIterator;
import com.fje.strategy.DrawContext;

/**
 * Container 类使用了组合模式和迭代器模式
 * 该类表示一个容器节点，可以包含子组件，在绘制逻辑中使用迭代器对子节点进行遍历
 */
public class Container implements Component {
    private List<Component> children = new ArrayList<>();
    private String name;
    private int level;
    private Icon icon;

    public Container(String name, int level, Icon icon) {
        this.name = name;
        this.level = level;
        this.icon = icon;
    }

    public void add(Component child) {
        this.children.add(child);
    }

    public List<Component> getChildren() {
        return children;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public Icon getIcon() {
        return icon;
    }

    @Override
    public MyIterator<Component> createIterator() {
        return new ComponentIterator(children);
    }

    @Override
    public void draw(int level, boolean isFirst, boolean isLast, List<Boolean> parentIsLast, DrawContext drawContext) {
        drawContext.draw(this, level, isFirst, isLast, parentIsLast);
        parentIsLast.add(isLast);
        for (MyIterator<Component> it = createIterator(); it.hasNext();) {
            Component child = it.getNext();
            child.draw(level + 1, false, !it.hasNext(), parentIsLast, drawContext);
        }
        parentIsLast.remove(parentIsLast.size() - 1);
    }
}