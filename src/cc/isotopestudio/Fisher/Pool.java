package cc.isotopestudio.Fisher;

import cc.isotopestudio.Fisher.util.ISOUtil;
import org.bukkit.Location;
import org.jetbrains.annotations.Nullable;

import java.util.*;

import static cc.isotopestudio.Fisher.Fisher.plugin;

/*
 * Created by Mars on 8/29/2016.
 * Copyright ISOTOPE Studio
 */
public class Pool {

    public static final Map<String, Pool> pools = new HashMap<>();

    private final String name;
    private final Location pos1;
    private final Location pos2;
    private final List<Reward> rewards = new ArrayList<>();

    public Pool(String name, Location pos1, Location pos2, boolean save) {
        this.name = name;
        this.pos1 = pos1;
        this.pos2 = pos2;
        if (save) {
            List<String> list = new ArrayList<>();
            list.add("give <player> stone 1|ʯͷ!");
            plugin.getPlayersData().set(name + ".pos1", ISOUtil.locationToString(pos1));
            plugin.getPlayersData().set(name + ".pos2", ISOUtil.locationToString(pos2));
            plugin.getPlayersData().set(name + ".reward", list);
            plugin.savePlayersData();
        }
    }

    public String getName() {
        return name;
    }

    private boolean isInPool(Location loc) {
        return !(!loc.getWorld().equals(pos1.getWorld()) ||
                loc.getBlockX() < pos1.getBlockX() || loc.getBlockX() > pos2.getBlockX() ||
                loc.getBlockY() < pos1.getBlockY() || loc.getBlockY() > pos2.getBlockY() ||
                loc.getBlockZ() < pos1.getBlockZ() || loc.getBlockZ() > pos2.getBlockZ());
    }

    public Reward getRandomReward() {
        Collections.shuffle(rewards);
        Iterator ite = rewards.iterator();
        if (ite.hasNext()) {
            return (Reward) ite.next();
        }
        return null;
    }

    @Nullable
    public static Pool getPool(Location loc) {
        for (Pool pool : pools.values()) {
            if (pool.isInPool(loc))
                return pool;
        }
        return null;
    }

    static void loadData() {
        for (String poolName : plugin.getPlayersData().getKeys(false)) {
            Location pos1 = ISOUtil.stringToLocation(plugin.getPlayersData().getString(poolName + ".pos1"));
            Location pos2 = ISOUtil.stringToLocation(plugin.getPlayersData().getString(poolName + ".pos2"));
            if (pos1 == null || pos2 == null) continue;
            List<String> rewardList = plugin.getPlayersData().getStringList(poolName + ".reward");
            Pool pool = new Pool(poolName, pos1, pos2, false);
            pools.put(poolName, pool);
            for (String reward : rewardList) {
                String[] line = reward.split("\\|");
                if (line.length != 2) continue;
                pool.rewards.add(new Reward(line[0], line[1]));
            }
        }
    }
}
