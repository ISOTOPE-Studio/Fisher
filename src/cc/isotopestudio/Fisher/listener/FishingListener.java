package cc.isotopestudio.Fisher.listener;

import cc.isotopestudio.Fisher.Pool;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Created by Mars on 8/29/2016.
 * Copyright ISOTOPE Studio
 */
public class FishingListener implements Listener {

    @EventHandler
    public void onRightClick(PlayerFishEvent event) {
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Pool pool = Pool.getPool(player);
        if (pool == null) return;
        player.sendMessage(pool.getName());
    }
}
