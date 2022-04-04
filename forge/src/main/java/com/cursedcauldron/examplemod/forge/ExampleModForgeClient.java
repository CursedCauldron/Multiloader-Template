package com.cursedcauldron.examplemod.forge;

import com.cursedcauldron.examplemod.core.ExampleMod;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

//<>

@OnlyIn(Dist.CLIENT)
public class ExampleModForgeClient {
    public ExampleModForgeClient() {
        ExampleMod.clientSetup();
    }
}