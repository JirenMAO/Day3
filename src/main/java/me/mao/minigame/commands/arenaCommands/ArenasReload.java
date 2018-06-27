package me.mao.minigame.commands.arenaCommands;

import me.mao.minigame.Core;

import me.mao.minigame.api.utils.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArenasReload implements CommandExecutor {

    private Core core = Core.getInstance();


    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)) return true;

        Player p = (Player) sender;

        if(args.length != 0) {
            p.sendMessage(ChatUtils.colorize("&c/areload"));
            return true;
        }
        core.getArenaManager().getArenas().clear();
        core.getArenaManager().loadArenas();
        p.sendMessage(ChatUtils.colorize("&b&lsuccesufly reloaded all the arenas!"));
        return true;
    }
}
