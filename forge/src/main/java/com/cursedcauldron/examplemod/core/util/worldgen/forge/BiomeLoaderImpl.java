package com.cursedcauldron.examplemod.core.util.worldgen.forge;

import com.cursedcauldron.examplemod.core.ExampleMod;
import com.cursedcauldron.examplemod.core.util.worldgen.BiomeLoader;
import com.cursedcauldron.examplemod.core.util.worldgen.BiomeWriter;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

//<>

@Mod.EventBusSubscriber(modid = ExampleMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BiomeLoaderImpl {
    public static void bootstrap() {}

    @SubscribeEvent
    public static void biomeLoading(BiomeLoadingEvent event) {
        BiomeWriter writer = new BiomeWriterImpl().build(event);
        BiomeLoader.INSTANCE.registries(writer);
    }
}