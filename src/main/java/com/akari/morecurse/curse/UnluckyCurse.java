package com.akari.morecurse.curse;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.RegistryObject;

public class UnluckyCurse extends Curse {
    public static final RegistryObject<UnluckyCurse> INSTANCE = registerCurse("unlucky_curse", UnluckyCurse::new);

    private UnluckyCurse() {
        super(Rarity.COMMON, EnchantmentCategory.VANISHABLE, EquipmentSlot.values(), "unlucky");
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

    public static int getLevel(ItemStack itemStack) {
        return itemStack.getEnchantmentLevel(INSTANCE.get());
    }
}
