package com.akari.morecurse.util;

import com.akari.morecurse.curse.Curse;
import com.akari.morecurse.curse.UnluckyCurse;
import com.google.common.collect.Streams;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import java.util.stream.Stream;

public class CurseUtils {
    public static boolean hasCurse(Curse curse, LivingEntity entity) {
        return EnchantmentHelper.getEnchantmentLevel(curse, entity) > 0;
    }

    public static boolean hasCurse(Curse curse, ItemStack itemStack) {
        return itemStack.getEnchantmentLevel(curse) > 0;
    }

    public static int computeCurseOfArmor(Player player, Curse curse) {
        return Streams.concat(
                        Streams.stream(player.getArmorSlots()),
                        Stream.of(player.getMainHandItem(), player.getOffhandItem())
                ).mapToInt(slop -> slop.getEnchantmentLevel(curse) * (UnluckyCurse.getLevel(slop) + 1))
                .sum();
    }

    public static boolean isMoreCurseEnchantedBook(ItemStack itemStack) {
        return (itemStack.getItem() instanceof EnchantedBookItem)
                && EnchantmentHelper.getEnchantments(itemStack).keySet().stream()
                .allMatch(enchantment -> enchantment instanceof Curse);
    }
}
