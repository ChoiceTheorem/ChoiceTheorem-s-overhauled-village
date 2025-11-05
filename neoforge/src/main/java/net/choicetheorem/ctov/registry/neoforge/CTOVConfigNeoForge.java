package net.choicetheorem.ctov.registry.neoforge;

import net.choicetheorem.ctov.CTOV;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.bus.api.SubscribeEvent;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@EventBusSubscriber(modid = CTOV.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class CTOVConfigNeoForge {
	public static ModConfigSpec COMMON_CONFIG;
	public static final String CATEGORY_STRUCTURES = "structures";
	public static final String CATEGORY_WEIGHTS = "weights";
	public static final ModConfigSpec.BooleanValue GENERATE_SMALL_VILLAGES;
	public static final ModConfigSpec.BooleanValue GENERATE_MEDIUM_VILLAGES;
	public static final ModConfigSpec.BooleanValue GENERATE_LARGE_VILLAGES;
	public static final ModConfigSpec.BooleanValue GENERATE_PILLAGER_OUTPOST;
	//public static final ModConfigSpec.ConfigValue<String[]> ENABLED_VILLAGES;
	//public static final ModConfigSpec.ConfigValue<String[]> ENABLED_PILLAGER_OUTPOST;
	public static final ModConfigSpec.IntValue SMALL_VILLAGE_WEIGHT;
	public static final ModConfigSpec.IntValue MEDIUM_VILLAGE_WEIGHT;
	public static final ModConfigSpec.IntValue LARGE_VILLAGE_WEIGHT;
	public static final ModConfigSpec.IntValue OUTPOST_WEIGHT;
	public static final String[] VILLAGE_POOL= new String[]{
		"beach", "christmas", "desert", "desert_oasis", "dark_forest",
		"jungle", "jungle_tree", "mesa", "mesa_fortified", "mountain",
		"mountain_alpine", "mushroom", "plains", "plains_fortified",
		"savanna", "savanna_na", "snowy_igloo", "swamp",
		"swamp_fortified", "taiga", "taiga_fortified"};
	public static final String[] PILLAGER_OUTPOST_POOL = new String[]{
		"beach", "dark_forest", "desert", "jungle", "badlands", "mountain",
		"plains", "savanna", "snowy", "swamp", "taiga"
	};
	static {
		ModConfigSpec.Builder COMMON_BUILDER = new ModConfigSpec.Builder();
		
		COMMON_BUILDER.comment("Structure spawning").push(CATEGORY_STRUCTURES);
		GENERATE_SMALL_VILLAGES = COMMON_BUILDER.comment("Should CTOV small village generates?")
			.define("generatesmallVillage", true);
		GENERATE_MEDIUM_VILLAGES = COMMON_BUILDER.comment("Should CTOV medium village generates?")
			.define("generatemediumVillage", true);
		GENERATE_LARGE_VILLAGES = COMMON_BUILDER.comment("Should CTOV large village generates?")
			.define("generatelargeVillage", true);
		GENERATE_PILLAGER_OUTPOST = COMMON_BUILDER.comment("Should CTOV Pillager Outpost generates?")
			.define("generatePillagerOutpost", true);
		/*ENABLED_VILLAGES = COMMON_BUILDER.comment("Which villages should generate")
			.define("enabledVillages",VILLAGE_POOL);
		ENABLED_PILLAGER_OUTPOST = COMMON_BUILDER.comment("Which pillager outpost should generates")
			.define("enabledpillageroutpost",PILLAGER_OUTPOST_POOL);*/
		COMMON_BUILDER.pop();
		
		COMMON_BUILDER.comment("Structure spawning frequency").push(CATEGORY_WEIGHTS);
		SMALL_VILLAGE_WEIGHT = COMMON_BUILDER.comment("CTOV small village spawn chances")
			.defineInRange("smallVillageWeight", 10,0,Integer.MAX_VALUE);
		MEDIUM_VILLAGE_WEIGHT = COMMON_BUILDER.comment("CTOV medium village spawn chance")
			.defineInRange("mediumVillageWeight", 4,0,Integer.MAX_VALUE);
		LARGE_VILLAGE_WEIGHT = COMMON_BUILDER.comment("CTOV large village spawn chance")
			.defineInRange("generatelargeVillage", 1,0,Integer.MAX_VALUE);
		OUTPOST_WEIGHT = COMMON_BUILDER.comment("CTOV Pillager Outpost spawn chance")
			.defineInRange("generatePillagerOutpost", 1,0,Integer.MAX_VALUE);
		
		COMMON_BUILDER.pop();
		
		COMMON_CONFIG = COMMON_BUILDER.build();
	}
	
	@SubscribeEvent
	public static void onLoad(final ModConfigEvent event) {
		if (event.getConfig().getSpec() == COMMON_CONFIG) {
		
		}
	}
	
	@SubscribeEvent
	public static void onReload(final ModConfigEvent event) {
		if (event.getConfig().getSpec() == COMMON_CONFIG) {
		
		}
	}
}