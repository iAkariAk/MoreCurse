package com.akari.morecurse.enchantment;

import com.akari.morecurse.util.EnchantmentUtils;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.RegistryObject;

import static com.akari.morecurse.MoreCurse.ENCHANTMENTS;

public class UnluckyCurse extends Curse {
    public static final RegistryObject<UnluckyCurse> INSTANCE = registerCurse("unlucky_curse", UnluckyCurse::new);

    protected UnluckyCurse() {
        super(Rarity.COMMON, EnchantmentCategory.VANISHABLE, EquipmentSlot.values());
    }

    @Override
    protected String getI18nKey() {
        return "morecurse.curse.unlucky";
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getMinCost(int level) {
        return 1;
    }

    @Override
    public int getMaxCost(int level) {
        return level; // very low cost
    }

    public static int getLevelOf(ItemStack itemStack) {
        return itemStack.getEnchantmentLevel(INSTANCE.get());
    }
}
