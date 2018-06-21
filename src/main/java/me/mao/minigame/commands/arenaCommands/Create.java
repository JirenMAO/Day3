package me.mao.minigame.commands.arenaCommands;

import com.sk89q.worldedit.bukkit.selections.Selection;
import me.mao.minigame.Core;
import me.mao.minigame.api.coreCommand.CoreCommand;
import me.mao.minigame.api.utils.ChatUtils;
import me.mao.minigame.arena.Arena;
import me.mao.minigame.arena.ArenaManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Create extends CoreCommand {

    private Core core;

    public Create(Core core) {
        super(core, "create", "c");
        this.core = core;
    }

    @Override
    public boolean handleCommand(CommandSender sender, Command cmd, String label, String[] args) throws Exception {
        Player p = (Player) sender;
        if(args.length < 1) {
            p.sendMessage(ChatUtils.colorize("&c/create <arena>"));
            return true;
        }

        Selection s = core.getWorldEditPlugin().getSelection(p);

        if(s == null) {
            p.sendMessage(ChatUtils.colorize("&cYou must make a world edit selection first!"));
            return true;
        }

        ArenaManager am = core.getArenaManager();
        Arena arena = am.getArena(args[0]);

        if(arena != null) {
            p.sendMessage(ChatUtils.colorize("&cThat arena already exist!"));
            return true;
        }
        core.getArenaManager().loadArena(arena);
        p.sendMessage(ChatUtils.colorize("&3Created The arena &7" + args[0]));

        return true;
    }
}
