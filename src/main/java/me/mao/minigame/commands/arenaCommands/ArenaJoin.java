package me.mao.minigame.commands.arenaCommands;

import me.mao.minigame.Core;
import me.mao.minigame.api.utils.ChatUtils;
import me.mao.minigame.arena.Arena;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArenaJoin implements CommandExecutor {

    private Core core = Core.getInstance();


    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)) return true;

        Player p = (Player) sender;

        if(args.length < 1) {
            p.sendMessage(ChatUtils.colorize("&c/ajoin <arena>"));
            return true;
        }

        Arena a = core.getArenaManager().getArena(args[0]);
        if(a == null) {
            p.sendMessage(ChatUtils.colorize("&cThat Arena dosent exist!"));
            return true;
        }

        if(a.hasPlayer(p)) {
            p.sendMessage(ChatUtils.colorize("&cYou are already in that arena!"));
            return true;
        }

        if(core.getArenaManager().getArena(p) != null) {
            p.sendMessage(ChatUtils.colorize("&cYou are already in an arena!"));
            return true;
        }

        a.addPlayer(p);
        //p.sendMessage(ChatUtils.colorize("&a You have been sent to the arena &7" + args[0]));
        return true;
    }
}
