package net.choicetheorem.ctov.utils.neoforge;

import net.neoforged.fml.ModList;

public class PlatformHelperImpl {
	// Implementation
	public static boolean isModLoaded(String modid) {
		return ModList.get().isLoaded(modid);
	}
}
