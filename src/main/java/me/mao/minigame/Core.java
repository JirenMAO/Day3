package me.mao.minigame;

import me.mao.minigame.api.coreListener.CoreListener;
import me.mao.minigame.api.usesAPI;
import me.mao.minigame.commands.TestCommand;
import me.mao.minigame.listeners.PlayerJoin;
import me.mao.minigame.listeners.PlayerLeave;
import me.mao.minigame.user.UserManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public class Core extends JavaPlugin implements usesAPI {

    private static Core instance;
    private UserManager userManager;
    private Set<CoreListener> listeners = new HashSet<CoreListener>();

    public static Core getInstance() {
        return instance;
    }

    public void onEnable() {
        loadDepends();
        registerCommands();
        registerListeners();
    }

    public void onDisable() {

    }

    public void registerCommands() {
        new TestCommand(this);
    }

    public void registerListeners() {
        listeners.add(new PlayerJoin(this));
        listeners.add(new PlayerLeave(this));
    }

    public void loadDepends() {
        userManager = new UserManager();
    }

    public UserManager getUserManager() {
        return userManager;
    }
}
