package cc.isotopestudio.Fisher;

import cc.isotopestudio.Fisher.command.FisherCommand;
import cc.isotopestudio.Fisher.listener.AdminListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Fisher extends JavaPlugin {

    private static final String pluginName = "Fisher";
    static final String prefix = (new StringBuilder()).append(ChatColor.GOLD).append(ChatColor.BOLD).append("[")
            .append("钓鱼").append("]").append(ChatColor.RED).toString();

    @Override
    public void onEnable() {

        this.getCommand("Fisher").setExecutor(new FisherCommand());

        Bukkit.getPluginManager().registerEvents(new AdminListener(), this);

        getLogger().info(pluginName + "成功加载!");
        getLogger().info(pluginName + "由ISOTOPE Studio制作!");
        getLogger().info("http://isotopestudio.cc");
    }

    @Override
    public void onDisable() {
        getLogger().info(pluginName + "成功卸载!");
    }

}
