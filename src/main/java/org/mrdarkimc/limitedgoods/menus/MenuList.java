package org.mrdarkimc.limitedgoods.menus;

import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.mrdarkimc.limitedgoods.menus.interfaces.Menu;
import org.mrdarkimc.limitedgoods.traits.IResetable;

import java.util.ArrayList;
import java.util.List;

public class MenuList implements IResetable {
    public List<Menu> list = new ArrayList<>();
    @Override
    public void reset() {
        list.forEach(Menu::createOrUpdate);
        list.forEach(Menu::closeInventory);
    }
}
