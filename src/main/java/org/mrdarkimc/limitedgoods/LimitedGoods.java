package org.mrdarkimc.limitedgoods;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.trait.TraitInfo;
import org.bukkit.plugin.java.JavaPlugin;
import org.mrdarkimc.SatanicLib.SatanicLib;
import org.mrdarkimc.SatanicLib.configsetups.Configs;
import org.mrdarkimc.limitedgoods.commands.Command;
import org.mrdarkimc.limitedgoods.cooldowns.CooldownHandler;
import org.mrdarkimc.limitedgoods.traits.*;
import org.mrdarkimc.limitedgoods.menus.MenuList;
import org.mrdarkimc.limitedgoods.utils.Utils;

import java.util.NoSuchElementException;

public final class LimitedGoods extends JavaPlugin {
    public static Configs config;
    public static Configs configSaves;
    private static LimitedGoods instance;
    public static boolean isShopActive = false;
    public MenuList menus;
    public static LimitedGoods getInstance(){
        if (instance == null){
            throw new NoSuchElementException("[LimitedGoods] Плагин не работает. Главный класс null. Вероятнее произошла ошибка при запуске");
        }
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        SatanicLib.setupLib(this);
        config = Configs.Defaults.setupConfig();
        configSaves = Configs.Defaults.setupItemStackSaver();
        getServer().getPluginCommand("goods").setExecutor(new Command());
        menus = new MenuList();
        new CooldownHandler();
        setupTraits();

        // Plugin startup logic
        Utils.startUp("LimitedGoods");


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public void setupTraits(){
        CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(Farmer.class));
        CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(Archer.class));
        CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(Armorsmith.class));
        CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(Armory.class));
        CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(Ender.class));
        CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(Witch.class));
        CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(Librarian.class));

    }
}
