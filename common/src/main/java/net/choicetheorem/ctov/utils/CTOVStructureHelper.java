package net.choicetheorem.ctov.utils;

import dev.worldgen.lithostitched.worldgen.modifier.AddStructureSetEntriesModifier;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.StructureSet.StructureSelectionEntry;

import java.util.List;

public class CTOVStructureHelper {
	public CTOVStructureHelper(MinecraftServer server, HolderLookup<StructureSet> StructureSetRegistry, HolderLookup<Structure> StructureRegistry, ResourceLocation setRL, ResourceLocation structureRL, int weight) {
		Holder.Reference<StructureSet> structureSetHolder = StructureSetRegistry.getOrThrow(ResourceKey.create(Registries.STRUCTURE_SET, setRL));
		Holder.Reference<Structure> structureHolder = StructureRegistry.getOrThrow(ResourceKey.create(Registries.STRUCTURE, structureRL));
		HolderSet<StructureSet> targetStructureSet = HolderSet.direct(structureSetHolder);
		StructureSelectionEntry newEntry = new StructureSelectionEntry(structureHolder, weight);
		AddStructureSetEntriesModifier modifier = new AddStructureSetEntriesModifier(1, targetStructureSet , List.of(newEntry));
		modifier.applyModifier();
	}
}
