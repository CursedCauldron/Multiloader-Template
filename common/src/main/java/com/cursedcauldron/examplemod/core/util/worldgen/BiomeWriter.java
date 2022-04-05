package com.cursedcauldron.examplemod.core.util.worldgen;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.function.Predicate;

//<>

public abstract class BiomeWriter {
    @SafeVarargs
    public final void add(Predicate<BiomeWriter> factory, ResourceKey<Biome>... biomes) {
        for (ResourceKey<Biome> biome : biomes) {
            if (this.isIn(biome)) {
                factory.test(this);
            }
        }
    }

    public void add(Predicate<BiomeWriter> factory, Biome.BiomeCategory... categories) {
        for (Biome.BiomeCategory category : categories) {
            if (this.isIn(category)) {
                factory.test(this);
            }
        }
    }

    public void exclude(Predicate<BiomeWriter> factory, Biome.BiomeCategory... categories) {
        for (Biome.BiomeCategory category : categories) {
            if (!this.isIn(category)) {
                factory.test(this);
            }
        }
    }

    public boolean isIn(ResourceKey<Biome> key) {
        return key == ResourceKey.create(Registry.BIOME_REGISTRY, this.getName());
    }

    public abstract boolean isIn(Biome.BiomeCategory category);

    public abstract ResourceLocation getName();

    public abstract void addFeature(GenerationStep.Decoration step, PlacedFeature feature);

    public abstract void addSpawn(MobCategory category, EntityType<?> entityType, int weight, int minGroupSize, int maxGroupSize);
}