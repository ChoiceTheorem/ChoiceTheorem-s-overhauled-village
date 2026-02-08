package net.choicetheorem.ctov.registry.fabric;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import net.choicetheorem.ctov.CTOV;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Config(name = CTOV.MOD_ID)
public class CTOVConfigFabric implements ConfigData {
	// Settings
	@ConfigEntry.Gui.CollapsibleObject
	public Structures structures = new Structures();
	@ConfigEntry.Gui.CollapsibleObject
	public Weights weights = new Weights();
	
	@ConfigEntry.Gui.Excluded
	public static final List<String> VILLAGE_POOL = List.of(
		"beach", "christmas", "desert", "desert_oasis", "dark_forest",
		"jungle", "jungle_tree", "mesa", "mesa_fortified", "mountain",
		"mountain_alpine", "mushroom", "plains", "plains_fortified",
		"savanna", "savanna_na", "snowy_igloo", "swamp",
		"swamp_fortified", "taiga", "taiga_fortified"
	);
	
	@ConfigEntry.Gui.Excluded
	public static final List<String> PILLAGER_OUTPOST_POOL = List.of(
		"beach", "dark_forest", "desert", "jungle", "badlands", "mountain",
		"plains", "savanna", "snowy", "swamp", "taiga"
	);
	
	private static final Set<String> VILLAGE_POOL_SET = new HashSet<>(VILLAGE_POOL);
	private static final Set<String> PILLAGER_OUTPOST_POOL_SET = new HashSet<>(PILLAGER_OUTPOST_POOL);
	
	private static final int MIN_WEIGHT = 0;
	
	@Override
	public void validatePostLoad() throws ValidationException {
		structures.enabledVillage = sanitizeIds(structures.enabledVillage, VILLAGE_POOL_SET);
		structures.enabledPillageroutpost = sanitizeIds(structures.enabledPillageroutpost, PILLAGER_OUTPOST_POOL_SET);
		
		weights.smallVillageWeight = clampNonNegative(weights.smallVillageWeight);
		weights.mediumVillageWeight = clampNonNegative(weights.mediumVillageWeight);
		weights.largeVillageWeight = clampNonNegative(weights.largeVillageWeight);
		weights.OutpostWeight = clampNonNegative(weights.OutpostWeight);
	}
	
	private static List<String> sanitizeIds(List<String> configured, Set<String> allowed) {
		if (configured == null) {
			return null;
		}
		List<String> copy = new ArrayList<>(configured);
		copy.removeIf(entry -> !allowed.contains(entry));
		return copy;
	}
	
	private static int clampNonNegative(int value) {
		return Math.max(MIN_WEIGHT, value);
	}
	
	public static class Structures {
		@Comment("Should CTOV small village generates?")
		public boolean generateSmallVillage = true;
		@Comment("Should CTOV medium village generates?")
		public boolean generateMediumVillage = true;
		@Comment("Should CTOV large village generates?")
		public boolean generateLargeVillage = true;
		@Comment("Should CTOV Pillager Outpost generates?")
		public boolean generatePillagerOutpost = true;
		
		@Comment("Which village should generates")
		public List<String> enabledVillage = new ArrayList<>(VILLAGE_POOL);
		@Comment("Which pillager outpost should generates")
		public List<String> enabledPillageroutpost = new ArrayList<>(PILLAGER_OUTPOST_POOL);
		
		public void validate() throws ValidationException {
			enabledVillage = sanitizeIds(enabledVillage, VILLAGE_POOL_SET);
			enabledPillageroutpost = sanitizeIds(enabledPillageroutpost, PILLAGER_OUTPOST_POOL_SET);
		}
	}
	
	public static class Weights {
		@Comment("CTOV small village spawn chance")
		public int smallVillageWeight = 10;
		@Comment("CTOV medium village spawn chance")
		public int mediumVillageWeight = 4;
		@Comment("CTOV large village spawn chance")
		public int largeVillageWeight = 1;
		@Comment("CTOV Pillager Outpost spawn chance")
		public int OutpostWeight = 1;
	}
}
