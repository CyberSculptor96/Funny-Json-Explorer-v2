package com.fje.strategy;

import com.fje.composite.Container;
import com.fje.composite.Leaf;
import java.util.List;

/**
 * TreeDrawStrategy 类使用了策略模式
 * 实现了 DrawStrategy 接口，定义了树形风格的绘制策略
 */
public class TreeDrawStrategy implements DrawStrategy {

    @Override
    public void drawContainer(Container container, int level, boolean isFirst, boolean isLast,
            List<Boolean> parentIsLast) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < level - 1; i++) {
            if (parentIsLast.get(i)) {
                indent.append("   ");
            } else {
                indent.append("│  ");
            }
        }
        String branch = isLast ? "└─" : "├─";
        String prefix = indent.toString() + branch + container.getIcon().getContainerIcon();
        System.out
                .println(prefix + " " + container.getName());
    }

    @Override
    public void drawLeaf(Leaf leaf, int level, boolean isFirst, boolean isLast, List<Boolean> parentIsLast) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < level - 1; i++) {
            if (parentIsLast.get(i)) {
                indent.append("   ");
            } else {
                indent.append("│  ");
            }
        }
        String branch = isLast ? "└─" : "├─";
        String prefix = indent.toString() + branch + leaf.getIcon().getLeafIcon();
        if (leaf.getValue() != "null") {
            System.out.println(prefix + " " + leaf.getName() + ": "
                    + leaf.getValue());
        } else {
            System.out.println(prefix + " " + leaf.getName());
        }
    }
}
