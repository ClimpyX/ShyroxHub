package com.shyrox.utils;

import com.shyrox.config.FileConfig;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConfigUtils {
    private final FileConfig fileConfig;
    private FileConfiguration configuration;
    private String pathValue;

    public void setConfiguration(FileConfiguration configuration) { this.configuration = configuration; } public void setPathValue(String pathValue) { this.pathValue = pathValue; }


    public ConfigUtils(FileConfig fileConfig, String pathValue) {
        this.fileConfig = fileConfig;
        this.pathValue = pathValue;

        this.configuration = fileConfig.getFileConfiguration();
    }

    public String getString(String path) {
        if (this.configuration.contains(getFinalPath(path))) {
            return this.configuration.getString(getFinalPath(path));
        }

        return "HATA: GİRDİ DEĞERİ BULUNAMADI!";
    }

    public String getString(String path, boolean colorized) {
        if (this.configuration.contains(getFinalPath(path))) {
            String toReturn;

            if (colorized) {
                toReturn = ChatColor.translateAlternateColorCodes('&', this.configuration.getString(getFinalPath(path)));
            } else {
                return this.configuration.getString(getFinalPath(path));
            }

            return toReturn;
        }

        return "HATA: GİRDİ DEĞERİ BULUNAMADI!";
    }

    public boolean getBoolean(String path) {
        if (this.configuration.contains(getFinalPath(path))) {
            return this.configuration.getBoolean(getFinalPath(path));
        }

        return false;
    }

    public int getInt(String path) {
        if (this.configuration.contains(getFinalPath(path))) {
            return this.configuration.getInt(getFinalPath(path));
        }

        return 0;
    }

    public double getDouble(String path) {
        if (this.configuration.contains(getFinalPath(path))) {
            return this.configuration.getDouble(getFinalPath(path));
        }

        return 0.0D;
    }

    public List<String> getStringList(String path, boolean colorized) {
        ArrayList<String> toReturn = new ArrayList<String>();
        for (String colorizedList : getStringList(getFinalPath(path))) {
            if (colorized) {
                toReturn.add(ChatColor.translateAlternateColorCodes('&', colorizedList)); continue;
            }
            return getStringList(getFinalPath(path));
        }


        return toReturn;
    }

    public List<String> getStringList(String path) {
        if (this.configuration.contains(getFinalPath(path))) {
            return new ArrayList(this.configuration.getStringList(getFinalPath(path)));
        }

        return Collections.singletonList("HATA: GİRDİ LİST DEĞERİ BULUNAMADI!");
    }


    public void set(Object value) { set(null, value); }



    public void set(String path, Object values) { this.configuration.set(this.pathValue + ((path == null) ? "" : ("." + path)), values); }



    private String getFinalPath(String path) { return this.pathValue + "." + path; }



    public void configSave() { this.fileConfig.configSave(); }
}
