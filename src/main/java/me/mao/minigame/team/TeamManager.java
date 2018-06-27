package me.mao.minigame.team;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import me.mao.minigame.Core;
import me.mao.minigame.team.data.TeamData;
import org.bukkit.ChatColor;

import java.util.*;

public class TeamManager {

    private Set<Team> teams = new HashSet<Team>();
    private Core core = Core.getInstance();
    private TeamData data;

    public void loadTeams() {
        data = new TeamData(core, "teams");
        data.getKeys(false).forEach(team -> loadTeam(team));
        core.getLogger().info("LOADED ALL TEAMS!");
    }

    public void loadTeam(String team) {
        Team t = new Team(team, data.getStringList(team + ".players"));
       teams.add(t);
       saveTeam(t);
    }


    public void saveTeam(Team t) {
        data.set(t.getName() + ".color", t.getColor());
        data.set(t.getName() + ".players", t.getPlayers());
    }

    public Team getTeam(String name) {
        return teams.stream().filter(t -> t.getName().equalsIgnoreCase(name)).findAny().orElse(null);
    }

    public Team getTeam(Player p) {
        return teams.stream().filter(t -> t.getPlayers().contains(p.getName())).findAny().orElse(null);
    }

    public void deleteTeam(Team t) {
        data.set(t.getName(), null);
    }

    public void saveTeams() {
        teams.forEach(this::saveTeam);
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public Team hasTeam(Player p) {
       return teams.stream().filter(t -> t.getPlayers().contains(p)).findAny().orElse(null);
    }

}
