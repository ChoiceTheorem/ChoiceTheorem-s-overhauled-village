package net.choicetheorem.ctov.neoforge;

import net.choicetheorem.ctov.CTOV;
import net.choicetheorem.ctov.registry.neoforge.CTOVConfigNeoForge;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;

@Mod(CTOV.MOD_ID)
public class ctovNeo {
    public ctovNeo(IEventBus modEventBus, ModContainer container) {
        // Run our common setup.
        CTOV.init();
        container.registerConfig(ModConfig.Type.COMMON, CTOVConfigNeoForge.COMMON_CONFIG);
        NeoForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void onServerAboutToStartEvent(ServerAboutToStartEvent event) {
        CTOV.registerstructure(event.getServer());
    }
}
