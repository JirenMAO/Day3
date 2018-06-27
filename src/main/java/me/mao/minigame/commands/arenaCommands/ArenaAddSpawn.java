package me.mao.minigame.commands.arenaCommands;

import me.mao.minigame.Core;
import me.mao.minigame.api.utils.ChatUtils;
import me.mao.minigame.arena.Arena;
import me.mao.minigame.arena.data.ArenaData;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArenaAddSpawn implements CommandExecutor {

    private Core core = Core.getInstance();

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) return true;

        Player p = (Player) sender;

        if (args.length < 1) {
            p.sendMessage(ChatUtils.colorize("&c/addspawn <arena>"));
            return true;
        }

        Arena a = core.getArenaManager().getArena(args[0]);
        if (a == null) {
            p.sendMessage(ChatUtils.colorize("&cThat Arena dosent exist!"));
            return true;
        }

        ArenaData data = core.getArenaManager().getData();

        Location roundedLocation = new Location(p.getLocation().getWorld(), Math.round(p.getLocation().getX()), Math.round(p.getLocation().getY()), Math.round(p.getLocation().getZ()));
        core.getArenaManager().getData().saveLocation(roundedLocation, core.getArenaManager().getData().createConfigurationSection(a.getName() + ".spawns" + "." + core.getArenaManager().getData().getSection(a.getName() + ".spawns").getKeys(false).size()));

        p.sendMessage(ChatUtils.colorize("&a You have added a new spawn to the arena &7" + args[0] + " &a do /areload to apply the changes!"));
        return true;

    }
}
