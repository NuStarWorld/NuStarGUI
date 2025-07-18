package top.nustar.nustargui.entity;

import lombok.Data;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@Data
@SuppressWarnings("unused")
public abstract class AbsNuStarGui {
    protected String title;
    protected String menuName;
    protected Inventory templateInventory;
    protected int currentPage = 1;

    public AbsNuStarGui(String title, String menuName, Inventory templateInventory) {
        this.title = title;
        this.menuName = menuName;
        this.templateInventory = templateInventory;
    }

    abstract public Inventory refresh(Player player);

    abstract public boolean hasNextPage();

    public void open(Player player) {
        currentPage = 1;
        player.openInventory(refresh(player));
    }


    public void nextPage(Player player) {
        currentPage++;
        player.openInventory(refresh(player));
    }

    public void lastPage(Player player) {
        if (currentPage > 1) {
            currentPage--;
        }
        player.openInventory(refresh(player));
    }

    public int calculateAvailableSlots(Inventory template) {
        int count = 0;
        for (ItemStack item : template.getContents()) {
            if (item == null) count++;
        }
        return count;
    }
}
