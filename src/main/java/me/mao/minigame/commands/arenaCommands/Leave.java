package me.mao.minigame.commands.arenaCommands;

import me.mao.minigame.Core;
import me.mao.minigame.api.coreCommand.CoreCommand;
import me.mao.minigame.api.utils.ChatUtils;
import me.mao.minigame.arenaSystem.Arena;
import me.mao.minigame.arenaSystem.ArenaManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Leave extends CoreCommand {

    private Core core;

    public Leave(Core core) {
        super(core, "leave", "l");
        this.core = core;
    }

    public boolean handleCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if(args.length != 0) {
            return true;
        }

        ArenaManager am = new ArenaManager(core);
        Arena arena = am.getArena(player);

        if(arena == null) {
            player.sendMessage(ChatUtils.colorize("&cYou are not in an arena!"));
            return true;
        }

        arena.removePlayer(player);

        return false;
    }

}
