package net.choicetheorem.ctov.platform.neoforge;

import net.choicetheorem.ctov.registry.neoforge.CTOVConfigNeoForge;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CTOVConfigHelperImpl {
	
	private CTOVConfigHelperImpl() {
		// utility class
	}
	
	public static boolean generateSmallVillage() {
		return CTOVConfigNeoForge.GENERATE_SMALL_VILLAGES.get();
	}
	
	public static boolean generateMediumVillage() {
		return CTOVConfigNeoForge.GENERATE_MEDIUM_VILLAGES.get();
	}
	
	public static boolean generateLargeVillage() {
		return CTOVConfigNeoForge.GENERATE_LARGE_VILLAGES.get();
	}
	
	public static boolean generatePillagerOutpost() {
		return CTOVConfigNeoForge.GENERATE_PILLAGER_OUTPOST.get();
	}
	
	public static int smallVillageWeight() {
		return CTOVConfigNeoForge.SMALL_VILLAGE_WEIGHT.get();
	}
	
	public static int mediumVillageWeight() {
		return CTOVConfigNeoForge.MEDIUM_VILLAGE_WEIGHT.get();
	}
	
	public static int largeVillageWeight() {
		return CTOVConfigNeoForge.LARGE_VILLAGE_WEIGHT.get();
	}
	
	public static int outpostWeight() {
		return CTOVConfigNeoForge.OUTPOST_WEIGHT.get();
	}
	
	public static @NotNull List<? extends String> enabledVillages() {
		return CTOVConfigNeoForge.ENABLED_VILLAGES.get();
	}
	
	public static @NotNull List<? extends String> enabledPillagerOutposts() {
		return CTOVConfigNeoForge.ENABLED_PILLAGER_OUTPOST.get();
	}
}
