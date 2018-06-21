package me.mao.minigame.commands.arenaCommands;

import me.mao.minigame.Core;
import me.mao.minigame.api.coreCommand.CoreCommand;
import me.mao.minigame.api.utils.ChatUtils;
import me.mao.minigame.arena.Arena;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Join extends CoreCommand {

    private Core core;

    public Join(Core core) {
        super(core, "join", "j");
        this.core = core;
    }

    @Override
    public boolean handleCommand(CommandSender sender, Command cmd, String label, String[] args) throws Exception {
        Player p = (Player) sender;
        if(args.length < 1) {
            p.sendMessage(ChatUtils.colorize("&c/join <arena>"));
            return true;
        }

        Arena arena = core.getArenaManager().getArena(p);
        if(arena != null) {
            p.sendMessage(ChatUtils.colorize("&cYou are already in an arena!"));
            return true;
        }

        Arena a = core.getArenaManager().getArena(args[0]);

        if(a != null) {
            p.sendMessage(ChatUtils.colorize("&cThat arena dosent exist!"));
            return true;
        }

        arena.addPlayer(p);

        p.sendMessage(ChatUtils.colorize("&3Joined The arena &7" + args[0]));

        return true;
    }
}
