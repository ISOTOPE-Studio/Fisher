package cc.isotopestudio.Fisher;

import cc.isotopestudio.Fisher.command.FisherCommand;
import cc.isotopestudio.Fisher.listener.AdminListener;
import cc.isotopestudio.Fisher.listener.FishingListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Fisher extends JavaPlugin {

    public static Fisher plugin;

    private static final String pluginName = "Fisher";
    public static final String prefix = (new StringBuilder()).append(ChatColor.GOLD).append(ChatColor.BOLD).append("[")
            .append("����").append("]").append(ChatColor.RED).toString();

    @Override
    public void onEnable() {
        plugin = this;

        try {
            getPlayersData().save(dataFile);
        } catch (IOException e) {
            getLogger().info("�����ļ�����");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        this.getCommand("Fisher").setExecutor(new FisherCommand());

        Bukkit.getPluginManager().registerEvents(new AdminListener(), this);
        Bukkit.getPluginManager().registerEvents(new FishingListener(), this);

        Pool.loadData();

        getLogger().info(pluginName + "�ɹ�����!");
        getLogger().info(pluginName + "��ISOTOPE Studio����!");
        getLogger().info("http://isotopestudio.cc");
    }

    @Override
    public void onDisable() {
        getLogger().info(pluginName + "�ɹ�ж��!");
    }

    private File dataFile = null;
    private FileConfiguration data = null;

    private void reloadPlayersData() {
        if (dataFile == null) {
            dataFile = new File(getDataFolder(), "poolsData.yml");
        }
        data = YamlConfiguration.loadConfiguration(dataFile);
    }

    public FileConfiguration getPlayersData() {
        if (data == null) {
            reloadPlayersData();
        }
        return data;
    }

    public void savePlayersData() {
        if (data == null || dataFile == null) {
            return;
        }
        try {
            getPlayersData().save(dataFile);
        } catch (IOException ex) {
            getLogger().info("����ļ�����ʧ�ܣ�");
        }
    }

}
