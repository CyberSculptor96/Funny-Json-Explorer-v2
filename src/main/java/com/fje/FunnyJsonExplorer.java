package com.fje;

import com.fje.Icon.Icon;
import com.fje.composite.RootContainer;
import com.fje.strategy.DrawContext;
import com.fje.strategy.RectangleDrawStrategy;
import com.fje.strategy.TreeDrawStrategy;

import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.cli.*;

import org.json.JSONObject;

public class FunnyJsonExplorer {
    public static void main(String[] args) {
        // 创建命令行解析器
        Options options = new Options();

        Option inputFile = new Option("f", "file", true, "Specify the JSON file");
        inputFile.setRequired(true);
        options.addOption(inputFile);

        Option style = new Option("s", "style", true, "Specify the style");
        style.setRequired(true);
        options.addOption(style);

        Option icon = new Option("i", "icon", true, "Specify the icon family");
        icon.setRequired(true);
        options.addOption(icon);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);
            System.exit(1);
            return;
        }

        // 获取命令行参数
        String jsonFilePath = cmd.getOptionValue("file");
        String styleOption = cmd.getOptionValue("style");
        String iconOption = cmd.getOptionValue("icon");

        JSONObject data = null;
        try {
            String content = new String(Files.readAllBytes(Paths.get(jsonFilePath)), "UTF-8");
            data = new JSONObject(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Icon iconFamily = new Icon(iconOption);

        DrawContext drawContext = new DrawContext();

        // 使用策略模式，指定绘制图形的策略
        if (styleOption.equals("tree")) {
            drawContext.setStrategy(new TreeDrawStrategy());
        } else {
            drawContext.setStrategy(new RectangleDrawStrategy());
        }

        // 创建根节点容器
        RootContainer rootContainer = new RootContainer("root", 0, iconFamily);

        // 结合配置文件加载 JSON 文件，并进行可视化
        rootContainer.load(data, iconFamily);
        rootContainer.show(drawContext);
    }
}