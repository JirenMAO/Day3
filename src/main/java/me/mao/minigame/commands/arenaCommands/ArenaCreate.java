package me.mao.minigame.commands.arenaCommands;

import com.sk89q.worldedit.bukkit.selections.Selection;
import me.mao.minigame.Core;

import me.mao.minigame.api.utils.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArenaCreate implements CommandExecutor {

    private Core core = Core.getInstance();


    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)) return true;

        Player p = (Player) sender;

        if(args.length < 1) {
            p.sendMessage(ChatUtils.colorize("&c/acreate <arena>"));
            return true;
        }

        Selection sel = core.getWorldEditPlugin().getSelection(p);

        if(sel == null) {
            p.sendMessage(ChatUtils.colorize("&cYou have to make a wolrd edit selection first!"));
            return true;
        }

        core.getArenaManager().getData().set(args[0] + ".selection.world", sel.getWorld().getName());
        core.getArenaManager().getData().set(args[0] + ".selection.cornerA", sel.getMinimumPoint());
        core.getArenaManager().getData().set(args[0] + ".selection.cornerB", sel.getMaximumPoint());
        core.getArenaManager().getData().createConfigurationSection(args[0] + ".spawns");

        core.getArenaManager().loadArenas();

        p.sendMessage(ChatUtils.colorize("&aCreated the arena &7" + args[0]));
        return true;
    }
}
