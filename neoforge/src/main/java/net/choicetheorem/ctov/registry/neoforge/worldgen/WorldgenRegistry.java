package net.choicetheorem.ctov.registry.neoforge.worldgen;

import com.mojang.serialization.MapCodec;
import net.choicetheorem.ctov.worldgen.processor.ModularCompatProcessor;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class WorldgenRegistry {
	public static final DeferredRegister<MapCodec<? extends StructureProcessor>> PROCESSORS =
		DeferredRegister.create(Registries.STRUCTURE_PROCESSOR, "ctov");
	
	public static final DeferredHolder<MapCodec<? extends StructureProcessor>, MapCodec<ModularCompatProcessor>> MODULAR_COMPAT =
		PROCESSORS.register("modular_compat", () -> ModularCompatProcessor.CODEC);
}
