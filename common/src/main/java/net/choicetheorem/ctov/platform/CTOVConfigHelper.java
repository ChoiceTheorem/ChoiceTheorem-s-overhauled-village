package net.choicetheorem.ctov.platform;

import java.lang.reflect.Method;
import java.util.List;

public class CTOVConfigHelper {
	
	public static boolean generateSmallVillage() {
		return invokeBoolean("generateSmallVillage");
	}
	
	public static boolean generateMediumVillage() {
		return invokeBoolean("generateMediumVillage");
	}
	
	public static boolean generateLargeVillage() {
		return invokeBoolean("generateLargeVillage");
	}
	
	public static boolean generatePillagerOutpost() {
		return invokeBoolean("generatePillagerOutpost");
	}
	
	public static int smallVillageWeight() {
		return invokeInt("smallVillageWeight");
	}
	
	public static int mediumVillageWeight() {
		return invokeInt("mediumVillageWeight");
	}
	
	public static int largeVillageWeight() {
		return invokeInt("largeVillageWeight");
	}
	
	public static int outpostWeight() {
		return invokeInt("outpostWeight");
	}
	
	public static List<? extends String> enabledVillages() {
		return invokeList("enabledVillages");
	}
	
	public static List<? extends String> enabledPillagerOutposts() {
		return invokeList("enabledPillagerOutposts");
	}
	
	private static boolean invokeBoolean(String methodName) {
		try {
			return (boolean) resolveImpl().getMethod(methodName).invoke(null);
		} catch (ReflectiveOperationException e) {
			throw new IllegalStateException("Unable to resolve CTOV config helper method: " + methodName, e);
		}
	}
	
	private static int invokeInt(String methodName) {
		try {
			return (int) resolveImpl().getMethod(methodName).invoke(null);
		} catch (ReflectiveOperationException e) {
			throw new IllegalStateException("Unable to resolve CTOV config helper method: " + methodName, e);
		}
	}
	
	@SuppressWarnings("unchecked")
	private static List<? extends String> invokeList(String methodName) {
		try {
			return (List<? extends String>) resolveImpl().getMethod(methodName).invoke(null);
		} catch (ReflectiveOperationException e) {
			throw new IllegalStateException("Unable to resolve CTOV config helper method: " + methodName, e);
		}
	}
	
	private static Class<?> resolveImpl() {
		return ImplHolder.IMPL_CLASS;
	}
	
	private static final class ImplHolder {
		private static final String[] CANDIDATES = {
			"net.choicetheorem.ctov.platform.fabric.CTOVConfigHelperImpl",
			"net.choicetheorem.ctov.platform.neoforge.CTOVConfigHelperImpl"
		};
		
		private static final Class<?> IMPL_CLASS = loadImplClass();
		
		private static Class<?> loadImplClass() {
			ClassLoader loader = CTOVConfigHelper.class.getClassLoader();
			for (String candidate : CANDIDATES) {
				try {
					return Class.forName(candidate, true, loader);
				} catch (ClassNotFoundException ignored) {
					// Try the next platform implementation.
				}
			}
			throw new IllegalStateException("No CTOV config helper implementation found for the current runtime.");
		}
	}
}