package me.mao.minigame.commands.arenaCommands;

import me.mao.minigame.Core;
import me.mao.minigame.api.utils.ChatUtils;
import me.mao.minigame.arena.Arena;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArenaLeave implements CommandExecutor {

    private Core core = Core.getInstance();

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)) return true;

        Player p = (Player) sender;

        if(args.length != 0) {
            p.sendMessage(ChatUtils.colorize("&c/aleave"));
            return true;
        }

        Arena a = core.getArenaManager().getArena(p);

        if(a == null) {
            p.sendMessage(ChatUtils.colorize("&cYou are not in an arena!"));
            return true;
        }

        a.removePlayer(p);
        return true;
    }
}
