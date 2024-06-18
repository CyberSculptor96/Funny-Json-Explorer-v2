package com.fje.strategy;

import com.fje.composite.Container;
import com.fje.composite.Leaf;
import java.util.List;

/**
 * RectangleDrawStrategy 类使用了策略模式
 * 实现了 DrawStrategy 接口，定义了矩形风格的绘制策略
 */
public class RectangleDrawStrategy implements DrawStrategy {
    private static final int maxLength = 55;

    @Override
    public void drawContainer(Container container, int level, boolean isFirst, boolean isLast,
            List<Boolean> parentIsLast) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < level - 1; i++) {
            indent.append("│   ");
        }
        String branch = (level == 1 && isFirst) ? "┌─" : "├─";
        String suffix = (level == 1 && isFirst) ? "┐" : "┤";
        String prefix = indent.toString() + branch + container.getIcon().getContainerIcon();
        System.out.println(prefix + " " + container.getName() + " "
                + "─".repeat(maxLength - prefix.length() - container.getName().length() - 1) + suffix);
    }

    @Override
    public void drawLeaf(Leaf leaf, int level, boolean isFirst, boolean isLast, List<Boolean> parentIsLast) {
        StringBuilder indent = new StringBuilder();
        boolean flag = true;
        for (int i = 0; i < level - 1; i++) {
            if (!parentIsLast.get(i)) {
                flag = false;
            }
            indent.append("│   ");
        }
        if (flag && isLast) {
            indent = new StringBuilder("└───");
            for (int i = 0; i < level - 2; i++) {
                indent.append("└───");
            }
        }
        String branch = (flag && isLast) ? "┴─" : "├─";
        String suffix = (flag && isLast) ? "┘" : "┤";
        String prefix = indent.toString() + branch + leaf.getIcon().getLeafIcon();
        if (leaf.getValue() != "null") {
            System.out.println(prefix + " " + leaf.getName() + ": " + leaf.getValue() + " "
                    + "─".repeat(maxLength - prefix.length() - leaf.getName().length() - leaf.getValue().length() - 3)
                    + suffix);
        } else {
            System.out
                    .println(prefix + " " + leaf.getName() + " "
                            + "─".repeat(maxLength - prefix.length() - leaf.getName().length() - 1)
                            + suffix);
        }
    }
}
