package com.cursedcauldron.examplemod.fabric;

import com.cursedcauldron.examplemod.core.ExampleMod;
import net.fabricmc.api.ModInitializer;

public class ExampleModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ExampleMod.bootstrap();
        ExampleMod.commonSetup();
    }
}
