package me.mao.minigame.listeners;

import me.mao.minigame.Core;

import me.mao.minigame.api.utils.ChatUtils;
import me.mao.minigame.user.User;

import me.mao.minigame.user.scoreboard.InfoScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerJoin implements Listener {

    private Core core;

    public PlayerJoin(Core core) {
        this.core = core;
        Bukkit.getPluginManager().registerEvents(this, core);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(ChatUtils.colorize("&6&lThe Listeners System is Working!"));


        if(!core.getUserManager().getUserData().contains(e.getPlayer().getUniqueId().toString())) {
            User us = new User(e.getPlayer().getUniqueId(), 0, core.getRankManager().getDefaultRank());
            core.getUserManager().loadUser(us);
            core.getUserManager().saveUser(us);
            scoreboard(us);
        }

        User u = core.getUserManager().loadUser(e.getPlayer());

        core.getUserManager().saveUser(u);
        if(core.getTeamManager().hasTeam(e.getPlayer()) != null) {
            core.getTeamManager().hasTeam(e.getPlayer()).addPlayer(e.getPlayer());
        }

        PermissionAttachment attachment = e.getPlayer().addAttachment(core);
        u.getRank().getPermissions().forEach(perm -> attachment.setPermission(perm, true));

        e.getPlayer().setDisplayName(ChatUtils.colorize(u.getRank().getPrefix() + " &r" + e.getPlayer().getName()));
        e.getPlayer().sendMessage("Your rank is " + ChatUtils.colorize(u.getRank().getPrefix()));
        scoreboard(u);
    }

    public void scoreboard(User u) {
        //SCOREBOARDS:

        Map<Integer, List<String>> sbdata = new HashMap<>();

        List<String> line1 = new ArrayList<String>();
        List<String> displays = new ArrayList<String>();
        Map<Integer, String> ordinary = new HashMap<>();

        displays.add("&6&lMAO SERVER");
        displays.add("&5&lMAO SERVER");
        displays.add("&6&lM");
        displays.add("&6&lMA");
        displays.add("&6&lMAO");
        displays.add("&6&lMAO S");
        displays.add("&6&lMAO SE");
        displays.add("&6&lMAO SER");
        displays.add("&6&lMAO SERV");
        displays.add("&6&lMAO SERVE");
        displays.add("&6&lMAO SERVER");
        displays.add("&4&lMAO SERVER");
        displays.add("&3&lMAO SERVER");
        displays.add("&2&lMAO SERVER");
        displays.add("&1&lMAO SERVER");
        displays.add("&a&lMAO SERVER");


        ordinary.put(8,"");
        ordinary.put(7,"&7Rank: &r" + u.getRank().getPrefix());
        ordinary.put(6,"");
        ordinary.put(5,"&7Name: &r" + u.getPlayer().getName());
        ordinary.put(4,"");
        ordinary.put(3,"&7Are you cool: &r Yes");
        ordinary.put(2,"");
        ordinary.put(1,"&7Coins: &r" + u.getCoins());
        ordinary.put(0, "");

        new InfoScoreboard(core, sbdata,displays,ordinary,u,displays.size()).runTaskTimerAsynchronously(core, 0, 5);
    }


}
