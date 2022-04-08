package com.cursedcauldron.examplemod.core.util.worldgen.forge;

import com.cursedcauldron.examplemod.core.util.worldgen.BiomeWriter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;

//<>

public class BiomeWriterImpl extends BiomeWriter {
    private BiomeLoadingEvent event;

    public BiomeWriter build(BiomeLoadingEvent event) {
        this.event = event;
        return this;
    }

    @Override
    public boolean isIn(Biome.BiomeCategory category) {
        return this.event.getCategory() == category;
    }

    @Override
    public ResourceLocation getName() {
        return this.event.getName();
    }

    @Override
    public void addFeature(GenerationStep.Decoration step, ConfiguredFeature<?, ?> feature) {
        this.event.getGeneration().addFeature(step, feature);
    }

    @Override
    public void addSpawn(MobCategory category, EntityType<?> entityType, int weight, int minGroupSize, int maxGroupSize) {
        this.event.getSpawns().addSpawn(category, new MobSpawnSettings.SpawnerData(entityType, weight, minGroupSize, maxGroupSize));
    }
}