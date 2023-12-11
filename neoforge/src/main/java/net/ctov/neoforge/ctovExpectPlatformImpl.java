package net.ctov.neoforge;

import net.ctov.ctovExpectPlatform;
import net.neoforged.fml.loading.FMLPaths;

import java.nio.file.Path;

public class ctovExpectPlatformImpl {
    /**
     * This is our actual method to {@link ctovExpectPlatform#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }
}
