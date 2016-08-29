package cc.isotopestudio.Fisher.command;

import cc.isotopestudio.Fisher.S;
import cc.isotopestudio.Fisher.listener.AdminListener;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Mars on 8/29/2016.
 * Copyright ISOTOPE Studio
 */
public class FisherCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("fisher")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(S.toPrefixRed("玩家执行的命令"));
                return true;
            }
            Player player = (Player) sender;
            if (!player.hasPermission("fisher.admin")) {
                player.sendMessage(S.toPrefixRed("你没有权限"));
                return true;
            }
            if (args.length < 1) {
                player.sendMessage(S.toPrefixGreen("帮助菜单"));
                player.sendMessage(S.toYellow("/" + label + " create <名字> - 创建鱼塘"));
                return true;
            }
            if (args[0].equalsIgnoreCase("create")) {
                if (!(AdminListener.createPos1.containsKey(player)
                        && AdminListener.createPos2.containsKey(player))) {
                    player.sendMessage(S.toPrefixRed("你没有设置好两个坐标"));
                    return true;
                }
                if (args.length < 2) {
                    player.sendMessage(S.toYellow("/" + label + " create <名字> - 创建鱼塘"));
                    return true;
                }
                Location pos1 = AdminListener.createPos1.remove(player);
                Location pos2 = AdminListener.createPos2.remove(player);

                player.sendMessage(S.toPrefixGreen("创建成功"));
                player.sendMessage(S.toYellow("名字: " + args[1]));
                player.sendMessage(S.toYellow("坐标1: " + "X "+pos1.getBlockX()+ ", Y "+pos1.getBlockY()+ ", Z "+pos1.getBlockZ()));
                player.sendMessage(S.toYellow("坐标2: " + "X "+pos2.getBlockX()+ ", Y "+pos2.getBlockY()+ ", Z "+pos2.getBlockZ()));

                return true;
            }
        }
        return false;
    }
}
