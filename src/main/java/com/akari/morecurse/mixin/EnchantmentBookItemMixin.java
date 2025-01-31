package com.akari.morecurse.mixin;

import com.akari.morecurse.curse.Curse;
import com.akari.morecurse.util.CurseUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Optional;

@Mixin(EnchantedBookItem.class)
public class EnchantmentBookItemMixin {

    @Inject(method = "appendHoverText", at = @At("RETURN"))
    public void appendHoverText(ItemStack itemStack, Level level, List<Component> components, TooltipFlag flag, CallbackInfo ci) {
        if (!Curse.isPresent() || !CurseUtils.isMoreCurseEnchantedBook(itemStack)) return;
        var enchantments = EnchantmentHelper.getEnchantments(itemStack);
        enchantments.keySet().stream()
                .map(enchantment -> (Curse) enchantment)
                .map(Curse::getDescription)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(components::add);
    }
}

