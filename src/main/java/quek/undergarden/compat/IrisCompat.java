package quek.undergarden.compat;

import net.irisshaders.iris.api.v0.IrisApi;
import net.irisshaders.iris.api.v0.IrisProgram;
import net.irisshaders.iris.api.v0.IrisShadowProgram;
import net.neoforged.fml.ModList;
import quek.undergarden.Undergarden;
import quek.undergarden.client.UGRenderTypes;

public class IrisCompat {

	public static boolean isIrisLoaded() {
		return ModList.get().isLoaded("iris");
	}

	public static boolean areShadersLoaded() {
		return isIrisLoaded() && IrisApi.getInstance().isShaderPackInUse();
	}

	public static void init() {
		if (!isIrisLoaded()) return;
		IrisApi api = IrisApi.getInstance();
		api.assignPipeline(UGRenderTypes.DECAL_PIPELINE, IrisProgram.ENTITIES_TRANSLUCENT);
		try {
			assignShadowPipelines(api);
		} catch (LinkageError e) {
			Undergarden.LOGGER.warn("This Iris version has no shadow pipeline API, skipping shadow pass compatibility");
		}
	}

	// Older iris versions don't have this
	private static void assignShadowPipelines(IrisApi api) {
		api.assignPipelineShadow(UGRenderTypes.DECAL_PIPELINE, IrisShadowProgram.SHADOW_ENTITIES);
	}
}
