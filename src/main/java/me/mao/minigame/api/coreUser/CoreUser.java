package me.mao.minigame.api.coreUser;

import org.bukkit.entity.Player;

import java.util.UUID;

public abstract class CoreUser {

    public abstract UUID getUUID();


    public abstract int getCoins();

    public abstract void setUUID(UUID id);

    public abstract void setCoins(int coins);

}
