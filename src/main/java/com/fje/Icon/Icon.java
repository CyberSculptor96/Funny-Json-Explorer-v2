package com.fje.Icon;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

/**
 * Represents an icon configuration handler that loads icon settings from a YAML
 * file.
 */
public class Icon {
    private Map<String, Object> iconConfig;
    private String iconFamily;

    public Icon(String iconFamily) {
        this.iconFamily = iconFamily;
        loadIconConfig();
    }

    public Icon() {
        this.iconFamily = "default";
        loadIconConfig();
    }

    /**
     * Loads the icon configuration from a YAML file.
     */
    private void loadIconConfig() {
        try (InputStream inputStream = Files.newInputStream(Paths.get("src/resources/config.yaml"))) {
            Yaml yaml = new Yaml();
            this.iconConfig = yaml.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public String getContainerIcon() {
        return ((Map<String, String>) iconConfig.get(iconFamily))
                .get("container_icon");
    }

    @SuppressWarnings("unchecked")
    public String getLeafIcon() {
        return ((Map<String, String>) iconConfig.get(iconFamily))
                .get("leaf_icon");
    }
}
