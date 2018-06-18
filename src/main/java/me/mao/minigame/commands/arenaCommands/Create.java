package me.mao.minigame.commands.arenaCommands;

import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import com.sk89q.worldedit.bukkit.selections.Selection;
import me.mao.minigame.Core;
import me.mao.minigame.api.coreCommand.CoreCommand;
import me.mao.minigame.api.utils.ChatUtils;
import me.mao.minigame.arenaSystem.Arena;
import me.mao.minigame.arenaSystem.ArenaManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Create extends CoreCommand {

    private Core core;

    public Create(Core core) {
        super(core, "create", "c");
        this.core = core;
    }

    public boolean handleCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if(args.length == 0) {
            player.sendMessage(ChatUtils.colorize("&c/create <arena>"));
            return true;
        }

        Selection sel = core.getWorldEditPlugin().getSelection(player);

        if(sel == null) {
            player.sendMessage(ChatUtils.colorize("&cYou have to make a world edit selection!"));
            return true;
        }

        ArenaManager am = new ArenaManager(core);
        Arena arena = am.getArena(args[0]);

        if(arena != null) {
            player.sendMessage(ChatUtils.colorize("&cThat arena already exists!"));
            return true;
        }

        core.getConfig().set(args[0] + ".cornerA", sel.getMinimumPoint());
        core.getConfig().set(args[0] + ".cornerB", sel.getMaximumPoint());
        core.saveConfig();

        am.addArena(arena);

        player.sendMessage(ChatUtils.colorize("&acreated the arena &7&l" + args[0] + " &a now you must setup the spawns!"));
        return true;

    }
}
