package net.ctov.forge;

import net.ctov.ctov;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ctov.MOD_ID)
public class ctovForge {
    public ctovForge() {
        // Submit our event bus to let architectury register our content on the right time
            // Initialize logic here
        var modBus = FMLJavaModLoadingContext.get().getModEventBus();
        ctov.init();
    }
}
