package me.mao.minigame.api.coreArena;

import com.sk89q.worldedit.bukkit.selections.CuboidSelection;

import me.mao.minigame.api.utils.ChatUtils;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface CoreArena {

    enum ArenaState{
        WAITING,STARTING,STARTED,ENDING,RESETTING;
    }

    class Countdown extends BukkitRunnable {

        private int timer;
        private String msg;
        private ArrayList<Integer> countingNums;

        public Countdown(int start, String msg,int... countingNums) {
            this.timer = start;
            this.msg = msg;
            this.countingNums = new ArrayList<Integer>();
            for (int i : countingNums) this.countingNums.add(i);
        }

        public void run() {
            if (timer == 0) {

                ChatUtils.broadcast(ChatUtils.colorize("&3The game has started!"));
                cancel();
            }

            if (countingNums.contains(timer)) {
                ChatUtils.broadcast(ChatUtils.colorize("&6Game starting in &7" + timer));            }

            timer--;
        }
    }


    String getName();
    CuboidSelection getCuboidSelection();
    Set<UUID> getPlayers();
    boolean hasPlayer(Player player);
    boolean shouldStart();
    boolean isMinimumMet();

    void addPlayer(Player player);
    void removePlayer(Player player);
    void setCuboidSelection(CuboidSelection selection);

    void startCountdown();
    void startGame();
    void endGame();
    void resetMap();

}
