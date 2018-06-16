package me.mao.minigame.api.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatUtils {

    public static String colorize(String s) {
        String colored = ChatColor.translateAlternateColorCodes('&',s);
        return colored;
    }

    public static void broadcast(String message) {
        for(Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(colorize(message));
        }
    }

}
