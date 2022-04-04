package com.cursedcauldron.examplemod.forge;

import com.cursedcauldron.examplemod.core.ExampleMod;
import com.cursedcauldron.examplemod.core.util.forge.EventBuses;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

//<>

@Mod(ExampleMod.MOD_ID)
public class ExampleModForge {
    public ExampleModForge() {
        EventBuses.registerModEventBus(ExampleMod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        ExampleMod.bootstrap();

        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ExampleModForgeClient::new);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(FMLCommonSetupEvent event) {
        ExampleMod.commonSetup();
    }
}
