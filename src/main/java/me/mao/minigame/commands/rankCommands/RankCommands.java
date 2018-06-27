package me.mao.minigame.commands.rankCommands;


import me.mao.minigame.Core;
import me.mao.minigame.api.coreCommand.CoreCommand;
import me.mao.minigame.api.utils.ChatUtils;
import me.mao.minigame.user.User;
import me.mao.minigame.user.rank.Rank;
import me.mao.minigame.user.rank.RankManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class RankCommands extends CoreCommand {

    private Core core;

    public RankCommands(Core core) {
        super(core, "rank", "mr");
        this.core = core;
    }

    public boolean handleCommand(CommandSender sender, Command cmd, String label, String[] args) throws Exception {

        block71:
        {
            if (!(sender instanceof Player)) {
                return false;
            }
            final Player p = (Player) sender;
             RankManager rankManager = core.getRankManager();

             User u = core.getUserManager().getUser(p);

            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("help")) {
                    sender.sendMessage((Object) ChatColor.DARK_AQUA + "             ");
                    sender.sendMessage((Object) ChatColor.DARK_AQUA + "Help             ");
                    sender.sendMessage((Object) ChatColor.GRAY + " - /rank help");
                    sender.sendMessage((Object) ChatColor.GRAY + " - /rank list");
                    sender.sendMessage((Object) ChatColor.GRAY + " - /rank info <rank>");
                    sender.sendMessage((Object) ChatColor.GRAY + " - /rank remove <rank>");
                    sender.sendMessage((Object) ChatColor.GRAY + " - /rank create <name> <prefix>");
                    sender.sendMessage((Object) ChatColor.GRAY + " - /rank setprefix <rank> <prefix>");
                    sender.sendMessage((Object) ChatColor.GRAY + " - /rank addpermission <rank> <permission>");
                    sender.sendMessage((Object) ChatColor.GRAY + " - /rank removepermission <rank> <permission>");
                    sender.sendMessage((Object) ChatColor.GRAY + " - /rank set <player> <rank>");
                    sender.sendMessage((Object) ChatColor.DARK_AQUA + "             ");
                } else if (args[0].equalsIgnoreCase("list")) {
                    sender.sendMessage((Object) ChatColor.DARK_AQUA + "             ");
                    sender.sendMessage((Object) ChatColor.DARK_AQUA + "Ranks: ");
                    for (Rank rank : rankManager.getRanks()) {
                        sender.sendMessage((Object) ChatColor.GRAY + " - #" + " " + rank.getName());
                    }
                    sender.sendMessage((Object) ChatColor.DARK_AQUA + "             ");
                } else {
                    sender.sendMessage((Object) ChatColor.RED + "Command was not found. Type '/rank help' for help.");
                }
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("info")) {
                    if (rankManager.getRank(args[1]) != null) {
                        Rank rank = rankManager.getRank(args[1]);
                        sender.sendMessage((Object) ChatColor.DARK_AQUA + "             ");
                        sender.sendMessage((Object) ChatColor.DARK_AQUA + "Rank " + rank.getName() + ": ");
                        sender.sendMessage((Object) ChatColor.GRAY + " - Name: " + rank.getName());
                        sender.sendMessage((Object) ChatColor.GRAY + " - Prefix: " + ChatUtils.colorize(rank.getPrefix()));
                        sender.sendMessage((Object) ChatColor.GRAY + " - Permissions: ");
                        for(String s : rank.getPermissions()) {
                            sender.sendMessage((Object) ChatColor.GRAY + "    - " + s);
                        }
                        sender.sendMessage((Object) ChatColor.DARK_AQUA + "             ");
                        return true;
                    } else {
                        sender.sendMessage((Object) ChatColor.RED + "Rank not found.");
                    }
                }

                if (args[0].equalsIgnoreCase("remove")) {
                    if (rankManager.getRank(args[1]) != null) {
                        Rank rank = rankManager.getRank(args[1]);
                        rankManager.removeRank(rank);
                        rankManager.loadRanks();
                        sender.sendMessage((Object) ChatColor.DARK_AQUA + "             ");
                        sender.sendMessage(ChatColor.DARK_AQUA + "Rank " + ChatColor.GRAY + args[1] + " has been deleted!");
                        sender.sendMessage((Object) ChatColor.DARK_AQUA + "             ");
                    } else {
                        sender.sendMessage((Object) ChatColor.RED + "Rank not found.");
                    }
                } else if (!args[0].equalsIgnoreCase("remove")) {
                    sender.sendMessage((Object) ChatColor.RED + "Command not found. Type '/rank help' for help.");
                }
            } else if (args.length == 3) {
                if (args[0].equalsIgnoreCase("create")) {
                    final String name = args[1];
                    if (rankManager.getRank(name) == null) {

                        new BukkitRunnable() {

                            public void run() {
                                sender.sendMessage((Object) ChatColor.DARK_AQUA + "             ");
                                p.sendMessage((Object) ChatColor.GRAY + "Creating rank...");
                                Rank rank = new Rank(name, args[2], new ArrayList<String>());
                                rankManager.loadRank(rank);
                                rankManager.saveRank(rank);
                                p.sendMessage((Object) ChatColor.GRAY + "Rank " + (Object) ChatColor.DARK_AQUA + name + (Object) ChatColor.GRAY + " has created.");
                                sender.sendMessage((Object) ChatColor.DARK_AQUA + "             ");
                            }
                        }.runTaskAsynchronously((Plugin) Core.getInstance());
                        break block71;
                    } else {
                        sender.sendMessage((Object) ChatColor.RED + "This rank name does already exist.");
                    }
                } else if (args[0].equalsIgnoreCase("set")) {
                    Player target = Bukkit.getPlayer(args[1]);

                        User user = core.getUserManager().loadUser(target);

                        if (rankManager.getRank(args[2]) != null) {
                            Rank rank = rankManager.getRank(args[2]);
                            user.setRank(rank);
                            core.getUserManager().loadUser(user);
                            core.getUserManager().saveUser(user);
                            sender.sendMessage((Object) ChatColor.DARK_AQUA + "             ");
                            sender.sendMessage((Object) ChatColor.GRAY + "Player's rank has set to " + (Object) ChatColor.DARK_AQUA + rank.getName().toLowerCase() + (Object) ChatColor.GRAY + ".");
                            sender.sendMessage((Object) ChatColor.DARK_AQUA + "             ");
                        } else {
                            sender.sendMessage((Object) ChatColor.RED + "Rank not found.");
                        }

                }  else if (args[0].equalsIgnoreCase("setprefix")) {
                    if (rankManager.getRank(args[1]) != null) {
                        Rank rank = rankManager.getRank(args[1]);
                        String prefix = args[2];
                        rank.setPrefix(prefix);
                        rankManager.loadRank(rank);
                        rankManager.saveRank(rank);
                        sender.sendMessage((Object) ChatColor.DARK_AQUA + "             ");
                        sender.sendMessage((Object) ChatColor.GRAY + "Rank prefix has been set.");
                        sender.sendMessage((Object) ChatColor.DARK_AQUA + "             ");
                    } else {
                        sender.sendMessage((Object) ChatColor.RED + "Rank not found.");
                    }

                } else if (args[0].equalsIgnoreCase("addpermission") || args[0].equalsIgnoreCase("addperm")) {
                    if (rankManager.getRank(args[1]) != null) {
                        Rank rank = rankManager.getRank(args[1]);
                        if (!rank.getPermissions().contains(args[2])) {
                            rank.addPermission(args[2]);
                            rankManager.loadRank(rank);
                            rankManager.saveRank(rank);
                            sender.sendMessage((Object) ChatColor.DARK_AQUA + "             ");
                            sender.sendMessage((Object) ChatColor.GRAY + "Permission added to rank.");
                            sender.sendMessage((Object) ChatColor.DARK_AQUA + "             ");
                        } else {
                            sender.sendMessage((Object) ChatColor.RED + "This permission does already exist.");
                        }
                    } else {
                        sender.sendMessage((Object) ChatColor.RED + "Rank not found.");
                    }
                } else if (args[0].equalsIgnoreCase("removepermission") || args[0].equalsIgnoreCase("removeperm")) {
                    if (rankManager.getRank(args[1]) != null) {
                        Rank rank = rankManager.getRank(args[1]);
                        if (rank.getPermissions().contains(args[2])) {
                            rank.removePermission(args[2]);
                            rankManager.loadRank(rank);
                            rankManager.saveRank(rank);
                            sender.sendMessage((Object) ChatColor.DARK_AQUA + "             ");
                            sender.sendMessage((Object) ChatColor.GRAY + "Permission removed to rank.");
                            sender.sendMessage((Object) ChatColor.DARK_AQUA + "             ");
                        } else {
                            sender.sendMessage((Object) ChatColor.RED + "This permission does not exist.");
                        }
                    } else {
                        sender.sendMessage((Object) ChatColor.RED + "Rank not found.");
                    }
                } else {
                    sender.sendMessage((Object) ChatColor.RED + "Command not found. Type '/rank help' for help.");
                }
            } else {
                sender.sendMessage((Object) ChatColor.RED + "Command not found. Type '/rank help' for help.");
            }
        }
        return true;
    }

}
