package com.cursedcauldron.examplemod.core.util.worldgen.fabric;

import com.cursedcauldron.examplemod.core.ExampleMod;
import com.cursedcauldron.examplemod.core.util.worldgen.BiomeLoader;
import com.cursedcauldron.examplemod.core.util.worldgen.BiomeWriter;
import com.google.common.base.Predicates;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.resources.ResourceLocation;

//<>

public class BiomeLoaderImpl {
    public static void bootstrap() {
        BiomeModifications.create(new ResourceLocation(ExampleMod.MOD_ID, "biome_modifications")).add(ModificationPhase.ADDITIONS, Predicates.alwaysTrue(), (selectionContext, modificationContext) -> {
            BiomeWriter writer = new BiomeWriterImpl().build(selectionContext, modificationContext);
            BiomeLoader.INSTANCE.registries(writer);
        });
    }
}