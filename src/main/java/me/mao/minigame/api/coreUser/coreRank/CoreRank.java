package me.mao.minigame.api.coreUser.coreRank;

import java.util.List;

public interface CoreRank {

    String getName();
    String getPrefix();
    List<String> getPermissions();

    void setName(String name);
    void setPrefix(String prefix);
    void setPermissions(List<String> permissions);
    void addPermission(String permision);
    void removePermission(String permission);
}
