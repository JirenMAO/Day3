package me.mao.minigame.api.coreArena;

import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import com.sk89q.worldedit.bukkit.selections.Selection;
import org.bukkit.entity.Player;

import java.util.List;

public interface CoreArena {

    String getName();
    //Spawn getSpawns();
    CuboidSelection getCuboidSelection();
    List<Player> getPlayers();
    boolean hasPlayer(Player player);
    boolean shouldStart();
    boolean isMinimumMet();

    void addPlayer(Player player);
    void removePlayer(Player player);
    void setCuboidSelection(CuboidSelection selection);

    void startCountdown();
    void startGame();
    void endGame();

}
