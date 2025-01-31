package com.akari.morecurse.curse;

import com.akari.morecurse.util.CurseUtils;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.Random;

@Mod.EventBusSubscriber
public class SilverfishCurse extends Curse {
    public static final RegistryObject<SilverfishCurse> INSTANCE = registerCurse("silverfish_curse", SilverfishCurse::new);

    protected SilverfishCurse() {
        super(Rarity.UNCOMMON, EnchantmentCategory.WEARABLE, EquipmentSlot.values(), "silverfish");
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    private static final Random random = new Random();

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        var player = event.player;
        var n = CurseUtils.computeCurseOfArmor(player, INSTANCE.get());
        if (random.nextInt(10000) < n) {
            var level = player.level();
            var silverfish = new Silverfish(EntityType.SILVERFISH, level);
            silverfish.setPos(player.position());
            level.addFreshEntity(silverfish);
        }
        player.playSound(SoundEvents.SILVERFISH_DEATH);
    }
}
