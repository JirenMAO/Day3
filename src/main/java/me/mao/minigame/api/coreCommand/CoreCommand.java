package me.mao.minigame.api.coreCommand;

import me.mao.minigame.Core;
import me.mao.minigame.api.utils.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public abstract class CoreCommand implements CommandExecutor {

    private Core core;

    public CoreCommand(Core core, String... cmds) {
        this.core = core;
        if(cmds != null) {
            registerCommands(cmds);
        }
    }


    private void registerCommands(String[] cmds){
        for(String cmd : cmds) {
            try {
                core.getCommand(cmd).setExecutor(this);
                core.getCommand(cmd).setPermissionMessage(ChatUtils.colorize("&4You don't have permission to execute this command!"));
            } catch(Exception e) {
                core.getLogger().severe("Attempted command register failed: \"" + cmd + "\" is not registered.");
            }
        }
    }

    @Override
    public final boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        try {
            return handleCommand(sender, cmd, label, args);
        } catch(Exception e) {
            String message = "Exception thrown on command: " + cmd.getName() + "\nLabel: " + label;
            message += "\nArgs: ";

            for(String arg : args) {
                message += arg + " ";
            }
            core.getLogger().severe(e + message);

            core.getLogger().severe("Unhandled exception on Command! Please check the error log for more information!");;
            return false;
        }
    }

    public boolean handleCommand(CommandSender sender, Command cmd, String label, String[] args) throws Exception{
        return false;
    }

}
