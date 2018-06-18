package me.mao.minigame.commands.arenaCommands;

import com.sk89q.worldedit.bukkit.selections.Selection;
import me.mao.minigame.Core;
import me.mao.minigame.api.coreCommand.CoreCommand;
import me.mao.minigame.api.utils.ChatUtils;
import me.mao.minigame.arenaSystem.Arena;
import me.mao.minigame.arenaSystem.ArenaManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Delete extends CoreCommand {

    private Core core;

    public Delete(Core core) {
        super(core, "delete", "remove");
        this.core = core;
    }

    public boolean handleCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if(args.length == 0) {
            player.sendMessage(ChatUtils.colorize("&c/delete <arena>"));
            return true;
        }

        ArenaManager am = new ArenaManager(core);
        Arena arena = am.getArena(args[0]);

        if(arena == null) {
            player.sendMessage(ChatUtils.colorize("&cThat arena dosent exist!"));
            return true;
        }

        core.getConfig().set(args[0], null);
        core.saveConfig();
        am.removeArena(arena);

        player.sendMessage(ChatUtils.colorize("&adeleted the arena &7&l" + args[0] + " &a!"));
        return true;

    }

}
