package net.choicetheorem.ctov;

import net.choicetheorem.ctov.utils.CTOVStructureHelper;
import net.choicetheorem.ctov.utils.TextUtils;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;

import java.util.List;

import static net.choicetheorem.ctov.platform.CTOVConfigHelper.*;
import static net.choicetheorem.ctov.utils.TextUtils.res;

public class CTOV {
	public static final String MOD_ID = "ctov";
	
	private static final Identifier VANILLA_VILLAGES_SET_ID = TextUtils.res("minecraft", "villages");
	private static final Identifier VANILLA_OUTPOSTS_SET_ID = TextUtils.res("minecraft", "pillager_outposts");
	
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
		HolderLookup.Provider registryProvider = server.registryAccess();
		HolderLookup<StructureSet> structureSetLookup = registryProvider.lookupOrThrow(Registries.STRUCTURE_SET);
		HolderLookup<Structure> structureLookup = registryProvider.lookupOrThrow(Registries.STRUCTURE);
		
		if (generatePillagerOutpost()) {
			registerVariants(
				enabledPillagerOutposts(),"pillager_outpost_", VANILLA_OUTPOSTS_SET_ID,
				outpostWeight(), structureSetLookup, structureLookup
			);
		}
		
		if (generateSmallVillage()) {
			registerVariants(
				enabledVillages(), "small/village_", VANILLA_VILLAGES_SET_ID,
				smallVillageWeight(), structureSetLookup, structureLookup
			);
		}
		
		if (generateMediumVillage()) {
			registerVariants(
				enabledVillages(), "medium/village_", VANILLA_VILLAGES_SET_ID,
				mediumVillageWeight(), structureSetLookup, structureLookup
			);
		}
		
		if (generateLargeVillage()) {
			registerVariants(
				enabledVillages(), "large/village_", VANILLA_VILLAGES_SET_ID,
				largeVillageWeight(), structureSetLookup, structureLookup
			);
		}
	}
	
	private static void registerVariants(
		List<? extends String> variants,
		String structurePathPrefix,
		Identifier targetStructureSetId,
		int weight,
		HolderLookup<StructureSet> structureSetLookup,
		HolderLookup<Structure> structureLookup
	) {
		for (String variant : variants) {
			Identifier structureId = res(structurePathPrefix + variant);
			CTOVStructureHelper.addToStructureSet(
				structureSetLookup,
				structureLookup,
				targetStructureSetId,
				structureId,
				weight
			);
		}
	}
}
