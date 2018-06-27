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
import me.mao.minigame.team.TeamManager;
import me.mao.minigame.user.UserManager;
import me.mao.minigame.user.rank.RankManager;

import org.bukkit.Bukkit;

import org.bukkit.plugin.java.JavaPlugin;



public class Core extends JavaPlugin implements usesAPI {

    private static Core instance;

    private UserManager userManager;
    private WorldEditPlugin worldEditPlugin;
    private ArenaManager arenaManager;
    private RankManager rankManager;
    private TeamManager teamManager;

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

        //teamManager.loadTeams();

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

        //ARENA COMMANDS

        this.getCommand("addspawn").setExecutor(new ArenaAddSpawn());
        this.getCommand("as").setExecutor(new ArenaAddSpawn());

        this.getCommand("acreate").setExecutor( new ArenaCreate());
        this.getCommand("ac").setExecutor( new ArenaCreate());

        this.getCommand("adelete").setExecutor(new ArenaDelete());
        this.getCommand("ad").setExecutor(new ArenaDelete());

        this.getCommand("astart").setExecutor( new ArenaForceStart());
        this.getCommand("frs").setExecutor( new ArenaForceStart());

        this.getCommand("astop").setExecutor(new ArenaForceStop());
        this.getCommand("fs").setExecutor(new ArenaForceStop());

        this.getCommand("ahelp").setExecutor(new ArenaHelp());
        this.getCommand("ah").setExecutor(new ArenaHelp());

        this.getCommand("ajoin").setExecutor(new ArenaJoin());
        this.getCommand("aj").setExecutor(new ArenaJoin());

        this.getCommand("aleave").setExecutor(new ArenaLeave());

        this.getCommand("alist").setExecutor(new ArenaList());
        this.getCommand("arenas").setExecutor(new ArenaList());
        this.getCommand("al").setExecutor(new ArenaList());

        this.getCommand("areload").setExecutor(new ArenasReload());
        this.getCommand("arl").setExecutor(new ArenasReload());

        this.getCommand("arenaspawns").setExecutor(new ArenaSpawns());

        this.getCommand("arenastate").setExecutor(new ArenaGameState());

        /*
        new me.mao.minigame.commands.teamCommands.Create(this);
        new me.mao.minigame.commands.teamCommands.Delete(this);
        new me.mao.minigame.commands.teamCommands.Invite(this);
        new me.mao.minigame.commands.teamCommands.Kick(this);
        new me.mao.minigame.commands.teamCommands.Leave(this);
        new me.mao.minigame.commands.teamCommands.Teams(this);
        */
    }

    public void registerListeners() {
        new PlayerJoin(this);
        new PlayerLeave(this);
        new AsyncPlayerChat(this);
    }

    public void loadDepends() {
        instance = this;
        userManager = new UserManager();
        arenaManager = new ArenaManager();
        rankManager = new RankManager();
        teamManager = new TeamManager();

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

    public TeamManager getTeamManager() {
        return teamManager;
    }


}
