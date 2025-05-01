package top.nustar.nustargui.entity;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;


public abstract class AbsNuStarGui {
    protected String title;
    protected String menuName;
    protected Inventory templateInventory;
    public AbsNuStarGui(String title, String menuName, Inventory templateInventory) {
        this.title = title;
        this.menuName = menuName;
        this.templateInventory = templateInventory;
    }
    abstract public void open(Player player);
    abstract public Inventory refresh(Player player);

    public String getMenuName() {
        return menuName;
    }

    public String getTitle() {
        return title;
    }
}
