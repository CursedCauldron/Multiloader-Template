package com.cursedcauldron.examplemod.core.util.client.fabric;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

import java.util.function.Function;
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
}