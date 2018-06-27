package me.mao.minigame.user.rank;

import me.mao.minigame.api.coreUser.coreRank.CoreRank;

import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

public class Rank implements CoreRank {

    private String name;
    private String prefix;
    private List<String> permissions;

    public Rank(String name, String prefix, List<String> permissions) {
        this.name = name;
        this.prefix = prefix;
        this.permissions = permissions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public void addPermission(String permision) {
        this.permissions.add(permision);
    }

    public void removePermission(String permission) {
        this.permissions.remove(permission);
    }

}
