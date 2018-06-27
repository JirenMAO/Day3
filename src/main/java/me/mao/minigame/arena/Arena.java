package me.mao.minigame.arena;

import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import me.mao.minigame.Core;
import me.mao.minigame.api.utils.ChatUtils;
import me.mao.minigame.arena.rollback.RollbackHandler;
import me.mao.minigame.arena.spawn.Spawn;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Arena {

    public enum ArenaState {
        WAITING, STARTING, STARTED, ENDING, ENDED, RESETING;
    }


    private ArenaState state;
    private String name;
    private CuboidSelection selection;
    private List<Player> players;
    private List<Spawn> spawns;
    private List<Player> spectators;

    private Core core = Core.getInstance();


    public Arena(String name, List<Spawn> spawns) {
        this.state = ArenaState.WAITING;
        this.name = name;
        this.spawns = spawns;
        this.players = new ArrayList<Player>();

        World sw = Bukkit.getWorld((String) core.getArenaManager().getData().get(name + ".selection.world"));
        Location coA = (Location) core.getArenaManager().getData().get(name + ".selection.cornerA");
        Location coB = (Location) core.getArenaManager().getData().get(name + ".selection.cornerB");

        selection = new CuboidSelection(sw, coA, coB);
    }

    public ArenaState getState() {
        return state;
    }

    public void setState(ArenaState state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Spawn> getSpawns() {
        return spawns;
    }

    public void addSpawn(Spawn s) {
        if (spawns.contains(s)) return;
        spawns.add(s);
    }

    public void setSpawns(List<Spawn> spawns) {
        this.spawns = spawns;
    }

    public CuboidSelection getSelection() {
        return selection;
    }

    public boolean isMinimumMet() {
        return players.size() >= 2;
    }

    public boolean shouldStart() {
        if (isMinimumMet() && state == ArenaState.WAITING) {
            startCountdown();
            return true;
        }
        return false;
    }

    public void startGame() {
        state = ArenaState.STARTED;
        ChatUtils.broadcast(ChatUtils.colorize("&6&lGame has started!"));
    }

    public boolean shouldEnd() {
        if (players.size() == 0 && state == ArenaState.STARTED) {
            endGame();
            return true;
        }

        return false;
    }

    public void endGame() {
        state = ArenaState.ENDING;

        new BukkitRunnable() {
            @Override
            public void run() {
                players.forEach(player -> removePlayer(player));
                cancel();
            }
        }.runTaskLater(core, 20 * 5);

        rollBack();
    }

    public void rollBack() {
        state = ArenaState.RESETING;
        RollbackHandler.getRollbackHandler().rollback(selection.getWorld());
        state = ArenaState.WAITING;
    }

    public void addPlayer(Player p) {
        if (spawns.size() != 0 && spawns.size() > players.size() && (state == ArenaState.WAITING || state == ArenaState.STARTING)) {
            players.add(p);
            handlePlayer(p);
            shouldStart();
        } else {
            p.sendMessage(ChatUtils.colorize("&cSorry, there is no room for you curently!"));
        }
    }

    public void removePlayer(Player player) {
        players.remove(player);
        handlePlayer(player);
        shouldEnd();
    }

    public Player hasPlayer(String name) {
        return players.stream().filter(player -> player.getName().equalsIgnoreCase(name)).findAny().orElse(null);
    }

    public boolean hasPlayer(Player p) {
        return players.contains(p);
    }

    public void handlePlayer(Player player) {


        UUID id = player.getUniqueId();

        if (!players.contains(player)) {

            for (Spawn spawn : spawns) {
                if (spawn.hasPlayer() && spawn.getPlayer().equals(player)) {
                    spawn.setPlayer(null);
                }
            }

            player.sendMessage(ChatUtils.colorize("&c&lYou have left the arena!"));

        } else {

            player.getInventory().clear();
            player.getInventory().setArmorContents(null);
            player.setLevel(0);

            for (Spawn spawn : spawns) {
                if (!spawn.hasPlayer()) {
                    spawn.setPlayer(player);
                    player.teleport(spawn.getLocation());
                    break;
                }
            }

            player.sendMessage(ChatUtils.colorize("&6&lYou have been prepared for the game!"));

        }

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
                        countdown--;
                    } else
                        if (countdown == 0) {
                        this.cancel();
                        startGame();
                    }
                }else {

                    this.cancel();
                    state = ArenaState.WAITING;
                    ChatUtils.broadcast(ChatUtils.colorize("&cNot enough players! countdown reseted"));
                    countdown = 30;
                }
            }


        }.runTaskTimerAsynchronously(core, 0, 20);

    }
}
