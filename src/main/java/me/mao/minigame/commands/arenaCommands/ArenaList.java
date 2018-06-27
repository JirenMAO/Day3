package me.mao.minigame.commands.arenaCommands;

import me.mao.minigame.Core;
import me.mao.minigame.api.utils.ChatUtils;
import me.mao.minigame.arena.Arena;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArenaList implements CommandExecutor {

    private Core core = Core.getInstance();


    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)) return true;

        Player p = (Player) sender;

        if(args.length != 0) {
            p.sendMessage(ChatUtils.colorize("&c/alist"));
            return true;
        }

        if(core.getArenaManager().getArenas().size() == 0) {
            p.sendMessage(ChatUtils.colorize("&cThere are no arenas currently!"));
            return true;
        }

        for(Arena a : core.getArenaManager().getArenas()) {
            p.sendMessage(ChatUtils.colorize("&b&lArenas: &7" + a.getName()));
        }
        return true;
    }
}
