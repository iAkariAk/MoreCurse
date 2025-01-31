package com.akari.morecurse.mixin;

import com.akari.morecurse.curse.Curse;
import com.akari.morecurse.curse.FragilityCurse;
import com.akari.morecurse.curse.UnluckyCurse;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow
    public abstract int getDamageValue();

    @Shadow
    public abstract Item getItem();

    @Shadow
    public abstract int getMaxDamage();

    @Inject(method = "setDamageValue(I)V", at = @At("HEAD"), cancellable = true)
    public void setDamageValue(int target, CallbackInfo ci) {
        var itemStack = (ItemStack) (Object) this;
        if (!Curse.isPresent()) return;
        var curse = FragilityCurse.INSTANCE.get();
        var finalDamage = target;
        var level = itemStack.getEnchantmentLevel(curse);
        var unluckyLevel = UnluckyCurse.getLevel(itemStack);
        if (level > 0) {
            var current = getDamageValue();
            if (current < target) {
                finalDamage = Math.min(getMaxDamage(), current + (target - current) * (level + 1) * unluckyLevel);
            }
        }
        getItem().setDamage(itemStack, finalDamage);
        ci.cancel();
    }
}
