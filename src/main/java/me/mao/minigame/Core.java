package me.mao.minigame;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import me.mao.minigame.api.usesAPI;


import me.mao.minigame.arena.ArenaManager;
import me.mao.minigame.commands.TestCommand;
import me.mao.minigame.commands.arenaCommands.*;
import me.mao.minigame.commands.rankCommands.RankCommands;
import me.mao.minigame.listeners.AsyncPlayerChat;
import me.mao.minigame.listeners.PlayerJoin;
import me.mao.minigame.listeners.PlayerLeave;
import me.mao.minigame.user.UserManager;
import me.mao.minigame.user.rank.RankManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public class Core extends JavaPlugin implements usesAPI {

    private static Core instance;
    private Set<Class<?>> commandHolder;
    private Set<Class<?>> listenerHolder;

    private UserManager userManager;
    private WorldEditPlugin worldEditPlugin;
    private ArenaManager arenaManager;
    private RankManager rankManager;

    public static Core getInstance() {
        return instance;
    }

    public void onEnable() {
        saveDefaultConfig();
        loadDepends();
        setup();
        registerCommands();
        registerListeners();
    }

    public void onDisable() {

    }

    private void setup() {

        this.getDataFolder().mkdir();

        rankManager.loadRanks();

        userManager.loadUsers();

        arenaManager.loadArenas();


        worldEditPlugin = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
        if (worldEditPlugin == null) {
            Bukkit.getPluginManager().disablePlugin(this);
            this.getLogger().severe("No world edit dependency found!");
            return;
        }
    }

    public void registerCommands() {
        new RankCommands(this);
        new TestCommand(this);
        new Arenas(this);
        new Create(this);
        new Delete(this);
        new ForceStart(this);
        new ForceStop(this);
        new Join(this);
        new Leave(this);
    }

    public void registerListeners() {
        new PlayerJoin(this);
        new PlayerLeave(this);
        new AsyncPlayerChat(this);
    }

    public void loadDepends() {
        instance = this;
        commandHolder = new HashSet<>();
        listenerHolder = new HashSet<>();
        userManager = new UserManager();
        arenaManager = new ArenaManager();
        rankManager = new RankManager();
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public WorldEditPlugin getWorldEditPlugin() {
        return worldEditPlugin;
    }

    public ArenaManager getArenaManager() {
        return arenaManager;
    }

    public RankManager getRankManager() {
        return rankManager;
    }

}
