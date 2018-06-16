package me.mao.minigame.api.coreUser;

import org.bukkit.entity.Player;

import java.util.UUID;

public abstract class CoreUser {

    public abstract UUID getUUID();

    public abstract Player getPlayer();

    public abstract String getName();

    public abstract int getCoins();

    public abstract void setUUID(UUID id);

    public abstract void setPlayer(Player p);

    public abstract void setName(String name);

    public abstract void setCoins(int coins);

}
