package cc.isotopestudio.Fisher.listener;

import cc.isotopestudio.Fisher.FishingPool;
import cc.isotopestudio.Fisher.Reward;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

/*
 * Created by Mars on 8/29/2016.
 * Copyright ISOTOPE Studio
 */
public class FishingListener implements Listener {

    @EventHandler
    public void onFishing(PlayerFishEvent event) {
        if (event.getState() != PlayerFishEvent.State.CAUGHT_FISH)
            return;
        Player player = event.getPlayer();
        FishingPool pool = FishingPool.getPool(event.getHook().getLocation());
        if (pool == null) return;
        Reward reward = pool.getRandomReward();
        if (reward == null) return;
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), reward.getCommand(player));
        player.sendMessage(reward.getMsg());
    }
/*
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Pool pool = Pool.getPool(player.getLocation());
        if (pool == null) return;
        player.sendMessage(pool.getName());
    }
*/
}
