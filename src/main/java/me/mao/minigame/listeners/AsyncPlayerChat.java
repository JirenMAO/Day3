package me.mao.minigame.listeners;

import me.mao.minigame.Core;
import me.mao.minigame.api.utils.ChatUtils;
import me.mao.minigame.user.User;
import me.mao.minigame.user.rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class AsyncPlayerChat implements Listener {

    private Core core;

    public AsyncPlayerChat(Core core) {
        this.core = core;
        Bukkit.getPluginManager().registerEvents(this, core);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        User u = core.getUserManager().loadUser(e.getPlayer());

        e.setFormat(ChatUtils.colorize(u.getRank().getPrefix()) + " " + (Object) ChatColor.GRAY + e.getPlayer().getName() + (Object) ChatColor.GOLD + " >> " + (Object) ChatColor.RESET + e.getMessage());

    }


}
