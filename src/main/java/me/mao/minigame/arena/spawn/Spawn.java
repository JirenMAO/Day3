package me.mao.minigame.arena.spawn;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Spawn {

    private Location loc;
    private Player player;

    public Spawn(Location loc) {
        this.loc = loc;
    }

    public Location getLocation() {
        return loc;
    }

    public void setLocation(Location loc) {
        this.loc = loc;
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
