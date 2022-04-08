package com.cursedcauldron.examplemod.core.util.client.fabric;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

import java.util.function.Function;

//<>

public class RenderManagerImpl {
    public static void setBlockRenderType(RenderType type, Block... blocks) {
        BlockRenderLayerMap.INSTANCE.putBlocks(type, blocks);
    }

    public static void setFluidRenderType(RenderType type, Fluid... fluids) {
        BlockRenderLayerMap.INSTANCE.putFluids(type, fluids);
    }

    public static <T extends Entity> void setEntityRenderer(EntityType<T> type, Function<EntityRenderDispatcher, EntityRenderer<T>> factory) {
        EntityRendererRegistry.INSTANCE.register(type, (manager, context) -> factory.apply(manager));
    }

    public static <T extends ParticleOptions> void setParticle(ParticleType<T> particle, ParticleProvider<T> provider) {
        ParticleFactoryRegistry.getInstance().register(particle, provider);
    }
}