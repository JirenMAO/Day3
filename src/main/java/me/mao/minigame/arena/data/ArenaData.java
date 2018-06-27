package me.mao.minigame.arena.data;

import me.mao.minigame.Core;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;
import java.util.Set;

public class ArenaData {

    private Core core;

    public ArenaData(Core core, String fileName) {

        this.core = core;

        if (!core.getDataFolder().exists()) core.getDataFolder().mkdir();

        file = new File(core.getDataFolder(), fileName + ".yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    private File file;
    private FileConfiguration config;

    public void set(String path, Object value) {
        config.set(path, value);
        try {
            config.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ConfigurationSection createConfigurationSection(String path) {
        ConfigurationSection cs = config.createSection(path);
        try {
            config.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cs;
    }

    public ConfigurationSection getSection(String path) {
        return config.getConfigurationSection(path);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String path) {
        return (T) config.get(path);
    }

    public Set<String> getKeys(boolean deep) {return config.getKeys(deep);}

    public boolean contains(String path) {
        return config.contains(path);
    }

    public List<String> getStringList(String path) {
        return config.getStringList(path);
    }

    public List<?> getList(String path) {
        return config.getList(path);
    }

    public void saveLocation(Location location, ConfigurationSection section) {
        section.set("world", location.getWorld().getName());
        section.set("x", location.getX());
        section.set("y", location.getY());
        section.set("z", location.getZ());
        section.set("pitch", location.getPitch());
        section.set("yaw", location.getYaw());
        try {
            config.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Location loadLocation(ConfigurationSection section) {
        return new Location(
                Bukkit.getServer().getWorld(section.getString("world")),
                section.getDouble("x"),
                section.getDouble("y"),
                section.getDouble("z"),
                (float) section.getDouble("pitch"),
                (float) section.getDouble("yaw")
        );
    }


}
