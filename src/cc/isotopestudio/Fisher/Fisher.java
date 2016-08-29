package cc.isotopestudio.Fisher;

import cc.isotopestudio.Fisher.command.FisherCommand;
import cc.isotopestudio.Fisher.listener.AdminListener;
import cc.isotopestudio.Fisher.listener.FishingListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Fisher extends JavaPlugin {

    private static final String pluginName = "Fisher";
    static final String prefix = (new StringBuilder()).append(ChatColor.GOLD).append(ChatColor.BOLD).append("[")
            .append("����").append("]").append(ChatColor.RED).toString();

    @Override
    public void onEnable() {

        this.getCommand("Fisher").setExecutor(new FisherCommand());

        Bukkit.getPluginManager().registerEvents(new AdminListener(), this);
        Bukkit.getPluginManager().registerEvents(new FishingListener(), this);

        getLogger().info(pluginName + "�ɹ�����!");
        getLogger().info(pluginName + "��ISOTOPE Studio����!");
        getLogger().info("http://isotopestudio.cc");
    }

    @Override
    public void onDisable() {
        getLogger().info(pluginName + "�ɹ�ж��!");
    }

}
