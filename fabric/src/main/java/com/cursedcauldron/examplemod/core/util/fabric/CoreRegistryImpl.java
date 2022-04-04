package com.cursedcauldron.examplemod.core.util.fabric;

import com.cursedcauldron.examplemod.core.util.CoreRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

//<>

public final class CoreRegistryImpl<T> extends CoreRegistry<T> {
    private final Registry<T> registry;

    public CoreRegistryImpl(ResourceKey<Registry<T>> key, String modId) {
        super(key, modId);
        this.registry = getRegistry(key);
    }

    public static <T> CoreRegistry<T> create(ResourceKey<Registry<T>> key, String modId) {
        return new CoreRegistryImpl<>(key, modId);
    }

    @Override
    public <E extends T> E register(String key, E entry) {
        Registry.register(this.registry, new ResourceLocation(this.modId, key), entry);
        return entry;
    }

    @Override
    protected void bootstrap() {}
}