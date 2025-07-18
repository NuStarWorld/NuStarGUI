package top.nustar.nustargui.entity;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Map;


/**
 * 菜单模板类
 * 该类用于创建菜单模板，具体使用要配合AbsNuStarGui使用
 * clone容器对象后进行操作
 */
@Getter
@SuppressWarnings("unused")
public class MenuTemplate {
    private final Inventory inventory;
    private final String menuName;
    private final String menuTitle;
    private final Map<String, NuStarMenuButton> buttons;

    public MenuTemplate(Inventory inventory, String menuName,String menuTitle, Map<String, NuStarMenuButton> buttons) {
        this.inventory = inventory;
        this.menuName = menuName;
        this.buttons = buttons;
        this.menuTitle = menuTitle;
    }

    public Inventory cloneInventory() {
        Inventory cloneInv = Bukkit.createInventory(inventory.getHolder(), inventory.getSize(), getMenuName());
        cloneInv.setContents(inventory.getContents());
        return cloneInv;
    }

    public void open(Player player) {
        player.openInventory(this.inventory);
    }
}
