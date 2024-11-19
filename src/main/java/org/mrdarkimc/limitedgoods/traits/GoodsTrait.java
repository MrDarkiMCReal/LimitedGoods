package org.mrdarkimc.limitedgoods.traits;

import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.trait.Trait;
import net.citizensnpcs.api.trait.TraitName;
import net.citizensnpcs.trait.HologramTrait;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.mrdarkimc.limitedgoods.LimitedGoods;
import org.mrdarkimc.limitedgoods.menus.NPCMenu;
import org.mrdarkimc.limitedgoods.menus.interfaces.Menu;


@TraitName("custom_shop")
public final class GoodsTrait extends Trait {

    private final Menu menu;
    private final LimitedGoods plugin;

    {
        this.plugin = LimitedGoods.getInstance();
        this.menu = new NPCMenu("Торговец", "trades");
    }

    public GoodsTrait() {
        super("custom_shop");
    }

    @EventHandler
    public void onRightClick(NPCRightClickEvent event) {
        if (npc.equals(event.getNPC())) {
            Player player = event.getClicker();
            if (LimitedGoods.isShopActive) {
                menu.open(player);
            }else {
                player.sendMessage("Shop is disabled for now");
            }

        }
    }

//    @Override
//    public void run() {
//        if (ticksBeforeUpdate > 0) {
//            ticksBeforeUpdate--;
//            return;
//        }
//
//        ticksBeforeUpdate = 20 * UPDATE_DELAY_SECONDS;
//
//        Plugin plugin = JavaPlugin.getPlugin(LimitedGoods.class);
//
//    }

    private void refreshPuppet(){//Bid bid) {
//        HologramTrait hologramTrait = npc.getOrAddTrait(HologramTrait.class);
//
//        hologramTrait.clear();
//        hologramTrait.addLine("{BEST_PLAYER.FOOTER_LINE}", new TranslatingHologramRenderer());
//        hologramTrait.addLine(bid != null ? bid.user().prefix() + bid.user().username() : "{BEST_PLAYER.NO_BEST_PLAYER}", new TranslatingHologramRenderer());
//        hologramTrait.addLine("{BEST_PLAYER.HEADER_LINE}", new TranslatingHologramRenderer());
//
//        SkinTrait skinTrait = npc.getOrAddTrait(SkinTrait.class);
//
//        if (bid != null) {
//            String currentSkin = bid.user().skin().orElse(bid.user().username());
//
//            if (skinTrait.getSkinName() == null || !skinTrait.getSkinName().equals(currentSkin))
//                skinTrait.setSkinName(currentSkin, true);
//        } else {
//            skinTrait.setSkinPersistent(NO_BID_SKIN_TEXTURE_ID, NO_BID_SKIN_TEXTURE_SIGNATURE, NO_BID_SKIN_TEXTURE_VALUE);
//        }
    }

    private static final class TranslatingHologramRenderer extends HologramTrait.TextDisplayRenderer {
//        @Override
//        public String getPerPlayerText(NPC npc, Player viewer) {
//            Locale locale = viewer.locale();
//
//            try {
//                locale =
//                        Client.instance()
//                                .usersRepository()
//                                .fetch(viewer.getName())
//                                .settings()
//                                .locale()
//                                .get();
//            } catch (ClientException e) {
//                JavaPlugin.getPlugin(SiliciumPuppets.class).getLogger().log(Level.WARNING, "Can't request user object", e);
//            }
//
//            return text.startsWith("{") && text.endsWith("}") ? new LocalizedText(
//                    "%s/general.yml".formatted(locale.getLanguage()),
//                    text.substring(1, text.length() - 1)
//            ).text() : text;
//        }
    }
}
