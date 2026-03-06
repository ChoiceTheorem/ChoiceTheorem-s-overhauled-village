package net.choicetheorem.ctov.utils;

import dev.worldgen.lithostitched.api.worldgen.modifier.WorldgenModifier;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;

import java.util.function.BiConsumer;

import static net.choicetheorem.ctov.utils.TextUtils.res;

public class CTOVStructureHelper {
	public static void addModifier(BiConsumer<ResourceLocation, WorldgenModifier> consumer, HolderLookup.RegistryLookup<Structure> registry, Holder.Reference<StructureSet> outpostSet, String name, int weight) {
		ResourceKey<Structure> key = ResourceKey.create(Registries.STRUCTURE, res(name));
		consumer.accept(
			key.location(),
			WorldgenModifier.builder().addStructureSetEntries(outpostSet, new StructureSet.StructureSelectionEntry(registry.getOrThrow(key), weight))
		);
	}
}
