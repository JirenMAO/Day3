package me.mao.minigame.commands;

import me.mao.minigame.Core;
import me.mao.minigame.api.coreCommand.CoreCommand;
import me.mao.minigame.api.utils.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand extends CoreCommand {

    private Core core;

    public TestCommand(Core core) {
        super(core, "heal", "healmebitch", "IM FUCKING DIEING");
        this.core = core;
    }

    public boolean handleCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) return false;

        Player p = (Player) sender;
        if(args.length != 0) return false;

        p.setHealth(20.0);
        p.sendMessage(ChatUtils.colorize("&6&lWorked!"));

        return false;
    }
}
