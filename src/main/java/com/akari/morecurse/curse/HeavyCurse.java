package com.akari.morecurse.curse;

import com.akari.morecurse.util.CurseUtils;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber
public class HeavyCurse extends Curse {
    public static final RegistryObject<HeavyCurse> INSTANCE = registerCurse("heavy_curse", HeavyCurse::new);

    protected HeavyCurse() {
        super(Rarity.RARE, EnchantmentCategory.WEARABLE, EquipmentSlot.values(), "heavy");
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        var player = event.player;
        var n = CurseUtils.computeCurseOfArmor(player, INSTANCE.get());
        if (n == 0) return;
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 5, (int) Math.ceil(n / 5.0), false, false, false));
    }
}
