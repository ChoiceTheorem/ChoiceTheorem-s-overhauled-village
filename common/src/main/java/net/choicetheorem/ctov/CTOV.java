package net.choicetheorem.ctov;

import net.choicetheorem.ctov.platform.CTOVConfigHelper;
import net.choicetheorem.ctov.utils.CTOVStructureHelper;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;

import static net.choicetheorem.ctov.platform.CTOVConfigHelper.*;
import static net.choicetheorem.ctov.utils.TextUtils.res;

public class CTOV {
    public static final String MOD_ID = "ctov";
    
    public static void init() {
        //System.out.println(ctovExpectPlatform.getConfigDirectory().toAbsolutePath().normalize().toString());
    }
    
    public  static void registerstructure(MinecraftServer server) {
        HolderLookup.Provider registryProvider = server.registryAccess();
        HolderLookup<StructureSet> StructureSetRegistry = registryProvider.lookupOrThrow(Registries.STRUCTURE_SET);
        HolderLookup<Structure> StructureRegistry = registryProvider.lookupOrThrow(Registries.STRUCTURE);
        String[] enabledpillageroutpost = {"beach", "dark_forest", "desert", "jungle", "badlands", "mountain",
            "plains", "savanna", "snowy", "swamp", "taiga"};
        String[] enabledvillage = {"beach", "christmas", "desert", "desert_oasis", "dark_forest",
            "jungle", "jungle_tree", "mesa", "mesa_fortified", "mountain", "mountain_alpine", "mushroom", "plains",
            "plains_fortified",	"savanna", "savanna_na", "snowy_igloo", "swamp", "swamp_fortified", "taiga", "taiga_fortified"};
        ResourceLocation villagesetLocation = ResourceLocation.fromNamespaceAndPath("minecraft", "villages");
        ResourceLocation outpostsetLocation = ResourceLocation.fromNamespaceAndPath("minecraft", "pillager_outposts");
        if (generatePillagerOutpost()) {
            for (String outpost : enabledpillageroutpost){
                ResourceLocation pillagerlocation = res("pillager_outpost_"+outpost);
                new CTOVStructureHelper(server, StructureSetRegistry, StructureRegistry, outpostsetLocation, pillagerlocation, OutpostWeight());
            }
        }
        if (generatesmallVillage()) {
            for (String village : enabledvillage){
                ResourceLocation villagelocation = res("small/village_"+ village);
                new CTOVStructureHelper(server, StructureSetRegistry, StructureRegistry, villagesetLocation, villagelocation, smallVillageWeight());
            }
        }
        if (generatemediumVillage()) {
            for (String village : enabledvillage){
                ResourceLocation villagelocation = res("medium/village_"+ village);
                new CTOVStructureHelper(server, StructureSetRegistry, StructureRegistry, villagesetLocation, villagelocation, mediumVillageWeight());
            }
        }
        if (generatelargeVillage()) {
            for (String village : enabledvillage){
                ResourceLocation villagelocation = res("large/village_"+ village);
                new CTOVStructureHelper(server, StructureSetRegistry, StructureRegistry, villagesetLocation, villagelocation, largeVillageWeight());
            }
        }
    }
}
