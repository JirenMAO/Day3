package me.mao.minigame.arenaSystem;

import me.mao.minigame.Core;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class ArenaManager {

    private Core core;
    private Set<Arena> arenas = new HashSet<Arena>();

    public ArenaManager(Core plugin) {
        this.core = plugin;
    }

    public void loadArenas() {
        if(core.getConfig().getKeys(false) == null) return;

        for(String s : core.getConfig().getKeys(false)) {
            arenas.add(new Arena(core , s));
        }
    }

    public Arena getArena(String name) {
        for(Arena a : arenas) {
            if(a.getName() != name)continue;
            return a;
        }

        return null;
    }

    public Arena getArena(Player player) {
        for(Arena a : arenas) {
            if(!a.hasPlayer(player))continue;
            return a;
        }

        return null;
    }

    public void addArena(Arena arena) {
        arenas.add(arena);
    }

    public void removeArena(Arena arena) {
        if(arenas.contains(arena)) arenas.remove(arena);
    }

}
