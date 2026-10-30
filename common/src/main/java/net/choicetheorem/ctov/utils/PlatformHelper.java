package net.choicetheorem.ctov.utils;

public class PlatformHelper {
	public static boolean isModLoaded(String modid) {
		return ImplHolder.invoke(modid);
	}
	
	private static final class ImplHolder {
		private static final String[] CANDIDATES = {
			"net.choicetheorem.ctov.utils.fabric.PlatformHelperImpl",
			"net.choicetheorem.ctov.utils.neoforge.PlatformHelperImpl"
		};
		
		private static boolean invoke(String modid) {
			try {
				Class<?> impl = resolveImpl();
				return (boolean) impl.getMethod("isModLoaded", String.class).invoke(null, modid);
			} catch (ReflectiveOperationException e) {
				throw new IllegalStateException("Unable to resolve platform helper for mod detection.", e);
			}
		}
		
		private static Class<?> resolveImpl() {
			ClassLoader loader = PlatformHelper.class.getClassLoader();
			for (String candidate : CANDIDATES) {
				try {
					return Class.forName(candidate, true, loader);
				} catch (ClassNotFoundException ignored) {
					// Try the next platform implementation.
				}
			}
			throw new IllegalStateException("No platform helper implementation found for the current runtime.");
		}
	}
}
