package top.nustar.nustargui;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import top.nustar.nustargui.entity.NuStarMenuButton;
import top.nustar.nustargui.entity.NuStarMenuHolder;
import top.nustar.nustargui.entity.MenuTemplate;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuUtil {
    /**
     * 添加菜单
     * 该方法通过读取一个YAML文件来创建一个菜单，包括菜单的布局和按钮
     * 菜单在游戏中的库存界面中创建，每个按钮都是根据YAML文件中的配置创建的
     *
     * @param menuFile 包含菜单配置的YAML文件
     */
    public static MenuTemplate buildMenu(File menuFile) {
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(menuFile);
        String menuName = yamlConfiguration.getString("MenuName");
        String menuTitle = yamlConfiguration.getString("Title");
        List<String> layout = yamlConfiguration.getStringList("Layout");
        Inventory inventory = Bukkit.createInventory(new NuStarMenuHolder(menuName), layout.size() * 9, menuName);
        Map<String, NuStarMenuButton> menuButtons = new HashMap<>();
        int line = 0;
        for (String layoutString : layout) {
            List<String> StringButtons = getStringButtons(layoutString);
            int index = 0;
            for (String button : StringButtons) {
                ConfigurationSection section = yamlConfiguration.getConfigurationSection("Buttons." + button);
                if (section != null) {
                    NuStarMenuButton menuButton = new NuStarMenuButton(section);
                    menuButtons.put(button, menuButton);
                    inventory.setItem(line * 9 + index, menuButton.crate());
                }
                index ++;
            }
            line ++;
        }
        return new MenuTemplate(inventory,menuName, menuTitle, menuButtons);
    }

    /**
     * 从布局字符串中提取字符串按钮
     * 该方法解析布局字符串，提取出每个被 '`' 包围的字符串作为按钮
     * 例如，对于字符串 "a`b`c"，它将返回 ["a", "b", "c"]
     *
     * @param layoutString 布局字符串，包含被 '`' 分隔的按钮文本
     * @return 包含提取出的字符串按钮的列表
     */
    private static List<String> getStringButtons(String layoutString) {
        char[] charButtons = layoutString.toCharArray();
        List<String> StringButtons = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        int firstSplit = 0;
        boolean isEnd = false;
        for (char charButton : charButtons) {
            stringBuilder.append(charButton);
        }
        for (int i = 0; i < charButtons.length; i++) {
            if (firstSplit != 0 && charButtons[i] == '`') {
                isEnd = true;
                StringButtons.add(stringBuilder.substring(firstSplit, i));
                firstSplit = 0;
                continue;
            }
            if (charButtons[i] == '`') {
                firstSplit = i + 1;
                continue;
            }
            if (firstSplit != 0 && !isEnd) {
                continue;
            }
            StringButtons.add(String.valueOf(charButtons[i]));
        }
        return StringButtons;
    }
}
