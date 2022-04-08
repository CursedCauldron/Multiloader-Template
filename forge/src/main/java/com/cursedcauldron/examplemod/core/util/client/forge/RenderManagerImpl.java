package com.cursedcauldron.examplemod.core.util.client.forge;

import com.cursedcauldron.examplemod.core.ExampleMod;
import com.cursedcauldron.examplemod.core.util.forge.EventBuses;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

//<>

public class RenderManagerImpl {
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
    public static <T extends Entity> void setEntityRenderer(EntityType<T> type, Function<EntityRenderDispatcher, EntityRenderer<T>> factory) {
        RenderingRegistry.registerEntityRenderingHandler(type, factory::apply);
    }

    public static <T extends ParticleOptions> void setParticle(ParticleType<T> particle, ParticleProvider<T> provider) {
        PARTICLES.put(particle, provider);
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