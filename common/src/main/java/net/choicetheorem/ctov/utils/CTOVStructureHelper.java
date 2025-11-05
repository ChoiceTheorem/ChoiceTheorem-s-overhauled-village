package net.choicetheorem.ctov.utils;

import dev.worldgen.lithostitched.worldgen.modifier.AddStructureSetEntriesModifier;
import net.minecraft.core.Holder;
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
	public CTOVStructureHelper(MinecraftServer server, ResourceLocation setRL, ResourceLocation structureRL, int weight) {
		Registry<StructureSet> structureSetRegistry = server.registryAccess().registry(Registries.STRUCTURE_SET).orElseThrow();
		Registry<Structure> structureRegistry = server.registryAccess().registry(Registries.STRUCTURE).orElseThrow();
		ResourceKey<StructureSet> structureSetKey = ResourceKey.create(Registries.STRUCTURE_SET, setRL);
		Holder<StructureSet> structureSetHolder = structureSetRegistry.getHolder(structureSetKey)
			.orElseThrow(() -> new IllegalArgumentException("StructureSet not found: " + structureSetKey));
		HolderSet<StructureSet> structureSets = HolderSet.direct(structureSetHolder);
		ResourceKey<Structure> structureKey = ResourceKey.create(Registries.STRUCTURE,structureRL);
		Holder<Structure> structureHolder = structureRegistry.getHolder(structureKey)
			.orElseThrow(() -> new IllegalArgumentException("Structure not found: " + structureKey));
		StructureSelectionEntry newEntry = new StructureSelectionEntry(structureHolder, weight); // 1 = weight
		AddStructureSetEntriesModifier modifier = new AddStructureSetEntriesModifier(structureSets, List.of(newEntry));
		modifier.applyModifier();
	}
}
