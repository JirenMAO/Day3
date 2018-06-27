package me.mao.minigame.commands.arenaCommands;

import me.mao.minigame.Core;
import me.mao.minigame.api.utils.ChatUtils;
import me.mao.minigame.arena.Arena;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArenaGameState implements CommandExecutor {

    private Core core = Core.getInstance();


    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)) return true;

        Player p = (Player) sender;

        if(args.length < 1) {
            p.sendMessage(ChatUtils.colorize("&c/arenastate <arena>"));
            return true;
        }

        Arena a = core.getArenaManager().getArena(args[0]);
        if(a == null) {
            p.sendMessage(ChatUtils.colorize("&cThat Arena dosent exist!"));
            return true;
        }

        p.sendMessage(ChatUtils.colorize("&a The game state of that arena is &7" + a.getState().toString()));
        return true;
    }
}
