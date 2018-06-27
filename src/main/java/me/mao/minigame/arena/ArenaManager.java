package me.mao.minigame.arena;

import me.mao.minigame.Core;
import me.mao.minigame.arena.data.ArenaData;
import me.mao.minigame.arena.spawn.Spawn;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArenaManager {

    private Set<Arena> arenas = new HashSet<>();
    private Core core = Core.getInstance();
    private ArenaData data = new ArenaData(core, "arenas");

    public void loadArenas() {
        if (data.getKeys(false).isEmpty()) return;

        data.getKeys(false).forEach(string -> loadArena(string));
        core.getLogger().info("LOADED ALL ARENAS!");
    }

    public void loadArena(String name) {
        //List<String> tempsp = data.getStringList(name + ".spawns");
        List<Spawn> spawns = new ArrayList<>();

        for(Object s : data.getSection(name + ".spawns").getKeys(false)) {
            if(s == null) continue;
            spawns.add(new Spawn(data.loadLocation(data.get(name + ".spawns." + s))));
        }

        Arena a = new Arena(name, spawns);
        arenas.add(a);


    }

    public void addArena(Arena a) {
        arenas.add(a);
        saveArena(a);
    }

    public void removeArena(Arena a) {
        arenas.remove(a);
        data.set(a.getName(), null);
    }

    public Arena getArena(String name) {
        return arenas.stream().filter(a -> a.getName().equalsIgnoreCase(name)).findAny().orElse(null);
    }

    public Arena getArena(Player p) {
        return arenas.stream().filter(a -> a.hasPlayer(p)).findAny().orElse(null);
    }

    public Set<Arena> getArenas() {
        return arenas;
    }

    public void saveArena(Arena a) {
        //SELECTION SAVE
        data.set(a.getName() + ".selection.world", a.getSelection().getWorld().getName());
        data.set(a.getName() + ".selection.cornerA", a.getSelection().getMinimumPoint());
        data.set(a.getName() + ".selection.cornerB", a.getSelection().getMaximumPoint());
        //SPAWNS SAVE
        a.getSpawns().forEach(spawn -> data.set(a.getName() + ".spawns", spawn.getLocation()));

        //Location roundedLocation = new Location(a.getSelection().getWorld(), Math.round(p.getLocation().getX()) + 0.5, Math.round(p.getLocation().getY()), Math.round(p.getLocation().getZ()) + 0.5);
    }

    public void saveArenas() {
        arenas.forEach(this::saveArena);
    }

    public ArenaData getData() {
        return data;
    }
}
