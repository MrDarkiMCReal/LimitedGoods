package org.mrdarkimc.limitedgoods.menus.interfaces;

import org.bukkit.entity.Player;

public interface Menu {
    void open(Player player);
    void createOrUpdate();
    void closeInventory();
}
