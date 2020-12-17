package mx.diosito.bungeetp.commands;

import mx.diosito.bungeetp.Main;
import mx.diosito.bungeetp.channels.PluginMessage;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerListCommand implements CommandExecutor {
    private Main plugin = Main.getInstance();
    private PluginMessage pluginMessage = new PluginMessage();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.hasPermission("bungeetp.mod")) return false;
            if (command.getName().equalsIgnoreCase("players")) {
                if (args.length == 0)
                    pluginMessage.getPlayerList(player);
                else
                    player.sendMessage(ChatColor.RED + "/players");
            }
        }
        return true;
    }
}
