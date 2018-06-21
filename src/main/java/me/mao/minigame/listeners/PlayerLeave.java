package me.mao.minigame.listeners;

import me.mao.minigame.Core;
import me.mao.minigame.api.coreListener.CoreListener;
import me.mao.minigame.api.utils.ChatUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave extends CoreListener {

    private Core core;

    public PlayerLeave(Core core) {
        super(core, "playerLeave");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        core.getRankManager().saveRank(core.getUserManager().getUser(e.getPlayer()).getRank());
        core.getUserManager().removeUser(e.getPlayer());
    }


}
