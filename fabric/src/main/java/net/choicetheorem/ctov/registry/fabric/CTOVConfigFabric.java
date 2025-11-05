package net.choicetheorem.ctov.registry.fabric;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import net.choicetheorem.ctov.CTOV;
import net.choicetheorem.ctov.registry.fabric.CTOVConfigFabric;

@Config(name = CTOV.MOD_ID)
public class CTOVConfigFabric implements ConfigData {
	// Settings
	@ConfigEntry.Gui.CollapsibleObject
	public Structures structures = new Structures();
	@ConfigEntry.Gui.CollapsibleObject
	public Weights weights = new Weights();
	
	public CTOVConfigFabric() {
	}
	
	public static class Structures {
		@Comment("Should CTOV small village generates?")
		public boolean generatesmallVillage = true;
		@Comment("Should CTOV medium village generates?")
		public boolean generatemediumVillage = true;
		@Comment("Should CTOV large village generates?")
		public boolean generatelargeVillage = true;
		@Comment("Should CTOV Pillager Outpost generates?")
		public boolean generatePillagerOutpost = true;
		@Comment("Which village should generates")
		public String[] enabledvillage = new String[]{"beach", "christmas", "desert", "desert_oasis", "dark_forest",
			"jungle", "jungle_tree", "mesa", "mesa_fortified", "mountain", "mountain_alpine", "mushroom", "plains",
			"plains_fortified",	"savanna", "savanna_na", "snowy_igloo", "swamp", "swamp_fortified", "taiga", "taiga_fortified"};
		@Comment("Which pillager outpost should generates")
		public String[] enabledpillageroutpost = new String[]{"beach","dark_forest", "desert", "jungle", "badlands",
			"mountain", "plains", "savanna", "snowy", "swamp", "taiga"};
		public Structures() {
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
		
		public Weights() {
		}
	}
}
