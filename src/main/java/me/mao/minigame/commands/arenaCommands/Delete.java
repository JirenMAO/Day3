package me.mao.minigame.commands.arenaCommands;

import me.mao.minigame.Core;
import me.mao.minigame.api.coreCommand.CoreCommand;
import me.mao.minigame.api.utils.ChatUtils;
import me.mao.minigame.arena.Arena;
import me.mao.minigame.arena.ArenaManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Delete extends CoreCommand {

    private Core core;

    public Delete(Core core) {
        super(core, "delete", "remove");
        this.core = core;
    }

    @Override
    public boolean handleCommand(CommandSender sender, Command cmd, String label, String[] args) throws Exception {
        Player p = (Player) sender;
        if(args.length < 1) {
            p.sendMessage(ChatUtils.colorize("&c/delete <arena>"));
            return true;
        }

        ArenaManager am = core.getArenaManager();
        Arena arena = am.getArena(args[0]);
        if(arena == null) {
            p.sendMessage(ChatUtils.colorize("&cThat arena dosent exist!"));
            return true;
        }

        core.getArenaManager().removeArena(arena);
        core.getArenaManager().loadArenas();

        p.sendMessage(ChatUtils.colorize("&3Deleted The arena &7" + args[0]));

        return true;
    }
}
