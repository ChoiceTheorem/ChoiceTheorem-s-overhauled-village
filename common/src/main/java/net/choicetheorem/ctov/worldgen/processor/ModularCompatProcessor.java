package net.choicetheorem.ctov.worldgen.processor;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.choicetheorem.ctov.utils.PlatformHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;

/**
 * A custom {@link StructureProcessor} that conditionally applies another list of structure processors based on
 * the presence of a specified mod. This processor ensures compatibility with structures defined by other mods.
 * The processor allows conditional execution by dynamically invoking registered processors from a specified processor list.
 * The class utilizes a codec-based approach for deserialization and reads the required mod ID and processor list
 * from JSON definitions, ensuring flexibility and extensibility in mod integration.
 */
public class ModularCompatProcessor implements StructureProcessor {
	private static final String CODEC_FIELD_MOD_ID = "modid";
	private static final String CODEC_FIELD_REFERENCE = "reference";
	
	// Codec to read "modid" and "compat_list" from JSON
	public static final MapCodec<ModularCompatProcessor> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
		Codec.STRING.fieldOf(CODEC_FIELD_MOD_ID).forGetter(p -> p.modId),
		Identifier.CODEC.fieldOf(CODEC_FIELD_REFERENCE).forGetter(p -> p.compatProcessorListId)
	).apply(inst, ModularCompatProcessor::new));
	
	private final String modId;
	private final Identifier compatProcessorListId;
	public ModularCompatProcessor(String modid, Identifier compatList) {
		this.modId = modid;
		this.compatProcessorListId = compatList;
	}
	
	@Override
	public StructureTemplate.StructureBlockInfo processBlock(
		LevelReader level,
		BlockPos offset,
		BlockPos pos,
		BlockPos pivot,
		StructureTemplate.StructureBlockInfo blockInfo,
		StructurePlaceSettings settings
	) {
		if (!PlatformHelper.isModLoaded(modId)) {
			return blockInfo;
		}
		
		return applyCompatProcessorList(level, offset, pos, pivot, blockInfo, settings);
	}
	
	private StructureTemplate.StructureBlockInfo applyCompatProcessorList(
		LevelReader level,
		BlockPos offset,
		BlockPos pos,
		BlockPos pivot,
		StructureTemplate.StructureBlockInfo originalBlockInfo,
		StructurePlaceSettings settings
	) {
		ResourceKey<StructureProcessorList> processorListKey = ResourceKey.create(Registries.PROCESSOR_LIST, compatProcessorListId);
		
		return level.registryAccess()
			       .lookup(Registries.PROCESSOR_LIST)
			       .flatMap(registry -> registry.get(processorListKey))
			       .map(holder -> {
				       StructureTemplate.StructureBlockInfo current = originalBlockInfo;
				       for (StructureProcessor processor : holder.value().list()) {
					       current = processor.processBlock(level, offset, pos, pivot, current, settings);
					       if (current == null) {
					       	break;
					       }
				       }
				       return current;
				   })
			       .orElse(originalBlockInfo);
	}
	
	@Override
	public MapCodec<? extends StructureProcessor> codec() {
		return (MapCodec<? extends StructureProcessor>) (MapCodec<?>) CODEC;
	}
}
