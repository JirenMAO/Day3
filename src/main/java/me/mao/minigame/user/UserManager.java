package me.mao.minigame.user;

import me.mao.minigame.Core;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class UserManager {

    private Set<User> users = new HashSet<User>();


    public void addUser(Player player) {
        User u = new User(player.getUniqueId(), 0);
        if(!users.contains(u)) {
            users.add(u);
            saveUser(u);
        }
    }

    public void addUser(User u) {
        if(!users.contains(u)) {
            users.add(u);
            saveUser(u);
        }
    }

    public void removeUser(User u) {
        if(users.contains(u)) {
            users.remove(u);
        }
    }

    public void removeUser(Player player) {
        User u = new User(player.getUniqueId(), 0);
        if(!users.contains(u)) {
            users.remove(u);
        }
    }

    public Set<User> getUsers() {
        return users;
    }

    public boolean isUser(Player p) {
        User u = new User(p.getUniqueId(), 0);
        return users.contains(u);
    }

    public void saveUser(User u) {

    }



}
