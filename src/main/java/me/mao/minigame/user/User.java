package me.mao.minigame.user;

import me.mao.minigame.api.coreUser.CoreUser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class User extends CoreUser {

    private String name;
    private UUID id;
    private Player player;
    private int coins;

    public User(UUID id, int coins) {
        this.id = id;
        this.coins = coins;
        this.player = Bukkit.getPlayer(id);
        this.name = player.getName();
    }


    public UUID getUUID() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public String getName() {
        return name;
    }

    public int getCoins() {
        return coins;
    }

    public void setUUID(UUID id) {
        this.id = id;
    }

    public void setPlayer(Player p) {
        this.player = p;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
}
