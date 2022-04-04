package com.cursedcauldron.examplemod.core.util.forge;

import com.cursedcauldron.examplemod.core.util.CoreRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.RegistryManager;

//<>

public final class CoreRegistryImpl<T extends IForgeRegistryEntry<T>> extends CoreRegistry<T> {
    private final DeferredRegister<T> registry;

    public CoreRegistryImpl(ResourceKey<Registry<T>> key, String modId) {
        super(key, modId);
        this.registry = DeferredRegister.create(RegistryManager.ACTIVE.getRegistry(key), modId);
    }

    @SuppressWarnings("all")
    public static <T> CoreRegistry<T> create(ResourceKey<Registry<T>> key, String modId) {
        return new CoreRegistryImpl(key, modId);
    }

    @Override
    public <E extends T> E register(String key, E entry) {
        this.registry.register(key, () -> entry);
        return entry;
    }

    @Override
    protected void bootstrap() {
        IEventBus bus = EventBuses.getModEventBus(this.modId).orElseThrow(() -> new IllegalStateException("Attempted to register stuff before registering a Mod Event Bus for: " + this.modId));
        this.registry.register(bus);
    }
}