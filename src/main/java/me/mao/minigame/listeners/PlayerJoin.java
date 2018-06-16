package me.mao.minigame.listeners;

import me.mao.minigame.Core;
import me.mao.minigame.api.coreListener.CoreListener;
import me.mao.minigame.api.utils.ChatUtils;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin extends CoreListener {

    private Core core;

    public PlayerJoin(Core core) {
        super(core, "playerJoin");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(ChatUtils.colorize("&6&lThe Listeners System is Working!"));
        core.getUserManager().addUser(e.getPlayer());
    }


}
