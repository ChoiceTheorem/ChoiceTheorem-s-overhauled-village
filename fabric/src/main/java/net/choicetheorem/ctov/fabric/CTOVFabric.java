package net.choicetheorem.ctov.fabric;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.choicetheorem.ctov.CTOV;
import net.choicetheorem.ctov.registry.fabric.CTOVConfigFabric;
import net.choicetheorem.ctov.registry.fabric.worldgen.WorldgenRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;


public class CTOVFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        AutoConfig.register(CTOVConfigFabric.class, GsonConfigSerializer::new);
        // Run our common setup.
        CTOV.init();
        ServerLifecycleEvents.SERVER_STARTING.register(CTOV::registerstructure);
        WorldgenRegistry.register();
    }
}
