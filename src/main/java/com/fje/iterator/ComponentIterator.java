package com.fje.iterator;

import com.fje.composite.Component;
import java.util.List;

/**
 * ComponentIterator 类使用了迭代器模式
 * 实现了 MyIterator 接口，用于遍历 Component 集合
 */
public class ComponentIterator implements MyIterator<Component> {
    private List<Component> components;
    private int position = 0;

    public ComponentIterator(List<Component> components) {
        this.components = components;
    }

    @Override
    public boolean hasNext() {
        return position < components.size();
    }

    @Override
    public Component getNext() {
        return components.get(position++);
    }
}
