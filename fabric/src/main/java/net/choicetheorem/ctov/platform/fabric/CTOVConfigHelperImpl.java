package net.choicetheorem.ctov.platform.fabric;

import me.shedaniel.autoconfig.AutoConfig;
import net.choicetheorem.ctov.registry.fabric.CTOVConfigFabric;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CTOVConfigHelperImpl {
	static CTOVConfigFabric config = AutoConfig.getConfigHolder(CTOVConfigFabric.class).getConfig();
	 
	public static boolean generatesmallVillage() {
		return config.structures.generatesmallVillage;
	}
	 
	public static boolean generatemediumVillage() {
		return config.structures.generatemediumVillage;
	}
	 
	public static boolean generatelargeVillage() {
		return config.structures.generatelargeVillage;
	}
	 
	public static boolean generatePillagerOutpost() {
		return config.structures.generatePillagerOutpost;
	}
	 
	public static int smallVillageWeight() {
		return config.weights.smallVillageWeight;
	}
	 
	public static int mediumVillageWeight(){
		return config.weights.mediumVillageWeight;
	}
	 
	public static int largeVillageWeight(){
		return config.weights.largeVillageWeight;
	}
	 
	public static int OutpostWeight() {
		return config.weights.OutpostWeight;
	}
	public static @NotNull List<String> enabledvillage(){
		return config.structures.enabledvillage;
	}
	
	public static @NotNull List<String> enabledpillageroutpost(){
		return config.structures.enabledpillageroutpost;
	}
}
