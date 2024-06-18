package com.fje.composite;

import com.fje.Icon.Icon;
import com.fje.iterator.MyIterator;
import com.fje.strategy.DrawContext;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * RootContainer 类使用了组合模式和迭代器模式
 * 该类继承 Container 类，具有 load 和 show 方法，在 show 方法中使用迭代器对子节点进行遍历
 */
public class RootContainer extends Container {

    public RootContainer(String name, int level, Icon icon) {
        super(name, level, icon);
    }

    // 加载 JSON 数据并构建树形结构
    public void load(JSONObject data, Icon icon) {
        Stack<Object[]> stack = new Stack<>();
        stack.push(new Object[] { this, data });

        while (!stack.isEmpty()) {
            Object[] current = stack.pop();
            Container currentContainer = (Container) current[0];
            JSONObject currentData = (JSONObject) current[1];

            java.util.Iterator<String> keys = currentData.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                Object value = currentData.get(key);
                if (value instanceof JSONObject) {
                    Container newContainer = new Container(key, currentContainer.getLevel() + 1, icon);
                    currentContainer.add(newContainer);
                    stack.push(new Object[] { newContainer, (JSONObject) value });
                } else {
                    Leaf newLeaf = new Leaf(key, value.toString(), icon);
                    currentContainer.add(newLeaf);
                }
            }
        }
    }

    // 显示树形结构
    public void show(DrawContext drawContext) {
        List<Boolean> parentIsLast = new ArrayList<>();
        boolean isFirst = true;
        for (MyIterator<Component> it = createIterator(); it.hasNext();) {
            Component child = it.getNext();
            child.draw(this.getLevel() + 1, isFirst, !it.hasNext(), parentIsLast,
                    drawContext);
            isFirst = false;
        }
    }
}
