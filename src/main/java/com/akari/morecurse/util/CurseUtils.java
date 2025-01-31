package com.akari.morecurse.util;

import com.akari.morecurse.curse.Curse;
import com.akari.morecurse.curse.UnluckyCurse;
import com.google.common.collect.Streams;
import net.minecraft.world.entity.player.Player;

import java.util.stream.Stream;

public class CurseUtils {
    public static int computeCurseOfArmor(Player player, Curse curse) {
        return Streams.concat(
                        Streams.stream(player.getArmorSlots()),
                        Stream.of(player.getMainHandItem(), player.getOffhandItem())
                ).mapToInt(slop -> slop.getEnchantmentLevel(curse) * UnluckyCurse.getLevelOf(slop))
                .sum();
    }
}
