package top.nustar.nustargui.entity;

import lombok.Getter;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

@Getter
@SuppressWarnings("unused")
public class NuStarMenuButton {

    private final String name;
    private final String material;
    private final List<String> lore;
    private final int data;

    private final ItemStack button;

    public NuStarMenuButton(ConfigurationSection section) {
        this.name = section.getString("name");
        this.material = section.getString("material");
        this.lore = section.getStringList("lore");
        this.data = section.getInt("data", 0);
        button = crate();
    }

    public ItemStack crate() {
        ItemStack itemStack = new ItemStack(Material.getMaterial(this.material.toUpperCase()));
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.name);
        itemMeta.setLore(this.lore);
        itemStack.setItemMeta(itemMeta);
        itemStack.setDurability((short) data);
        return itemStack;
    }

    public ItemStack getPapiItem(Player player) {
        ItemStack itemStack = button.clone();
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (!itemMeta.hasLore()) return itemStack;
        List<String> lore1 = new ArrayList<>(itemMeta.getLore());
        lore1.replaceAll(text -> PlaceholderAPI.setPlaceholders(player, text));
        lore1.replaceAll(text -> text.replace("&", "§"));
        String itemName = itemMeta.getDisplayName().replaceAll("&", "§");
        itemMeta.setDisplayName(itemName);
        itemMeta.setLore(lore1);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack getButton() {
        return button.clone();
    }

    public List<String> getLore() {
        return new ArrayList<>(lore);
    }
}
