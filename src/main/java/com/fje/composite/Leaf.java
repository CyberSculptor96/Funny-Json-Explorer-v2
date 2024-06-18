package com.fje.composite;

import java.util.List;

import com.fje.Icon.Icon;
import com.fje.iterator.MyIterator;
import com.fje.strategy.DrawContext;

/**
 * Leaf 类使用了组合设计模式
 * 该类表示一个叶子节点
 */
public class Leaf implements Component {
    private String name;
    private String value;
    private Icon icon;

    public Leaf(String name, String value, Icon icon) {
        this.name = name;
        this.value = value;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public Icon getIcon() {
        return icon;
    }

    @Override
    public MyIterator<Component> createIterator() {
        return new MyIterator<Component>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Component getNext() {
                return null;
            }
        };
    }

    @Override
    public void draw(int level, boolean isFirst, boolean isLast, List<Boolean> parentIsLast, DrawContext drawContext) {
        drawContext.draw(this, level, isFirst, isLast, parentIsLast);
    }
}