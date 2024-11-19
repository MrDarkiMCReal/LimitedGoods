package org.mrdarkimc.limitedgoods.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.mrdarkimc.SatanicLib.ItemStackUtils.StackUtils;
import org.mrdarkimc.limitedgoods.LimitedGoods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Utils {
    private static final JavaPlugin plugin = LimitedGoods.getInstance();

    public static List<MerchantRecipe> deserealizeRecipes(String path) {
        List<MerchantRecipe> recipes = new ArrayList<>();
        List<Map<?, ?>> trades = LimitedGoods.config.get().getMapList(path);


        for (Map<?, ?> trade : trades) {
            String result = (String) trade.get("item");
            List<String> ingridients = (List<String>) trade.get("recipe");
            String[] arrayofRecipeItems = ingridients.toArray(new String[2]);

            MerchantRecipe recipeTemp = new MerchantRecipe(StackUtils.stringToItemStack(result,Map.of()), 1);
            for (String recipe : arrayofRecipeItems) {
                recipeTemp.addIngredient(StackUtils.stringToItemStack(recipe,Map.of()));
            }
            recipes.add(recipeTemp);
        }
        return recipes;
    }

    public static String translateHex(String message) {
        Pattern pattern = Pattern.compile("&#[0-9A-Fa-f]{6}");
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            String color = message.substring(matcher.start(), matcher.end());
            message = message.replace(color, net.md_5.bungee.api.ChatColor.of(color.replaceAll("&", "")) + "");
            matcher = pattern.matcher(message);
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void startUp(String pluginname) {
        System.out.println("=======================================================");
        System.out.println("  __  __      _____             _    _ __  __  _____ ");
        System.out.println(" |  \\/  |    |  __ \\           | |  (_)  \\/  |/ ____|");
        System.out.println(" | \\  / |_ __| |  | | __ _ _ __| | ___| \\  / | |     ");
        System.out.println(" | |\\/| | '__| |  | |/ _` | '__| |/ / | |\\/| | |     ");
        System.out.println(" | |  | | |  | |__| | (_| | |  |   <| | |  | | |____ ");
        System.out.println(" |_|  |_|_|  |_____/ \\__,_|_|  |_|\\_\\_|_|  |_|\\_____|");

        System.out.println("  _____                 _                                  _   ");
        System.out.println(" |  __ \\               | |                                | |  ");
        System.out.println(" | |  | | _____   _____| | ___  _ __  _ __ ___   ___ _ __ | |_ ");
        System.out.println(" | |  | |/ _ \\ \\ / / _ \\ |/ _ \\| '_ \\| '_ ` _ \\ / _ \\ '_ \\| __|");
        System.out.println(" | |__| |  __/\\ V /  __/ | (_) | |_) | | | | | |  __/ | | | |_ ");
        System.out.println(" |_____/ \\___| \\_/ \\___|_|\\___/| .__/|_| |_| |_|\\___|_| |_|\\__|");
        System.out.println("                               | |                             ");
        System.out.println("                               |_|                             ");
        System.out.println("Enabling plugin " + pluginname + " by @MrDarkiMC");
        System.out.println("=======================================================");
    }

    //DIAMOND_SWORD 1 name:"Super name" lore:"Text 1 \ntext 2 \ntext3" enchant:"SHARPNESS:5,SMITE:5"
//    public static ItemStack stringToStack(String item) {
//        ItemStack stack;
//        if (item.startsWith("saved:")) {
//            item = item.replace("saved:", "");
//            stack = LimitedGoods.configSaves.get().getItemStack("saves." + item);
//            if (stack == null) {
//                stack = new ItemStack(Material.STONE, 1);
//                ItemMeta meta = stack.getItemMeta();
//                meta.setDisplayName(ChatColor.RED + "ПРЕДМЕТ: " + item + " не найден!");
//                stack.setItemMeta(meta);
//            }
//        } else {
//            String[] parts = item.split(" ");
//            Material material = Material.valueOf(parts[0]);
//            int amount = Integer.parseInt(parts[1]);
//            stack = new ItemStack(material, amount);
//            if (parts.length > 2) {
//                ItemMeta meta = stack.getItemMeta();
//                List<String> list = Arrays.stream(parts).collect(Collectors.toList());
//                list.remove(0);
//                list.remove(0);
//                for (String tag : list) {
//                    String[] contents = tag.split(":");
//                    String tagExtracted = contents[0];
//                    String value = contents[1].replaceAll("\"", ""); //Text 1 \ntext 2 \ntext3
//                    switch (tagExtracted) {
//                        case "name":
//                            meta.setDisplayName(value);
//                            break;
//                        case "lore":
//                            List<String> lore = new ArrayList<>(Arrays.stream(value.split("\n")).toList());
//                            lore.replaceAll(line -> translateHex(line) + "\n");
//                            meta.setLore(lore);
//                            break;
//                        case "enchant":
//                            String[] enchants = value.split(",");
//                            for (String string : enchants) {
//                                String[] str = string.split("-");
//                                Enchantment enc = Enchantment.getByName(str[0]);
//                                int level = Integer.parseInt(str[1]);
//                                meta.addEnchant(enc, level, true);
//                            }
//                            break;
//                    }
//                }
//                stack.setItemMeta(meta);
//            }
//        }
//        return stack;
//    }

    protected static String extractQuotedValue(String input) {
        String regex = "\"([^\"]*)\"";

        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return "null";
    }
}
