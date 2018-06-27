package me.mao.minigame.commands.arenaCommands;

import me.mao.minigame.Core;

import me.mao.minigame.api.utils.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArenaHelp implements CommandExecutor {

    private Core core = Core.getInstance();


    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)) return true;

        Player p = (Player) sender;

        if(args.length != 0) {
            p.sendMessage(ChatUtils.colorize("&c/ahelp"));
            return true;
        }


        p.sendMessage(ChatUtils.colorize("&b&lHelp:"));
        p.sendMessage(ChatUtils.colorize(""));
        p.sendMessage(ChatUtils.colorize("&7Commands: &7"));
        p.sendMessage(ChatUtils.colorize("&7/acreate <arena>"));
        p.sendMessage(ChatUtils.colorize("&7/adelete <arena>"));
        p.sendMessage(ChatUtils.colorize("&7/ajoin <arena>"));
        p.sendMessage(ChatUtils.colorize("&7/aleave"));
        p.sendMessage(ChatUtils.colorize("&7/astart <arena>"));
        p.sendMessage(ChatUtils.colorize("&7/astop <arena>"));
        p.sendMessage(ChatUtils.colorize("&7/alist"));
        p.sendMessage(ChatUtils.colorize("&7/areload"));

        return true;
    }
}
