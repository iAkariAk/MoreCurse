package com.akari.morecurse.enchantment;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.akari.morecurse.MoreCurse.ENCHANTMENTS;

public abstract class Curse extends Enchantment {
    protected Curse(Rarity rarity, EnchantmentCategory category, EquipmentSlot[] slots) {
        super(rarity, category, slots);
    }

    @SuppressWarnings("unchecked")
    public static final Lazy<RegistryObject<? extends Curse>[]> CURSES = () -> new RegistryObject[]{
            FragilityCurse.INSTANCE,
            UnluckyCurse.INSTANCE
    };

    public static final List<Supplier<Stream<ItemStack>>> BOOK_ITEM_FACTORIES = new ArrayList<>();

    public static <C extends Curse> RegistryObject<C> registerCurse(String name, Supplier<C> factory) {
        var registryObject = ENCHANTMENTS.register(name, factory);
        Supplier<Stream<ItemStack>> bookFactory = () -> {
            var curse = registryObject.get();
            var minLevel = curse.getMinLevel();
            var maxLevel = curse.getMaxLevel();
            return IntStream.rangeClosed(minLevel, maxLevel)
                    .mapToObj(level -> EnchantedBookItem.createForEnchantment(new EnchantmentInstance(curse, level)));
        };
        BOOK_ITEM_FACTORIES.add(bookFactory);
        return registryObject;
    }

    public static void registerAll() {
        CURSES.get();
    }

    public static boolean isPresent() {
        return Arrays.stream(CURSES.get()).allMatch(net.minecraftforge.registries.RegistryObject::isPresent);
    }

    protected String getI18nKey() {
        return null;
    }


    @Override
    protected @NotNull String getOrCreateDescriptionId() {
        var key = getI18nKey();
        return key != null ? Component.translatable(key).getString() : super.getOrCreateDescriptionId();
    }

    @Override
    public boolean isCurse() {
        return true;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }

    @Override
    public boolean isTradeable() {
        return true;
    }

    @Override
    public boolean isDiscoverable() {
        return true;
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return false;
    }
}
