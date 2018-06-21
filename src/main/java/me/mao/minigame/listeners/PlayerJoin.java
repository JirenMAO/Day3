package me.mao.minigame.listeners;

import me.mao.minigame.Core;
import me.mao.minigame.api.coreListener.CoreListener;
import me.mao.minigame.api.utils.ChatUtils;
import me.mao.minigame.user.User;
import org.bukkit.Bukkit;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    private Core core;

    public PlayerJoin(Core core) {
        this.core = core;
        Bukkit.getPluginManager().registerEvents(this, core);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(ChatUtils.colorize("&6&lThe Listeners System is Working!"));
        if(core.getUserManager().getUser(e.getPlayer()) == null) {
            User u = new User(e.getPlayer().getUniqueId(), 0, core.getRankManager().getDefaultRank());
            core.getUserManager().loadUser(u);
            core.getUserManager().saveUser(u);
        }

        User u = core.getUserManager().loadUser(e.getPlayer());
        core.getUserManager().saveUser(u);
        e.getPlayer().sendMessage("Your rank is " + ChatUtils.colorize(u.getRank().getPrefix()));
    }


}
