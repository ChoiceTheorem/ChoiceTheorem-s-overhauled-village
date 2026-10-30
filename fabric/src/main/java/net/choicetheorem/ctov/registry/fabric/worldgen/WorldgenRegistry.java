package net.choicetheorem.ctov.registry.fabric.worldgen;

import com.mojang.serialization.MapCodec;
import net.choicetheorem.ctov.utils.TextUtils;
import net.choicetheorem.ctov.worldgen.processor.ModularCompatProcessor;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

public class WorldgenRegistry {
	public static void register() {
	
	}
	public static final MapCodec<ModularCompatProcessor> MODULAR_COMPAT =
		Registry.register(
			BuiltInRegistries.STRUCTURE_PROCESSOR,
			TextUtils.res( "modular_compat"),
			ModularCompatProcessor.CODEC
		);
}
