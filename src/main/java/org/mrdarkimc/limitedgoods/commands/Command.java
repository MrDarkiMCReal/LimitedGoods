package org.mrdarkimc.limitedgoods.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.mrdarkimc.limitedgoods.LimitedGoods;


public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        if (commandSender instanceof Player && ((Player) commandSender).hasPermission("secretshop.admin")){
            Player player = (Player) commandSender;
            if (strings.length > 0) {
                switch (strings[0]){
                    case "save":
                        if (strings.length == 2) {
                            ItemStack stack = player.getInventory().getItemInMainHand();
                            LimitedGoods.configSaves.get().set("savedItems." + strings[1],stack);
                            LimitedGoods.configSaves.saveConfig();
                            player.sendMessage(ChatColor.YELLOW + "[Тайный Рынок] Предмет сохранен.");
                            return true;
                        }else{
                            player.sendMessage(ChatColor.RED + "Недостаточно аргументов");
                            player.sendMessage(ChatColor.RED + "Сохранение предмета /goods save <название>");
                            player.sendMessage(ChatColor.RED + "Название должно быть 1м словом.");
                            player.sendMessage(ChatColor.GREEN + "Пример: " + ChatColor.GRAY + "/goods save superSword");
                            return true;
                        }
                    case "reload": //todo
                        LimitedGoods.configSaves.reloadConfig();
                        LimitedGoods.config.reloadConfig();
                        player.sendMessage(ChatColor.YELLOW + "[Тайный Рынок] Конфиг перезагружен.");
                        player.sendMessage(ChatColor.YELLOW + "[Тайный Рынок] Внимание: команда перезагружает только конфиг");
                        player.sendMessage(ChatColor.YELLOW + "[Тайный Рынок] Работа магазина не приостановлена");
                        player.sendMessage(ChatColor.YELLOW + "[Тайный Рынок] Товары в магазине будут обновлены после ре-активации магазина");
                        return true;
                }
            }
        }
        return true;
    }
}
