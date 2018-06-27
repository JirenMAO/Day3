package me.mao.minigame.api.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;
import java.util.Set;

public class FileBuilder {

    private File f;
    private YamlConfiguration c;

    public FileBuilder(String filePath, String fileName) {
        this.f = new File(filePath, fileName);
        this.c = YamlConfiguration.loadConfiguration(f);
    }

    public FileBuilder setValue(String valuePath, Object value) {
        this.c.set(valuePath, value);
        return this;
    }

    public boolean exists() {
        return f.exists();
    }

    public Object getObject(String valuePath) {
        return c.get(valuePath);
    }

    public Integer getInt(String valuePath) {
        return c.getInt(valuePath);
    }

    public Double getDouble(String valuePath) {return c.getDouble(valuePath);}

    public float getFloat(String valuePath) {return (float)c.getDouble(valuePath);}

    public String getString(String valuePath) {
        return c.getString(valuePath);
    }

    public boolean getBoolean(String valuePath) {
        return c.getBoolean(valuePath);
    }

    public List<String> getStringList(String listPath) {
        return c.getStringList(listPath);
    }

    public Set<String> getKeys(boolean deep) {
        return c.getKeys(deep);
    }

    public ConfigurationSection getConfigurationSection(String path) {
        return c.getConfigurationSection(path);
    }

    public FileBuilder save() {
        try {
            c.save(f);
            return this;
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[Error]:" + ChatColor.GRAY + " Couldn't save the " + c.getName() + "!");
            return null;
        }
    }
}
