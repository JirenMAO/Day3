package me.mao.minigame.arenaSystem;

import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import com.sk89q.worldedit.bukkit.selections.Selection;
import me.mao.minigame.Core;
import me.mao.minigame.api.coreArena.CoreArena;
import me.mao.minigame.api.utils.ChatUtils;
import me.mao.minigame.arenaSystem.spawn.Spawn;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class Arena implements CoreArena {

    public enum ArenaState {
        WAITING, STARTING, ENDING, RESSETING, STARTED;
    }

    private Core core;

    private String name;
    private ArenaState state;
    private CuboidSelection selection;
    private List<Player> players;
    private Set<Spawn> spawns;

    public Arena(Core core, String name) {
        this.core = core;
        this.name = name;
        this.state = ArenaState.WAITING;

        World w = Bukkit.getServer().getWorld(core.getConfig().getString(name + ".world"));
        Location cornerA = (Location) core.getConfig().get(name + ".cornerA");
        Location cornerB = (Location) core.getConfig().get(name + ".cornerB");

        this.selection = new CuboidSelection(w, cornerA, cornerB);

        players = new ArrayList<Player>();
        spawns = new HashSet<>();

        if(core.getConfig().contains(name + ".spawns")) {
            for(String s : core.getConfig().getConfigurationSection(name +".spawns").getKeys(false)) {
                spawns.add(new Spawn(core , (Location) core.getConfig().getConfigurationSection(name + ".spawns").get(s)));
            }
        }
    }

    public String getName() {
        return name;
    }

    public CuboidSelection getCuboidSelection() {
        return selection;
    }


    public List<Player> getPlayers() {
        return players;
    }

    public boolean hasPlayer(Player player) {
        if (players.contains(player)) return true;
        return false;
    }

    public boolean shouldStart() {
        if (isMinimumMet() && state == ArenaState.WAITING)
            return true;

        return false;
    }

    public int getMaxPlayers() {
        return spawns.size();
    }

    public int getMinimumPlayers() {
        return (int) spawns.size() / 2;
    }

    public boolean isMinimumMet() {
        return players.size() == getMinimumPlayers();
    }


    public void addPlayer(Player player) {
        if (!players.contains(player)) {
            players.add(player);
            handlePlayer(player);
            if(shouldStart()) startCountdown();
        }
    }


    public void removePlayer(Player player) {
        if (players.contains(player)) {
            players.remove(player);
            handlePlayer(player);
        }
    }

    public void setCuboidSelection(CuboidSelection selection) {
        this.selection = selection;
    }

    public boolean inArena(Player p) {
        return selection.contains(p.getLocation());
    }

    public void startCountdown() {
        state = ArenaState.STARTING;

        new BukkitRunnable() {
            int countdown = 30;

            @Override
            public void run() {
                if (isMinimumMet()) {
                    if (countdown != 0) {
                        switch (countdown) {
                            case 30:
                                ChatUtils.broadcast(ChatUtils.colorize("&6&lGame starts in &7" + countdown));
                                break;
                            case 15:
                                ChatUtils.broadcast(ChatUtils.colorize("&6&lGame starts in &7" + countdown));
                                break;
                            case 10:
                                ChatUtils.broadcast(ChatUtils.colorize("&6&lGame starts in &7" + countdown));
                                break;
                            case 5:
                                ChatUtils.broadcast(ChatUtils.colorize("&6&lGame starts in &7" + countdown));
                                break;
                            case 4:
                                ChatUtils.broadcast(ChatUtils.colorize("&e&lGame starts in &7" + countdown));
                                break;
                            case 3:
                                ChatUtils.broadcast(ChatUtils.colorize("&c&lGame starts in &7" + countdown));
                                break;
                            case 2:
                                ChatUtils.broadcast(ChatUtils.colorize("&4&lGame starts in &7" + countdown));
                                break;
                            case 1:
                                ChatUtils.broadcast(ChatUtils.colorize("&5&lGame starts in &7" + countdown));
                                break;
                        }
                    }

                    this.cancel();
                    ChatUtils.broadcast(ChatUtils.colorize("&6&lGame has started!"));
                }

                this.cancel();
                ChatUtils.broadcast(ChatUtils.colorize("&cNot enough players!"));
                countdown = 30;
            }


        }.runTaskTimerAsynchronously(core, 0, 20);
    }

    public void startGame() {

    }

    public void endGame() {
        state = ArenaState.ENDING;

    }

    private void resetMap() {
        state = ArenaState.RESSETING;
        for(Entity en : selection.getWorld().getEntities()) {
            if(selection.contains(en.getLocation()) && en.getType() == EntityType.DROPPED_ITEM){
                en.remove();
            }
        }
    }

    private void handlePlayer(Player player) {
        Set<UUID> handled = new HashSet<>();
         HashMap<UUID, ItemStack[]> inv = new HashMap<>();
        HashMap<UUID, ItemStack[]> armor = new HashMap<>();
        HashMap<UUID, Integer> level = new HashMap<>();
        HashMap<UUID, Location> loc = new HashMap<>();

        UUID id = player.getUniqueId();

        if(handled.contains(id)) {

            player.getInventory().setContents(inv.get(id));
            player.getInventory().setArmorContents(armor.get(id));
            player.setLevel(level.get(id));
            player.teleport(loc.get(id));

            inv.remove(id);
            armor.remove(id );
            level.remove(id);
            loc.remove(id);

            player.sendMessage(ChatUtils.colorize("&c&lYou have left the arena!"));

        }else {

            inv.put(id, player.getInventory().getContents());
            armor.put(id , player.getInventory().getArmorContents());
            level.put(id, player.getLevel());
            loc.put(id, player.getLocation());

            player.getInventory().setContents(null);
            player.getInventory().setArmorContents(null);
            player.setLevel(0);
            //player.teleport()
            player.sendMessage(ChatUtils.colorize("&6&lYou have been prepared for the game!"));

        }
    }
}
