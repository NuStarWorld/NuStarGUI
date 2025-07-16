package top.nustar.nustargui.entity;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class NuStarMenuButton {
    public String getName() {
        return name;
    }

    public String getMaterial() {
        return material;
    }

    public List<String> getLore() {
        return lore;
    }

    public int getData() {
        return data;
    }

    private final String name;
    private final String material;
    private final List<String> lore;
    private final int data;

    public ItemStack getButton() {
        return button;
    }

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
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_DESTROYS,
                ItemFlag.HIDE_ENCHANTS,
                ItemFlag.HIDE_PLACED_ON,
                ItemFlag.HIDE_POTION_EFFECTS,
                ItemFlag.HIDE_UNBREAKABLE);
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
}
