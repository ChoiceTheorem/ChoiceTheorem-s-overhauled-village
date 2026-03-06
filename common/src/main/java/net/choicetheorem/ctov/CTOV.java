package net.choicetheorem.ctov;

import dev.worldgen.lithostitched.api.event.AddWorldgenModifiersEvent;
import net.choicetheorem.ctov.utils.CTOVStructureHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup.RegistryLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.levelgen.structure.BuiltinStructureSets;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;

import java.util.List;

import static net.choicetheorem.ctov.platform.CTOVConfigHelper.*;

public class CTOV {
    public static final String MOD_ID = "ctov";
    
    public static void init() {
        //System.out.println(ctovExpectPlatform.getConfigDirectory().toAbsolutePath().normalize().toString());
    }
    
    public  static void registerstructure(MinecraftServer server) {
        List<String> outpostVariants = List.of(
            "beach", "dark_forest", "desert", "jungle", "badlands",
            "mountain", "plains", "savanna", "snowy", "swamp", "taiga"
        );
        
        AddWorldgenModifiersEvent.EVENT.register((registries, consumer) -> {
            Holder.Reference<StructureSet> outpostSet = registries.lookupOrThrow(Registries.STRUCTURE_SET).getOrThrow(BuiltinStructureSets.PILLAGER_OUTPOSTS);
            Holder.Reference<StructureSet> villageSet = registries.lookupOrThrow(Registries.STRUCTURE_SET).getOrThrow(BuiltinStructureSets.VILLAGES);
            RegistryLookup<Structure> registry = registries.lookupOrThrow(Registries.STRUCTURE);
            
            if (generatePillagerOutpost()) {
                for (String variant : outpostVariants) {
                    CTOVStructureHelper.addModifier(consumer, registry, outpostSet, "pillager_outpost_" + variant, OutpostWeight());
                }
            }
            
            if (generatesmallVillage()) {
                for (String variant : enabledvillage()) {
                    CTOVStructureHelper.addModifier(consumer, registry, villageSet, "small/village_" + variant, smallVillageWeight());
                }
            }
            
            if (generatemediumVillage()) {
                for (String variant : enabledvillage()) {
                    CTOVStructureHelper.addModifier(consumer, registry, villageSet, "medium/village_" + variant, mediumVillageWeight());
                }
            }
            
            if (generatelargeVillage()) {
                for (String variant : enabledvillage()) {
                    CTOVStructureHelper.addModifier(consumer, registry, villageSet, "large/village_" + variant, largeVillageWeight());
                }
            }
        });
    }
}
