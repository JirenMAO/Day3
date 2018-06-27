package me.mao.minigame.team;

import me.mao.minigame.Core;
import org.bukkit.entity.Player;
import me.mao.minigame.api.coreTeam.CoreTeam;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.*;

public class Team implements CoreTeam {

    private ChatColor color;
    private List<String> players;
    private String name;

    public Team(String name, List<String> players) {
        this.name = name;
        this.color = ChatColor.GOLD;
        this.players = players;
    }

    public ChatColor getColor() {
        return color;
    }

    public void setColor(ChatColor color) {
        this.color = color;
    }

    public List<Player> getPlayers() {
        List<Player> temp = new ArrayList<>();
        players.forEach(s -> temp.add(Bukkit.getPlayer(s)));
        return temp;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addPlayer(Player p) {
        players.add(p.getName());
    }

    public void removePlayer(Player p) {
        if(players.contains(p.getUniqueId())) {
            players.remove(p.getUniqueId());
        }
    }

}
