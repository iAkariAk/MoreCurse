package com.akari.morecurse;

import com.akari.morecurse.curse.Curse;
import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

@Mod(MoreCurse.MOD_ID)
@Mod.EventBusSubscriber
public class MoreCurse {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "morecurse";
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);
    public static final RegistryObject<CreativeModeTab> TAB = CREATIVE_MODE_TABS.register("more_curse_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT).icon(Items.ENCHANTED_BOOK::getDefaultInstance)
            .displayItems((parameters, output) -> {
                Curse.BOOK_ITEM_FACTORIES.forEach(factory -> {
                    factory.get().forEach(output::accept);
                });
                output.accept(Curse.makeSuperCurseBook());
            })
            .title(Component.translatable("morecurse.title"))
            .build());


    @SuppressWarnings("removal")
    public MoreCurse() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        Curse.registerAll();
        ITEMS.register(modEventBus);
        ENCHANTMENTS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    @SubscribeEvent
    public void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Ciallo");
    }
}
