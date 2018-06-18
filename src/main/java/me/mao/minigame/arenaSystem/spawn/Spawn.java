package me.mao.minigame.arenaSystem.spawn;

import me.mao.minigame.Core;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Spawn {

    private Location loc;
    private Player player;
    private Core core;

    public Spawn(Core core, Location loc) {
        this.core = core;
        this.loc = (loc);
    }

    public Location getLocation() {
        return loc;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean hasPlayer() {
        return player != null;
    }

}
