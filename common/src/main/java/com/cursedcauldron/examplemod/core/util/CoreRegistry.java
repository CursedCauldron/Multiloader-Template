package com.cursedcauldron.examplemod.core.util;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

//<>

/**
 * @author Trikzon & Andante
 * @apiNote original source: Plume by DodoGang
 */
public abstract class CoreRegistry<T> {
    protected final ResourceKey<Registry<T>> key;
    protected final String modId;
    protected boolean isPresent;

    protected CoreRegistry(ResourceKey<Registry<T>> key, String modId) {
        this.key = key;
        this.modId = modId;
        this.isPresent = false;
    }

    @ExpectPlatform
    public static <T> CoreRegistry<T> create(ResourceKey<Registry<T>> key, String modId) {
        throw new AssertionError();
    }

    public abstract <E extends T> E register(String key, E entry);

    public void register() {
        if (this.isPresent) throw new IllegalStateException("Duplication of Registry: " + this.key);
        this.isPresent = true;
        this.bootstrap();
    }

    protected abstract void bootstrap();

    @SuppressWarnings("all")
    protected static <T> Registry<T> getRegistry(ResourceKey<Registry<T>> key) {
        return Registry.REGISTRY.get((ResourceKey)key);
    }
}