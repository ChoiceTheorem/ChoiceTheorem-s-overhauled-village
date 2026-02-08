package net.choicetheorem.ctov.platform.fabric;

import me.shedaniel.autoconfig.AutoConfig;
import net.choicetheorem.ctov.registry.fabric.CTOVConfigFabric;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CTOVConfigHelperImpl {
	private static final CTOVConfigFabric CONFIG =
		AutoConfig.getConfigHolder(CTOVConfigFabric.class).getConfig();
	
	private CTOVConfigHelperImpl() {
		// utility class
	}
	
	public static boolean generateSmallVillage() {
		return CONFIG.structures.generateSmallVillage;
	}
	
	public static boolean generateMediumVillage() {
		return CONFIG.structures.generateMediumVillage;
	}
	
	public static boolean generateLargeVillage() {
		return CONFIG.structures.generateLargeVillage;
	}
	
	public static boolean generatePillagerOutpost() {
		return CONFIG.structures.generatePillagerOutpost;
	}
	
	public static int smallVillageWeight() {
		return CONFIG.weights.smallVillageWeight;
	}
	
	public static int mediumVillageWeight() {
		return CONFIG.weights.mediumVillageWeight;
	}
	
	public static int largeVillageWeight() {
		return CONFIG.weights.largeVillageWeight;
	}
	
	public static int outpostWeight() {
		return CONFIG.weights.OutpostWeight;
	}
	
	public static @NotNull List<? extends String> enabledVillages() {
		return CONFIG.structures.enabledVillage;
	}
	
	public static @NotNull List<? extends String> enabledPillagerOutposts() {
		return CONFIG.structures.enabledPillageroutpost;
	}
}
