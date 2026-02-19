package net.choicetheorem.ctov.utils;

import dev.architectury.injectables.annotations.ExpectPlatform;

public class PlatformHelper {
	@ExpectPlatform
	public static boolean isModLoaded(String modid) {
		throw new AssertionError(); // Implemented per-platform
	}
}