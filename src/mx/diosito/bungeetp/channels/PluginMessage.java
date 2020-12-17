package mx.diosito.bungeetp.channels;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import mx.diosito.bungeetp.Main;
import mx.diosito.bungeetp.ScrollerInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.ArrayList;

public class PluginMessage implements PluginMessageListener {
    private Main plugin = Main.getInstance();

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) return;
        ArrayList<ItemStack> items = new ArrayList<>();
        ByteArrayDataInput input = ByteStreams.newDataInput(message);
        String subchannel = input.readUTF();
        if (subchannel.equalsIgnoreCase("PlayerList")) {
            String server = input.readUTF();
            String[] playerList = input.readUTF().split(", ");
            for (int i = 0; i < playerList.length; i++) {
                ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
                skullMeta.setOwner(playerList[i]);
                skullMeta.setDisplayName(playerList[i]);
                itemStack.setItemMeta(skullMeta);
                items.add(itemStack);
            }
            /* TO TEST
            for (int i = 1; i <= 80; i++) {
                Random rnd = new Random();
                String firstname = "Juan";
                String lastname = "Bernal";

                String result;

                result = Character.toString(firstname.charAt(0)); // First char
                if (lastname.length() > 5)
                    result += lastname.substring(0, 6);
                else
                    result += lastname;
                result += Integer.toString(rnd.nextInt(99));
                ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
                skullMeta.setOwner(result);
                skullMeta.setDisplayName(result);
                itemStack.setItemMeta(skullMeta);
                items.add(itemStack);
            }
            ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
            SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
            skullMeta.setOwner("Zane_Gamer");
            skullMeta.setDisplayName("Zane_Gamer");
            itemStack.setItemMeta(skullMeta);
            items.add(itemStack);
             */
            new ScrollerInventory(items, "Jugadores", player);
        }
    }

    public void getPlayerList(Player player) {
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("PlayerList");
        output.writeUTF("ALL");
        player.sendPluginMessage(plugin, "BungeeCord", output.toByteArray());
    }
}
