package com.shyrox.config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class FileConfig {

    private FileConfiguration fileConfiguration;
    private File file;

    public FileConfiguration getFileConfiguration() { return this.fileConfiguration; }
    public File getFile() { return this.file; }

    public FileConfig(JavaPlugin plugin, String fileName) {
        this.file = new File(plugin.getDataFolder(), fileName);
        checkFileStatus(plugin, this.file, false);

        this.fileConfiguration = YamlConfiguration.loadConfiguration(this.file);
    }

    public static void checkFileStatus(JavaPlugin plugin, File checkedFile, boolean hasDirectory) {
        String fileName = checkedFile.getName();

        if (!checkedFile.exists()) {
            if (hasDirectory) {
                if (checkedFile.mkdir()) {
                    plugin.getLogger().log(Level.WARNING, "Yapılandırma dosyası oluşturulmadı!");
                }
            } else if (!checkedFile.getParentFile().mkdir()) {
                plugin.getLogger().log(Level.WARNING, "Yapılandırma dizini oluşturulmadı!");
            }

            if (plugin.getResource(fileName) == null) {
                try {
                    if (!checkedFile.createNewFile())
                        plugin.getLogger().log(Level.WARNING, "Yapılandırma dizini oluşturulmadı.");
                } catch (IOException ex) {
                    plugin.getLogger().log(Level.WARNING, "Yaılandırma dosyası " + fileName + " oluşturulamadı!");
                }
            } else {
                plugin.saveResource(fileName, false);
            }
        }
    }

    public void configSave() {
        try {
            this.fileConfiguration.save(this.file);
        } catch (IOException ex) {
            Bukkit.getLogger().log(Level.WARNING, "Yapılandırma dosyası " + this.file.getName() + " kayıt edilemedi!");
            ex.printStackTrace();
        }
    }
}
