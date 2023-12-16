package net.ctov.neoforge;

import net.ctov.ctov;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
@Mod(ctov.MOD_ID)
public class ctovNeo {
    public ctovNeo() {
        // Submit our event bus to let architectury register our content on the right time
            // Initialize logic here
        var modBus = FMLJavaModLoadingContext.get().getModEventBus();
        ctov.init();
    }
}
