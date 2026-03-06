package net.choicetheorem.ctov.utils;

import dev.worldgen.lithostitched.api.worldgen.modifier.WorldgenModifier;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;

import java.util.function.BiConsumer;

public class CTOVStructureHelper {
	public static void addModifier(BiConsumer<Identifier, WorldgenModifier> consumer, HolderLookup.RegistryLookup<Structure> registry, Holder.Reference<StructureSet> outpostSet, String name, int weight) {
		ResourceKey<Structure> key = ResourceKey.create(Registries.STRUCTURE, TextUtils.res(name));
		consumer.accept(
			key.identifier(),
			WorldgenModifier.builder().addStructureSetEntries(outpostSet, new StructureSet.StructureSelectionEntry(registry.getOrThrow(key), weight))
		);
	}
}