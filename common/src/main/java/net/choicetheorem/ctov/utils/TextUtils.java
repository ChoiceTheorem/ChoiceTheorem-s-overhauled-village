package net.choicetheorem.ctov.utils;

import net.choicetheorem.ctov.CTOV;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;

public class TextUtils {
	private static final MutableComponent NO_EFFECTS = Component.translatable("effect.none").withStyle(ChatFormatting.GRAY);

	public static MutableComponent getTranslatable(String string, Object... args) {
		return Component.translatable(CTOV.MOD_ID + "." + string, args);
	}

	public static Identifier res(String name) {
		return Identifier.fromNamespaceAndPath(CTOV.MOD_ID, name);
	}
	public static Identifier res(String namespace, String name) {
		return Identifier.fromNamespaceAndPath(namespace, name);
	}
}
