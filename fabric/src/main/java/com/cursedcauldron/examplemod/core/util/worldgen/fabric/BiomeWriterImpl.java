package com.cursedcauldron.examplemod.core.util.worldgen.fabric;

import com.cursedcauldron.examplemod.core.util.worldgen.BiomeWriter;
import net.fabricmc.fabric.api.biome.v1.BiomeModificationContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

//<>

public class BiomeWriterImpl extends BiomeWriter {
    private BiomeSelectionContext selectionContext;
    private BiomeModificationContext modificationContext;

    public static BiomeWriter create() {
        return new BiomeWriterImpl();
    }

    public BiomeWriter build(BiomeSelectionContext selectionContext, BiomeModificationContext modificationContext) {
        this.selectionContext = selectionContext;
        this.modificationContext = modificationContext;
        return this;
    }

    @Override
    public boolean isIn(Biome.BiomeCategory category) {
        return this.selectionContext.getBiome().getBiomeCategory() == category;
    }

    @Override
    public ResourceLocation getName() {
        return this.selectionContext.getBiomeKey().location();
    }

    @Override
    public void addFeature(GenerationStep.Decoration step, PlacedFeature feature) {
        this.modificationContext.getGenerationSettings().addBuiltInFeature(step, feature);
    }

    @Override
    public void addSpawn(MobCategory category, EntityType<?> entityType, int weight, int minGroupSize, int maxGroupSize) {
        this.modificationContext.getSpawnSettings().addSpawn(category, new MobSpawnSettings.SpawnerData(entityType, weight, minGroupSize, maxGroupSize));
    }
}
