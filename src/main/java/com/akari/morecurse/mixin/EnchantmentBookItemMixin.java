package com.akari.morecurse.mixin;

import com.akari.morecurse.curse.Curse;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.RegistryObject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Mixin(EnchantedBookItem.class)
public class EnchantmentBookItemMixin {

    @Inject(method = "appendHoverText", at = @At("RETURN"))
    public void appendHoverText(ItemStack itemStack, Level level, List<Component> components, TooltipFlag flag, CallbackInfo ci) {
        if (!(itemStack.getItem() instanceof EnchantedBookItem item)) return;
        var enchantments = EnchantmentHelper.getEnchantments(itemStack);
        var isSingle = enchantments.size() == 1;
        if (!Curse.isPresent() || !isSingle) return;
        var single = enchantments.keySet().iterator().next();
        var hasDescription = Arrays.stream(Curse.CURSES.get())
                .map(RegistryObject::get)
                .anyMatch(it -> Objects.equals(single, it));
        if (!(single instanceof Curse curse) || !hasDescription) return;
        curse.getDescription().ifPresent(components::add);
    }
}

