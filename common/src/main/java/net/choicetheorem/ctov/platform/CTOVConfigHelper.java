package net.choicetheorem.ctov.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;

import java.util.List;

public class CTOVConfigHelper {
	
	@ExpectPlatform
	public static boolean generateSmallVillage() {
		throw new AssertionError();
	}
	
	@ExpectPlatform
	public static boolean generateMediumVillage() {
		throw new AssertionError();
	}
	
	@ExpectPlatform
	public static boolean generateLargeVillage() {
		throw new AssertionError();
	}
	
	@ExpectPlatform
	public static boolean generatePillagerOutpost() {
		throw new AssertionError();
	}
	
	@ExpectPlatform
	public static int smallVillageWeight() {
		throw new AssertionError();
	}
	
	@ExpectPlatform
	public static int mediumVillageWeight() {
		throw new AssertionError();
	}
	
	@ExpectPlatform
	public static int largeVillageWeight() {
		throw new AssertionError();
	}
	
	@ExpectPlatform
	public static int outpostWeight() {
		throw new AssertionError();
	}
	
	@ExpectPlatform
	public static List<? extends String> enabledVillages() {
		throw new AssertionError();
	}
	
	@ExpectPlatform
	public static List<? extends String> enabledPillagerOutposts() {
		throw new AssertionError();
	}
}
