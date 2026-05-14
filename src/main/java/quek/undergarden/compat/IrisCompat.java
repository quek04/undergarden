package quek.undergarden.compat;

import net.irisshaders.iris.api.v0.IrisApi;
import net.neoforged.fml.ModList;

public class IrisCompat {

	public static boolean isIrisLoaded() {
		return ModList.get().isLoaded("iris");
	}

	public static boolean areShadersLoaded() {
		return isIrisLoaded() && IrisApi.getInstance().isShaderPackInUse();
	}
}
