package net.choicetheorem.ctov.integration.fabric;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import net.choicetheorem.ctov.registry.fabric.CTOVConfigFabric;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;

@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi {
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return parent -> {
			CTOVConfigFabric config = AutoConfig.getConfigHolder(CTOVConfigFabric.class).getConfig();

			return ConfigBuilder.create()
			                    .setParentScreen(parent)
			                    .setTitle(Component.translatable("text.autoconfig.ctov.title"))
			                    .setSavingRunnable(() -> AutoConfig.getConfigHolder(CTOVConfigFabric.class).save())
			                    .build();
		};
	}
}