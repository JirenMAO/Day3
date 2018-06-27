package me.mao.minigame.user.scoreboard;

import me.mao.minigame.Core;


import me.mao.minigame.api.utils.ChatUtils;
import me.mao.minigame.user.User;
import org.bukkit.Bukkit;

import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;


import java.util.List;
import java.util.Map;

public class InfoScoreboard extends BukkitRunnable {

    private Core core;
    private Map<Integer, List<String>> content;
    private List<String> displayNames;
    private Map<Integer, String> ordinaryContent;
    private User u;
    private int maxTurns;

    public InfoScoreboard(Core core, Map<Integer, List<String>> content, List<String> displayName, Map<Integer, String> ordinaryContent, User u, int maxTurns) {
        this.core = core;
        this.content = content;
        this.maxTurns = maxTurns;
        this.displayNames = displayName;
        this.ordinaryContent = ordinaryContent;
        this.u = u;
    }

    int turn = 0;

    @Override
    public void run() {
        Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective o = sb.registerNewObjective("info", "dummy");
        o.setDisplaySlot(DisplaySlot.SIDEBAR);

        for(Team t : sb.getTeams()) {
            if(t != null && t.getName().equalsIgnoreCase(u.getRank().getName())) {
                t.addPlayer(u.getPlayer());
                return;
            }
        }

        Team team = sb.registerNewTeam(u.getRank().getName());
        team.setPrefix(ChatUtils.colorize(u.getRank().getPrefix() + " &r"));
        team.addPlayer(u.getPlayer());

        if(u == null) {
            cancel();
            return;
        }

        if (turn == maxTurns) {
            turn = 0;
        }

        o.setDisplayName(ChatUtils.colorize(displayNames.get(turn)));

        for (Map.Entry<Integer, String> entry : ordinaryContent.entrySet()) {
            String t = entry.getValue();
            o.getScore(ChatUtils.colorize(t)).setScore(entry.getKey());

        }

        for (Map.Entry<Integer, List<String>> entry : content.entrySet()) {
            if (!(entry.getValue().size() < turn)) {
                String t = entry.getValue().get(turn);
                o.getScore(ChatUtils.colorize(t)).setScore(entry.getKey());
            }

        }

        u.getPlayer().setScoreboard(sb);
        turn++;


    }
}
