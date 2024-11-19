package org.mrdarkimc.limitedgoods.traits;

import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.trait.Trait;
import net.citizensnpcs.api.trait.TraitName;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.mrdarkimc.SatanicLib.chat.SimpleMessage;
import org.mrdarkimc.limitedgoods.LimitedGoods;
import org.mrdarkimc.limitedgoods.menus.NPCMenu;
import org.mrdarkimc.limitedgoods.menus.interfaces.Menu;
import org.mrdarkimc.limitedgoods.utils.Utils;

import java.util.Map;

@TraitName("secretshop_armorsmith") //todo change this
public class Armorsmith extends Trait { //todo change this
    public Armorsmith() {
        super("secretshop_armorsmith"); //todo change this
    }
    private Menu menu;

    public Menu getMenu() {
        String traitname = getName().toLowerCase().replace("secretshop_","");
        String displayname = Utils.translateHex(LimitedGoods.config.get().getString("displaynames." + traitname));
        if (this.menu == null)
            this.menu = new NPCMenu(displayname, "trades.armorsmith"); //todo change this
        return this.menu;
    }

    @EventHandler
    public void onRightClick(NPCRightClickEvent event) {
        if (npc.equals(event.getNPC())) {
            Player player = event.getClicker();
            if (LimitedGoods.isShopActive) {
                getMenu().open(player);
            }else {
                SimpleMessage.ToPlayer.sendListMessages(player,LimitedGoods.config.get().getStringList("messages.shopDisabled"), Map.of());
            }
        }
    }

}
