package cc.isotopestudio.Fisher;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Mars on 8/29/2016.
 * Copyright ISOTOPE Studio
 */
public class Pool {

    public static Map<String, Pool> pools = new HashMap<>();

    private final String name;
    private final Location pos1;
    private final Location pos2;
    private Set<Reward> rewards = new HashSet<>();

    public Pool(String name, Location pos1, Location pos2) {
        this.name = name;
        this.pos1 = pos1;
        this.pos2 = pos2;
    }

    public String getName() {
        return name;
    }

    private boolean isInPool(Player player) {
        Location loc = player.getLocation();
        return !(!loc.getWorld().equals(pos1.getWorld()) ||
                loc.getBlockX() < pos1.getBlockX() || loc.getBlockX() > pos2.getBlockX() ||
                loc.getBlockY() < pos1.getBlockY() || loc.getBlockY() > pos2.getBlockY() ||
                loc.getBlockZ() < pos1.getBlockZ() || loc.getBlockZ() > pos2.getBlockZ());
    }

    @Nullable
    public static Pool getPool(Player player) {
        for (Pool pool : pools.values()) {
            if (pool.isInPool(player))
                return pool;
        }
        return null;
    }
}
