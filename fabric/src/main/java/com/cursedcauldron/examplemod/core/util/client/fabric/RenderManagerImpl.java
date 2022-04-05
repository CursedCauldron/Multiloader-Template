package com.cursedcauldron.examplemod.core.util.client.fabric;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

import java.util.function.Supplier;

//<>

public class RenderManagerImpl {
    public static void setBlockRenderType(RenderType type, Block... blocks) {
        BlockRenderLayerMap.INSTANCE.putBlocks(type, blocks);
    }

    public static void setFluidRenderType(RenderType type, Fluid... fluids) {
        BlockRenderLayerMap.INSTANCE.putFluids(type, fluids);
    }

    public static <T extends Entity> void setEntityRenderer(Supplier<? extends EntityType<T>> entityType, EntityRendererProvider<T> factory) {
        EntityRendererRegistry.register(entityType.get(), factory);
    }

    public static void setEntityModelLayer(ModelLayerLocation location, Supplier<LayerDefinition> definition) {
        EntityModelLayerRegistry.registerModelLayer(location, definition::get);
    }

    public static <T extends ParticleOptions> void setParticle(ParticleType<T> particle, ParticleProvider<T> provider) {
        ParticleFactoryRegistry.getInstance().register(particle, provider);
    }
}