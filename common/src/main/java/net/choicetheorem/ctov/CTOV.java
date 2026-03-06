package net.choicetheorem.ctov;

import dev.worldgen.lithostitched.api.event.AddWorldgenModifiersEvent;
import net.choicetheorem.ctov.utils.CTOVStructureHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.levelgen.structure.BuiltinStructureSets;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;

import java.util.List;

import static net.choicetheorem.ctov.platform.CTOVConfigHelper.*;

public class CTOV {
	public static final String MOD_ID = "ctov";
	
	public static void init() {
		//System.out.println(ctovExpectPlatform.getConfigDirectory().toAbsolutePath().normalize().toString());
	}
	
	/**
	 * Backwards-compatible entrypoint (kept for existing callers).
	 * Prefer {@link #registerStructures(MinecraftServer)}.
	 */
	public static void registerstructure(MinecraftServer server) {
		registerStructures(server);
	}
	
	public static void registerStructures(MinecraftServer server) {
		AddWorldgenModifiersEvent.EVENT.register((registries, consumer) -> {
			Holder.Reference<StructureSet> outpostSet = registries.lookupOrThrow(Registries.STRUCTURE_SET).getOrThrow(BuiltinStructureSets.PILLAGER_OUTPOSTS);
			Holder.Reference<StructureSet> villageSet = registries.lookupOrThrow(Registries.STRUCTURE_SET).getOrThrow(BuiltinStructureSets.VILLAGES);
			HolderLookup.RegistryLookup<Structure> registry = registries.lookupOrThrow(Registries.STRUCTURE);
			
			if (generatePillagerOutpost()) {
				for (String variant : enabledPillagerOutposts()) {
					CTOVStructureHelper.addModifier(consumer, registry, outpostSet, "pillager_outpost_" + variant, outpostWeight());
				}
			}
			
			if (generateSmallVillage()) {
				for (String variant : enabledVillages()) {
					CTOVStructureHelper.addModifier(consumer, registry, villageSet, "small/village_" + variant, smallVillageWeight());
				}
			}
			
			if (generateMediumVillage()) {
				for (String variant : enabledVillages()) {
					CTOVStructureHelper.addModifier(consumer, registry, villageSet, "medium/village_" + variant, mediumVillageWeight());
				}
			}
			
			if (generateLargeVillage()) {
				for (String variant : enabledVillages()) {
					CTOVStructureHelper.addModifier(consumer, registry, villageSet, "large/village_" + variant, largeVillageWeight());
				}
			}
		});
	}
}
