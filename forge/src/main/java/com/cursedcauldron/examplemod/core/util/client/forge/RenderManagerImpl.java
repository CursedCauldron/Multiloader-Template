package com.cursedcauldron.examplemod.core.util.client.forge;

import com.cursedcauldron.examplemod.core.ExampleMod;
import com.cursedcauldron.examplemod.core.util.forge.EventBuses;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

//<>

public class RenderManagerImpl {
    private static final Map<Supplier<EntityType<?>>, EntityRendererProvider<?>> ENTITY_RENDERERS = new ConcurrentHashMap<>();
    private static final Map<ModelLayerLocation, Supplier<LayerDefinition>> MODEL_LAYERS = new ConcurrentHashMap<>();
    private static final Map<ParticleType<?>, ParticleProvider<?>> PARTICLES = new ConcurrentHashMap<>();

    public static void setBlockRenderType(RenderType type, Block... blocks) {
        for (Block block : blocks) {
            ItemBlockRenderTypes.setRenderLayer(block, type);
        }
    }

    public static void setFluidRenderType(RenderType type, Fluid... fluids) {
        for (Fluid fluid : fluids) {
            ItemBlockRenderTypes.setRenderLayer(fluid, type);
        }
    }

    @SuppressWarnings("all")
    public static <T extends Entity> void setEntityRenderer(Supplier<? extends EntityType<T>> entityType, EntityRendererProvider<T> factory) {
        ENTITY_RENDERERS.put((Supplier<EntityType<?>>)entityType, factory);
    }

    public static void setEntityModelLayer(ModelLayerLocation location, Supplier<LayerDefinition> definition) {
        MODEL_LAYERS.put(location, definition);
    }

    public static <T extends ParticleOptions> void setParticle(ParticleType<T> particle, ParticleProvider<T> provider) {
        PARTICLES.put(particle, provider);
    }

    @SubscribeEvent @SuppressWarnings("all")
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        for (Map.Entry<Supplier<EntityType<?>>, EntityRendererProvider<?>> renderer : ENTITY_RENDERERS.entrySet()) {
            event.registerEntityRenderer(renderer.getKey().get(), (EntityRendererProvider<Entity>) renderer.getValue());
        }
    }

    @SubscribeEvent
    public static void registerModelLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        for (Map.Entry<ModelLayerLocation, Supplier<LayerDefinition>> definition : MODEL_LAYERS.entrySet()) {
            event.registerLayerDefinition(definition.getKey(), definition.getValue());
        }
    }

    @SubscribeEvent @SuppressWarnings("all")
    public static <T extends ParticleOptions> void registerParticleFactories(ParticleFactoryRegisterEvent event) {
        for (Map.Entry<ParticleType<?>, ParticleProvider<?>> particle : PARTICLES.entrySet()) {
            Minecraft.getInstance().particleEngine.register((ParticleType<T>)particle.getKey(), (ParticleProvider<T>)particle.getValue());
        }
    }

    static {
        EventBuses.onRegistered(ExampleMod.MOD_ID, bus -> bus.register(RenderManagerImpl.class));
    }
}