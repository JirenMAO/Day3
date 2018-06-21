package me.mao.minigame.user;

import me.mao.minigame.Core;
import me.mao.minigame.user.data.UserData;
import me.mao.minigame.user.rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class UserManager {

    private Set<User> users;
    private UserData data;
    private Core core = Core.getInstance();

    public void loadUsers() {
        users = new HashSet<User>();
        data = new UserData(core, "users");

        data.getKeys(false).forEach(String -> loadUser(String));
        Bukkit.getOnlinePlayers().forEach(Player -> loadUser(Player));

         core.getLogger().info("LOADED ALL USERS!");
    }

    public User loadUser(Player p) {
        User user = new User(p.getUniqueId(),(int) data.get(p.getUniqueId() + ".coins"), core.getRankManager().getRank(data.get(p.getUniqueId() + ".rank")));
        users.add(user);
        return user;
    }
    public User loadUser(String s) {
        User user = new User(UUID.fromString(s) ,(int) data.get(s + ".coins"), core.getRankManager().getRank(data.get(s + ".rank")));
        users.add(user);
        return user;
    }


    public User loadUser(User u) {
        users.add(u);
        return u;
    }


    public User addUser(Player player) {
        return loadUser(player);
    }

    public User addUser(User u) {
        return loadUser(u);
    }

    public void removeUser(User u) {
        saveUser(u);
    }

    public void removeUser(Player player) {
        User u = getUser(player);
        saveUser(u);
    }

    public Set<User> getUsers() {
        return users;
    }

    public User getUser(Player player) {
        return users.stream().filter(user -> Bukkit.getPlayer(user.getUUID()).equals(player.getUniqueId())).findAny().orElse(null);
    }
    public User getUser(UUID id) {
        return users.stream().filter(user -> Bukkit.getPlayer(id).getUniqueId().equals(id)).findAny().orElse(null);
    }

    public void saveUser(User u) {
        data.set(u.getUUID() + ".coins", u.getCoins());
        data.set(u.getUUID() + ".rank", u.getRank().getName());
    }

    public void saveUsers() {
        users.forEach(this::saveUser);
        core.getLogger().info("SAVED ALL USERS!");
    }

    public Rank getUserRank(String s) {
        Rank r = core.getRankManager().getRank(s);
        if(r.equals(null)) return null;

        return r;
    }

    public Rank getUserRank(User u) {
        Rank r = core.getRankManager().getRank(data.get(u.getUUID() + ".rank"));
        if(r.equals(null)) return null;

        return r;
    }


}
