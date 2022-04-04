package com.cursedcauldron.examplemod.core.util.worldgen;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.data.worldgen.placement.NetherPlacements;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;

//<>

/**
 * @author BlackGear
 * unifies the world generation system, making it easier to add features to the world generation
 */
public class BiomeLoader {
    public static final BiomeLoader INSTANCE = new BiomeLoader();

    @ExpectPlatform
    public static void bootstrap() {}

    /**
     * @param writer adds features to specific biomes or categories
     */
    public void registries(BiomeWriter writer) {
        writer.add(BiomeLoader::test, Biomes.PLAINS);
    }

    /**
     * @param writer offers tools to add features or spawns
     * @return checks if it should generate the features or not (useful for configs)
     */
    public static boolean test(BiomeWriter writer) {
        writer.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, NetherPlacements.DELTA);
        writer.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, NetherPlacements.SMALL_BASALT_COLUMNS);
        writer.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, NetherPlacements.LARGE_BASALT_COLUMNS);
        return true;
    }
}