package com.akari.morecurse.util;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

public class EnchantmentUtils {
    public static boolean has(ItemStack itemStack, Enchantment enchantment) {
        return itemStack.getAllEnchantments().containsKey(enchantment);
    };
}
