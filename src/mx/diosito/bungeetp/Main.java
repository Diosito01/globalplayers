package mx.diosito.bungeetp;

import mx.diosito.bungeetp.channels.PluginMessage;
import mx.diosito.bungeetp.commands.PlayerListCommand;
import mx.diosito.bungeetp.listeners.ClickEvents;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getPluginManager().registerEvents(new ClickEvents(), this);
        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        Bukkit.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new PluginMessage());

        Bukkit.getPluginCommand("players").setExecutor(new PlayerListCommand());
    }

    public static Main getInstance() {
        return instance;
    }
}