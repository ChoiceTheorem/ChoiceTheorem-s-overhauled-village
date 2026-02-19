package net.choicetheorem.ctov.worldgen.processor;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.choicetheorem.ctov.utils.PlatformHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;
import org.jetbrains.annotations.NotNull;

/**
 * A custom {@link StructureProcessor} that conditionally applies another list of structure processors based on
 * the presence of a specified mod. This processor ensures compatibility with structures defined by other mods.
 * The processor allows conditional execution by dynamically invoking registered processors from a specified processor list.
 * The class utilizes a codec-based approach for deserialization and reads the required mod ID and processor list
 * from JSON definitions, ensuring flexibility and extensibility in mod integration.
 */
public class ModularCompatProcessor extends StructureProcessor {
	private static final String CODEC_FIELD_MOD_ID = "modid";
	private static final String CODEC_FIELD_REFERENCE = "reference";
	
	// Codec to read "modid" and "compat_list" from JSON
	public static final MapCodec<ModularCompatProcessor> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
		Codec.STRING.fieldOf(CODEC_FIELD_MOD_ID).forGetter(p -> p.modId),
		ResourceLocation.CODEC.fieldOf(CODEC_FIELD_REFERENCE).forGetter(p -> p.compatProcessorListId)
	).apply(inst, ModularCompatProcessor::new));
	
	private final String modId;
	private final ResourceLocation compatProcessorListId;
	public static final StructureProcessorType<ModularCompatProcessor> TYPE = () -> CODEC;
	public ModularCompatProcessor(String modid, ResourceLocation compatList) {
		this.modId = modid;
		this.compatProcessorListId = compatList;
	}
	
	@Override
	public StructureTemplate.StructureBlockInfo processBlock(
		LevelReader level,
		BlockPos pos,
		BlockPos pivot,
		StructureTemplate.StructureBlockInfo blockInfo,
		StructureTemplate.StructureBlockInfo nextBlockInfo,
		StructurePlaceSettings settings
	) {
		if (!PlatformHelper.isModLoaded(modId)) {
			return nextBlockInfo;
		}
		
		return applyCompatProcessorList(level, pos, pivot, blockInfo, nextBlockInfo, settings);
	}
	
	private StructureTemplate.StructureBlockInfo applyCompatProcessorList(
		LevelReader level,
		BlockPos pos,
		BlockPos pivot,
		StructureTemplate.StructureBlockInfo originalBlockInfo,
		StructureTemplate.StructureBlockInfo currentBlockInfo,
		StructurePlaceSettings settings
	) {
		var registry = level.registryAccess().registryOrThrow(Registries.PROCESSOR_LIST);
		ResourceKey<StructureProcessorList> processorListKey = ResourceKey.create(Registries.PROCESSOR_LIST, compatProcessorListId);
		
		return registry.getOptional(processorListKey)
			       .map(list -> {
				       StructureTemplate.StructureBlockInfo current = currentBlockInfo;
				       for (StructureProcessor processor : list.list()) {
					       current = processor.processBlock(level, pos, pivot, originalBlockInfo, current, settings);
					       if (current == null) {
						       break;
					       }
				       }
				       return current;
			       })
			       .orElse(currentBlockInfo);
	}
	
	@Override
	public @NotNull StructureProcessorType<?> getType() {
		return TYPE;
	}
}
