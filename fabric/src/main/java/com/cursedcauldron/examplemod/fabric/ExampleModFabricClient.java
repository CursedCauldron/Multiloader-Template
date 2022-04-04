package com.cursedcauldron.examplemod.fabric;

import com.cursedcauldron.examplemod.core.ExampleMod;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ExampleModFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ExampleMod.clientSetup();
    }
}