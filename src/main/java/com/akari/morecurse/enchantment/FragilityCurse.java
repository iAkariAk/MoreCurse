package com.akari.morecurse.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import static com.akari.morecurse.MoreCurse.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class FragilityCurse extends Curse {
    public static final RegistryObject<FragilityCurse> INSTANCE = registerCurse("fragility_curse", FragilityCurse::new);

    private FragilityCurse() {
        super(Rarity.VERY_RARE, EnchantmentCategory.BREAKABLE, EquipmentSlot.values());
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    protected String getI18nKey() {
        return "morecurse.curse.fragility";
    }
}
