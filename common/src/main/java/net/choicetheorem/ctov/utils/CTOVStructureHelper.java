package net.choicetheorem.ctov.utils;

import dev.worldgen.lithostitched.worldgen.modifier.AddStructureSetEntriesModifier;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.StructureSet.StructureSelectionEntry;

import java.util.List;

public final class CTOVStructureHelper {
	
	private static final int DEFAULT_PRIORITY = 1;
	
	private CTOVStructureHelper() {
		// Utility class; no instances.
	}
	
	public static void addToStructureSet(
		HolderLookup<StructureSet> structureSetLookup,
		HolderLookup<Structure> structureLookup,
		Identifier structureSetId,
		Identifier structureId,
		int weight
	) {
		final Holder.Reference<StructureSet> structureSetHolder =
			structureSetLookup.getOrThrow(ResourceKey.create(Registries.STRUCTURE_SET, structureSetId));
		final Holder.Reference<Structure> structureHolder =
			structureLookup.getOrThrow(ResourceKey.create(Registries.STRUCTURE, structureId));
		
		final HolderSet<StructureSet> targetStructureSet = HolderSet.direct(structureSetHolder);
		final StructureSelectionEntry newEntry = new StructureSelectionEntry(structureHolder, weight);
		
		final AddStructureSetEntriesModifier modifier =
			new AddStructureSetEntriesModifier(DEFAULT_PRIORITY, targetStructureSet, List.of(newEntry));
		
		modifier.applyModifier();
	}
}
