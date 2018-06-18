package me.mao.minigame.commands.arenaCommands;

import me.mao.minigame.Core;
import me.mao.minigame.api.coreCommand.CoreCommand;
import me.mao.minigame.api.utils.ChatUtils;
import me.mao.minigame.arenaSystem.Arena;
import me.mao.minigame.arenaSystem.ArenaManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Join extends CoreCommand {

    private Core core;

    public Join(Core core) {
        super(core, "join", "j");
        this.core = core;
    }

    public boolean handleCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if(args.length == 0) {
            player.sendMessage(ChatUtils.colorize("&c/join <arena>"));
            return true;
        }

        ArenaManager am = new ArenaManager(core);
        Arena arena = am.getArena(args[0]);

        if(arena == null) {
            player.sendMessage(ChatUtils.colorize("&cThat arena dosent exist!"));
            return true;
        }

        arena.addPlayer(player);

        return false;
    }
}
