package com.cursedcauldron.examplemod.core;

import com.cursedcauldron.examplemod.core.util.worldgen.BiomeLoader;

//<>

public class ExampleMod {
    public static final String MOD_ID = "examplemod";

    public static void bootstrap() {}

    public static void commonSetup() {
        BiomeLoader.bootstrap();
    }

    public static void clientSetup() {}
}