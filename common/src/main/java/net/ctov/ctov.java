package net.ctov;

import com.google.common.base.Suppliers;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrarManager;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.world.item.Item;
import dev.architectury.registry.ReloadListenerRegistry;

import java.util.function.Supplier;

import org.jetbrains.annotations.Nullable;

import net.ctov.ctovReloadListener;

public class ctov {
    public static final String MOD_ID = "ctov";

    // We can use this if we don't want to use DeferredRegister
    public static final Supplier<RegistrarManager> REGISTRIES = Suppliers.memoize(() -> RegistrarManager.get(MOD_ID));
    public static void init() {

        System.out.println(ctovExpectPlatform.getConfigDirectory().toAbsolutePath().normalize().toString());
    }
}
