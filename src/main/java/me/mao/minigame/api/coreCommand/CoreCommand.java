package me.mao.minigame.api.coreCommand;

import me.mao.minigame.Core;
import me.mao.minigame.api.utils.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class CoreCommand implements CommandExecutor {

    private Core core;

    public CoreCommand(Core core, String... cmds) {
        this.core = core;
        if (cmds != null) {
            registerCommands(cmds);
        }
    }


    private void registerCommands(String[] cmds) {
        for (String cmd : cmds) {
            try {
                core.getCommand(cmd).setExecutor(this);
                core.getCommand(cmd).setPermissionMessage(ChatUtils.colorize("&4You don't have permission to execute this command!"));
            } catch (Exception e) {
                core.getLogger().severe("Attempted command register failed: \"" + cmd + "\" is not registered.");
            }
        }
    }

    @Override
    public final boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        try {
            return handleCommand(sender, cmd, label, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;

    }

    public boolean handleCommand(CommandSender sender, Command cmd, String label, String[] args) throws Exception {
        if(!(sender instanceof Player)) return true;
        return false;
    }

}
