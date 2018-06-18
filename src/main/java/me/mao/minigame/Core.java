package me.mao.minigame;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import me.mao.minigame.api.usesAPI;
import me.mao.minigame.arenaSystem.ArenaManager;
import me.mao.minigame.commands.TestCommand;
import me.mao.minigame.commands.arenaCommands.Create;
import me.mao.minigame.commands.arenaCommands.Delete;
import me.mao.minigame.commands.arenaCommands.Join;
import me.mao.minigame.commands.arenaCommands.Leave;
import me.mao.minigame.listeners.PlayerJoin;
import me.mao.minigame.listeners.PlayerLeave;
import me.mao.minigame.user.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin implements usesAPI {

    private static Core instance;
    private UserManager userManager;
    private WorldEditPlugin worldEditPlugin;
    private ArenaManager arenaManager;

    public static Core getInstance() {
        return instance;
    }

    public void onEnable() {
        setup();
        loadDepends();
        registerCommands();
        registerListeners();
    }

    public void onDisable() {

    }
    private void setup() {
        worldEditPlugin = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
        if(worldEditPlugin == null) {
            Bukkit.getPluginManager().disablePlugin(this);
            this.getLogger().severe("No world edit dependency found!");
        }

        arenaManager.loadArenas();
    }

    public void registerCommands() {
        new TestCommand(this);
        new Create(this);
        new Delete(this);
        new Join(this);
        new Leave(this);
    }

    public void registerListeners() {
        new PlayerJoin(this);
        new PlayerLeave(this);
    }

    public void loadDepends() {
        userManager = new UserManager();
        arenaManager = new ArenaManager(this);
    }

    public UserManager getUserManager() {
        return userManager;
    }
    public WorldEditPlugin getWorldEditPlugin() {return worldEditPlugin;}
}
