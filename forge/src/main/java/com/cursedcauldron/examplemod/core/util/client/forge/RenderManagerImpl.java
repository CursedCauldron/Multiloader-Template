package com.cursedcauldron.examplemod.core.util.client.forge;

import com.cursedcauldron.examplemod.core.ExampleMod;
import com.cursedcauldron.examplemod.core.util.forge.EventBuses;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

//<>

public class RenderManagerImpl {
    private static final Map<Supplier<EntityType<?>>, EntityRendererProvider<?>> ENTITY_RENDERERS = new ConcurrentHashMap<>();

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

    @SubscribeEvent @SuppressWarnings("all")
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        for (Map.Entry<Supplier<EntityType<?>>, EntityRendererProvider<?>> renderer : ENTITY_RENDERERS.entrySet()) {
            event.registerEntityRenderer(renderer.getKey().get(), (EntityRendererProvider<Entity>) renderer.getValue());
        }
    }

    static {
        EventBuses.onRegistered(ExampleMod.MOD_ID, bus -> bus.register(RenderManagerImpl.class));
    }
}