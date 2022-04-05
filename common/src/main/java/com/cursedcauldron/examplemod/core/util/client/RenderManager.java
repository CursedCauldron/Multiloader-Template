package com.cursedcauldron.examplemod.core.util.client;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.particle.ParticleEngine;
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

public class RenderManager {
    @ExpectPlatform
    public static void setBlockRenderType(RenderType type, Block... blocks) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void setFluidRenderType(RenderType type, Fluid... fluids) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends Entity> void setEntityRenderer(Supplier<? extends EntityType<T>> entityType, EntityRendererProvider<T> factory) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void setEntityModelLayer(ModelLayerLocation location, Supplier<LayerDefinition> definition) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends ParticleOptions> void setParticle(ParticleType<T> particle, ParticleProvider<T> provider) {
        throw new AssertionError();
    }
}