package me.mao.minigame.commands.arenaCommands;

import me.mao.minigame.Core;
import me.mao.minigame.api.utils.ChatUtils;
import me.mao.minigame.arena.Arena;
import me.mao.minigame.arena.spawn.Spawn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArenaSpawns implements CommandExecutor {

    private Core core = Core.getInstance();


    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)) return true;

        Player p = (Player) sender;

        if(args.length < 1) {
            p.sendMessage(ChatUtils.colorize("&c/arenaspawns <arena>"));
            return true;
        }

        Arena a = core.getArenaManager().getArena(args[0]);
        if(a == null) {
            p.sendMessage(ChatUtils.colorize("&cThat Arena dosent exist!"));
            return true;
        }

        for(Spawn sp : a.getSpawns()) {
            p.sendMessage(ChatUtils.colorize("&aSpawns: &7" + a.getSpawns().size()));
            p.sendMessage(ChatUtils.colorize("&aLocations: &7" + sp.getLocation().toString()));
            p.sendMessage(ChatUtils.colorize("&Players: &7" + sp.getPlayer()));
        }
        //p.sendMessage(ChatUtils.colorize("&a You have been sent to the arena &7" + args[0]));
        return true;
    }
}
