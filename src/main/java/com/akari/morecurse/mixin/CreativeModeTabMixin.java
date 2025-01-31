package com.akari.morecurse.mixin;

import com.akari.morecurse.util.CurseUtils;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.akari.morecurse.MoreCurse.TAB;


public class CreativeModeTabMixin {
    @Mixin(CreativeModeTab.ItemDisplayBuilder.class)
    public static class ItemDisplayBuilder {
        @Shadow @Final public CreativeModeTab tab;

        @Inject(method = "accept(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/CreativeModeTab$TabVisibility;)V", at = @At("HEAD"), cancellable = true)
        public void accept(ItemStack itemStack, CreativeModeTab.TabVisibility visibility, CallbackInfo ci) {
            if (!CurseUtils.isMoreCurseEnchantedBook(itemStack)) return;
            if (tab == TAB.get()) return;
            ci.cancel();
        }
    }
}
