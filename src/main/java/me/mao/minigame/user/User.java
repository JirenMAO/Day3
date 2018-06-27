package me.mao.minigame.user;

import me.mao.minigame.api.coreUser.CoreUser;
import me.mao.minigame.user.rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class User extends CoreUser {


    private UUID id;

    private Rank rank;
    private int coins;

    public User(UUID id, int coins, Rank rank) {
        this.id = id;
        this.coins = coins;

        this.rank = rank;
    }


    public UUID getUUID() {
        return id;
    }



    public Rank getRank() {
        return rank;
    }

    public int getCoins() {
        return coins;
    }

    public void setUUID(UUID id) {
        this.id = id;
    }


    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public boolean hasPermission(String permission) {
        return rank.getPermissions().contains(permission);
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(id);
    }
}
