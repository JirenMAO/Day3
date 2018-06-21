package me.mao.minigame.commands.arenaCommands;

import me.mao.minigame.Core;
import me.mao.minigame.api.coreCommand.CoreCommand;
import me.mao.minigame.api.utils.ChatUtils;
import me.mao.minigame.arena.Arena;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Leave extends CoreCommand {

    private Core core;

    public Leave(Core core) {
        super(core, "leave", "l");
        this.core = core;
    }

    @Override
    public boolean handleCommand(CommandSender sender, Command cmd, String label, String[] args) throws Exception {
        Player p = (Player) sender;

        Arena arena = core.getArenaManager().getArena(p);
        if(arena == null) {
            p.sendMessage(ChatUtils.colorize("&cYou are not in an Arena!"));
            return true;
        }

        arena.removePlayer(p);

        p.sendMessage(ChatUtils.colorize("&3You have left the arena &7" + arena.getName()));

        return true;
    }
}
