package com.akari.morecurse.curse;

import com.akari.morecurse.util.CurseUtils;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber
public class ReverentCurse extends Curse {
    public static final RegistryObject<ReverentCurse> INSTANCE = registerCurse("reverse_curse", ReverentCurse::new);

    private ReverentCurse() {
        super(Rarity.VERY_RARE, EnchantmentCategory.VANISHABLE, EquipmentSlot.values(), "reverse");
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getMinCost(int p_44679_) {
        return 30;
    }

    @Override
    public int getMaxCost(int p_44691_) {
        return 30;
    }

    @SubscribeEvent
    public static void onAnvilUpdate(AnvilUpdateEvent event) {
        ItemStack output = event.getOutput();
        output.enchant(Enchantments.BINDING_CURSE, 1);
        output.setDamageValue(output.getMaxDamage() - 1);
    }

    public static boolean isIn(ItemStack itemStack) {
        return CurseUtils.hasCurse(INSTANCE.get(), itemStack);
    }
}
