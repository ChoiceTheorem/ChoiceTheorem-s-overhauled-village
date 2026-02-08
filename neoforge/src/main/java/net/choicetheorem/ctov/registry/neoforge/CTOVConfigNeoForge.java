package net.choicetheorem.ctov.registry.neoforge;

import net.choicetheorem.ctov.CTOV;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

@EventBusSubscriber(modid = CTOV.MOD_ID)
public class CTOVConfigNeoForge {
	
	// --- Categories ---
	private static final String CATEGORY_STRUCTURES = "structures";
	private static final String CATEGORY_WEIGHTS = "weights";
	
	// --- Keys (avoid string duplication / typos) ---
	private static final String KEY_GENERATE_SMALL_VILLAGES = "generateSmallVillage";
	private static final String KEY_GENERATE_MEDIUM_VILLAGES = "generateMediumVillage";
	private static final String KEY_GENERATE_LARGE_VILLAGES = "generateLargeVillage";
	private static final String KEY_GENERATE_PILLAGER_OUTPOST = "generatePillagerOutpost";
	
	private static final String KEY_ENABLED_VILLAGES = "enabledVillages";
	private static final String KEY_ENABLED_PILLAGER_OUTPOSTS = "enabledPillagerOutposts";
	
	private static final String KEY_SMALL_VILLAGE_WEIGHT = "smallVillageWeight";
	private static final String KEY_MEDIUM_VILLAGE_WEIGHT = "mediumVillageWeight";
	private static final String KEY_LARGE_VILLAGE_WEIGHT = "largeVillageWeight";
	private static final String KEY_OUTPOST_WEIGHT = "outpostWeight";
	
	// --- Defaults ---
	private static final int DEFAULT_SMALL_VILLAGE_WEIGHT = 10;
	private static final int DEFAULT_MEDIUM_VILLAGE_WEIGHT = 4;
	private static final int DEFAULT_LARGE_VILLAGE_WEIGHT = 1;
	private static final int DEFAULT_OUTPOST_WEIGHT = 1;
	
	public static final List<String> VILLAGE_POOL = List.of(
		"beach", "christmas", "desert", "desert_oasis", "dark_forest",
		"jungle", "jungle_tree", "mesa", "mesa_fortified", "mountain",
		"mountain_alpine", "mushroom", "plains", "plains_fortified",
		"savanna", "savanna_na", "snowy_igloo", "swamp",
		"swamp_fortified", "taiga", "taiga_fortified"
	);
	
	public static final List<String> PILLAGER_OUTPOST_POOL = List.of(
		"beach", "dark_forest", "desert", "jungle", "badlands", "mountain",
		"plains", "savanna", "snowy", "swamp", "taiga"
	);
	
	public static final ModConfigSpec COMMON_CONFIG;
	
	public static final ModConfigSpec.BooleanValue GENERATE_SMALL_VILLAGES;
	public static final ModConfigSpec.BooleanValue GENERATE_MEDIUM_VILLAGES;
	public static final ModConfigSpec.BooleanValue GENERATE_LARGE_VILLAGES;
	public static final ModConfigSpec.BooleanValue GENERATE_PILLAGER_OUTPOST;
	
	public static final ModConfigSpec.ConfigValue<List<? extends String>> ENABLED_VILLAGES;
	public static final ModConfigSpec.ConfigValue<List<? extends String>> ENABLED_PILLAGER_OUTPOST;
	
	public static final ModConfigSpec.IntValue SMALL_VILLAGE_WEIGHT;
	public static final ModConfigSpec.IntValue MEDIUM_VILLAGE_WEIGHT;
	public static final ModConfigSpec.IntValue LARGE_VILLAGE_WEIGHT;
	public static final ModConfigSpec.IntValue OUTPOST_WEIGHT;
	
	static {
		ModConfigSpec.Builder commonBuilder = new ModConfigSpec.Builder();
		
		commonBuilder.comment("Structure spawning settings").push(CATEGORY_STRUCTURES);
		GENERATE_SMALL_VILLAGES = commonBuilder
			                          .comment("Should CTOV small villages generate?")
			                          .define(KEY_GENERATE_SMALL_VILLAGES, true);
		
		GENERATE_MEDIUM_VILLAGES = commonBuilder
			                           .comment("Should CTOV medium villages generate?")
			                           .define(KEY_GENERATE_MEDIUM_VILLAGES, true);
		
		GENERATE_LARGE_VILLAGES = commonBuilder
			                          .comment("Should CTOV large villages generate?")
			                          .define(KEY_GENERATE_LARGE_VILLAGES, true);
		
		GENERATE_PILLAGER_OUTPOST = commonBuilder
			                            .comment("Should CTOV pillager outposts generate?")
			                            .define(KEY_GENERATE_PILLAGER_OUTPOST, true);
		
		ENABLED_VILLAGES = commonBuilder
			                   .comment("Which village variants should generate")
			                   .defineListAllowEmpty(KEY_ENABLED_VILLAGES, VILLAGE_POOL, CTOVConfigNeoForge::isValidVillageId);
		
		ENABLED_PILLAGER_OUTPOST = commonBuilder
			                           .comment("Which pillager outpost variants should generate")
			                           .defineListAllowEmpty(KEY_ENABLED_PILLAGER_OUTPOSTS, PILLAGER_OUTPOST_POOL, CTOVConfigNeoForge::isValidOutpostId);
		
		commonBuilder.pop();
		
		commonBuilder.comment("Structure spawning frequency (higher = more common)").push(CATEGORY_WEIGHTS);
		SMALL_VILLAGE_WEIGHT = commonBuilder
			                       .comment("CTOV small village spawn weight")
			                       .defineInRange(KEY_SMALL_VILLAGE_WEIGHT, DEFAULT_SMALL_VILLAGE_WEIGHT, 0, Integer.MAX_VALUE);
		
		MEDIUM_VILLAGE_WEIGHT = commonBuilder
			                        .comment("CTOV medium village spawn weight")
			                        .defineInRange(KEY_MEDIUM_VILLAGE_WEIGHT, DEFAULT_MEDIUM_VILLAGE_WEIGHT, 0, Integer.MAX_VALUE);
		
		LARGE_VILLAGE_WEIGHT = commonBuilder
			                       .comment("CTOV large village spawn weight")
			                       .defineInRange(KEY_LARGE_VILLAGE_WEIGHT, DEFAULT_LARGE_VILLAGE_WEIGHT, 0, Integer.MAX_VALUE);
		
		OUTPOST_WEIGHT = commonBuilder
			                 .comment("CTOV pillager outpost spawn weight")
			                 .defineInRange(KEY_OUTPOST_WEIGHT, DEFAULT_OUTPOST_WEIGHT, 0, Integer.MAX_VALUE);
		
		commonBuilder.pop();
		
		COMMON_CONFIG = commonBuilder.build();
	}
	
	private CTOVConfigNeoForge() {
		// static holder
	}
	
	private static boolean isValidVillageId(Object obj) {
		return obj instanceof String str && VILLAGE_POOL.contains(str);
	}
	
	private static boolean isValidOutpostId(Object obj) {
		return obj instanceof String str && PILLAGER_OUTPOST_POOL.contains(str);
	}
	
	private static boolean isCommonConfig(ModConfigEvent event) {
		return event.getConfig().getSpec() == COMMON_CONFIG;
	}
	
	@SubscribeEvent
	public static void onLoad(final ModConfigEvent event) {
		if (!isCommonConfig(event)) {
			return;
		}
		// Intentionally empty: kept for future load-time derived values / caching.
	}
	
	@SubscribeEvent
	public static void onReload(final ModConfigEvent event) {
		if (!isCommonConfig(event)) {
			return;
		}
		// Intentionally empty: kept for future reload-time derived values / caching.
	}
}