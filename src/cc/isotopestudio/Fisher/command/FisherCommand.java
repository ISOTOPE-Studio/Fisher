package cc.isotopestudio.Fisher.command;

import cc.isotopestudio.Fisher.Pool;
import cc.isotopestudio.Fisher.listener.AdminListener;
import cc.isotopestudio.Fisher.util.S;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;

/*
 * Created by Mars on 8/29/2016.
 * Copyright ISOTOPE Studio
 */
public class FisherCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("fisher")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(S.toPrefixRed("���ִ�е�����"));
                return true;
            }
            Player player = (Player) sender;
            if (!player.hasPermission("fisher.admin")) {
                player.sendMessage(S.toPrefixRed("��û��Ȩ��"));
                return true;
            }
            if (args.length < 1) {
                player.sendMessage(S.toPrefixGreen("�����˵�"));
                player.sendMessage(S.toYellow("/" + label + " create <����> - ��������"));
                return true;
            }
            if (args[0].equalsIgnoreCase("create")) {
                if (!(AdminListener.createPos1.containsKey(player)
                        && AdminListener.createPos2.containsKey(player))) {
                    player.sendMessage(S.toPrefixRed("��û�����ú���������"));
                    return true;
                }
                if (!AdminListener.createPos1.get(player).getWorld().equals(AdminListener.createPos2.get(player).getWorld())) {
                    player.sendMessage(S.toPrefixRed("�������겻��һ������"));
                    return true;
                }
                if (args.length < 2) {
                    player.sendMessage(S.toYellow("/" + label + " create <����> - ��������"));
                    return true;
                }
                Location oldPos1 = AdminListener.createPos1.remove(player);
                Location oldPos2 = AdminListener.createPos2.remove(player);

                Location pos1 = new Location(oldPos1.getWorld(),
                        getSmaller(oldPos1.getBlockX(), oldPos2.getBlockX()),
                        getSmaller(oldPos1.getBlockY(), oldPos2.getBlockY()),
                        getSmaller(oldPos1.getBlockZ(), oldPos2.getBlockZ()));
                Location pos2 = new Location(oldPos1.getWorld(),
                        getLarger(oldPos1.getBlockX(), oldPos2.getBlockX()),
                        getLarger(oldPos1.getBlockY(), oldPos2.getBlockY()),
                        getLarger(oldPos1.getBlockZ(), oldPos2.getBlockZ()));

                Pool.pools.put(args[1], new Pool(args[1], pos1, pos2, true));

                player.sendMessage(S.toPrefixGreen("�����ɹ�"));
                player.sendMessage(S.toYellow("����: " + args[1]));
                player.sendMessage(S.toYellow("����1: " +
                        "X " + pos1.getBlockX() + ", Y " + pos1.getBlockY() + ", Z " + pos1.getBlockZ()));
                player.sendMessage(S.toYellow("����2: " +
                        "X " + pos2.getBlockX() + ", Y " + pos2.getBlockY() + ", Z " + pos2.getBlockZ()));

                return true;
            }
        }
        return false;
    }

    @Contract(pure = true)
    private int getLarger(int a, int b) {
        return a > b ? a : b;
    }

    @Contract(pure = true)
    private int getSmaller(int a, int b) {
        return a < b ? a : b;
    }
}
