package net.choicetheorem.ctov.registry.fabric.worldgen;

import net.choicetheorem.ctov.utils.TextUtils;
import net.choicetheorem.ctov.worldgen.processor.ModularCompatProcessor;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;

public class WorldgenRegistry {
	public static void register() {
	
	}
	public static final StructureProcessorType<ModularCompatProcessor> MODULAR_COMPAT =
		Registry.register(
			BuiltInRegistries.STRUCTURE_PROCESSOR,
			TextUtils.res( "modular_compat"),
			() -> ModularCompatProcessor.CODEC
		);
}
