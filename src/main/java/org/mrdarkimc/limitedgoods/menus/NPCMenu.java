package org.mrdarkimc.limitedgoods.menus;


import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.mrdarkimc.limitedgoods.LimitedGoods;
import org.mrdarkimc.limitedgoods.menus.interfaces.Menu;
import org.mrdarkimc.limitedgoods.utils.Utils;

import java.util.List;

public class NPCMenu implements Menu {
    Merchant merchant;
    List<MerchantRecipe> recipes;
    String displayname;
    String path;

    private final LimitedGoods goods = LimitedGoods.getInstance();
    public NPCMenu(String displayname, String path) {
        this.displayname = displayname;
        this.path = path;
        LimitedGoods.getInstance().menus.list.add(this);
        createOrUpdate();
    }
    public void createOrUpdate(){
        this.recipes = Utils.deserealizeRecipes(path);
        this.merchant = goods.getServer().createMerchant(displayname);
        this.merchant.setRecipes(recipes);
    }

    @Override
    public void closeInventory() {
        Player player = (Player)merchant.getTrader();
        if (player !=null){
            player.closeInventory();
        }
    }


    @Override
    public void open(Player player) {
        player.openMerchant(merchant,false);
    }
}
