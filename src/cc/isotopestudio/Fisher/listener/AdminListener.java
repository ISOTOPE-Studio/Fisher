package cc.isotopestudio.Fisher.listener;

import cc.isotopestudio.Fisher.util.S;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mars on 8/29/2016.
 * Copyright ISOTOPE Studio
 */
public class AdminListener implements Listener {

    public static final Map<Player, Location> createPos1 = new HashMap<>();
    public static final Map<Player, Location> createPos2 = new HashMap<>();

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (!event.getPlayer().hasPermission("fisher.admin") || event.getMaterial() != Material.INK_SACK) {
            return;
        }
        Player player = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            createPos2.put(player, event.getClickedBlock().getLocation());
            player.sendMessage(S.toPrefixYellow("�����˵ڶ�������"));
            event.setCancelled(true);
        } else if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            createPos1.put(player, event.getClickedBlock().getLocation());
            player.sendMessage(S.toPrefixYellow("�����˵�һ������"));
            event.setCancelled(true);
        }
    }
}
