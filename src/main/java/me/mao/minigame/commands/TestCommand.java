package me.mao.minigame.commands;

import me.mao.minigame.Core;
import me.mao.minigame.api.coreCommand.CoreCommand;
import me.mao.minigame.api.utils.ChatUtils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TestCommand extends CoreCommand {

    private Core core;

    public TestCommand(Core core) {
        super(core, "menchant");
        this.core = core;
    }

    public boolean handleCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) return false;

        Player p = (Player) sender;
        if(args.length < 1) return false;

        if(p.getItemInHand() == null || p.getItemInHand().getType() == Material.AIR) return true;

        ItemStack item = p.getItemInHand();
        item.addUnsafeEnchantment(Enchantment.FROST_WALKER, 20);

        p.sendMessage(ChatUtils.colorize("&6&lWorked!"));

        return false;
    }
}
