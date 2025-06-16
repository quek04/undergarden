package quek.undergarden.client.render;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.Util;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Function;

public class UGRenderTypes extends RenderType {

	private static final Function<ResourceLocation, RenderType> ENTITY_DECAL_TRANSLUCENT = Util.memoize((texture) ->
		create(
			"undergarden:entity_decal_translucent",
			DefaultVertexFormat.NEW_ENTITY,
			VertexFormat.Mode.QUADS,
			1536,
			CompositeState.builder()
				.setShaderState(RENDERTYPE_ENTITY_DECAL_SHADER)
				.setTextureState(new TextureStateShard(texture, false, false))
				.setDepthTestState(EQUAL_DEPTH_TEST)
				.setTransparencyState(TRANSLUCENT_TRANSPARENCY)
				.setLightmapState(LIGHTMAP)
				.setOverlayState(OVERLAY)
				.createCompositeState(false)));

	private UGRenderTypes(String name, VertexFormat format, VertexFormat.Mode mode, int bufferSize, boolean affectsCrumbling, boolean sortOnUpload, Runnable setupState, Runnable clearState) {
		super(name, format, mode, bufferSize, affectsCrumbling, sortOnUpload, setupState, clearState);
	}

	public static RenderType entityDecalTranslucent(ResourceLocation tex) {
		return ENTITY_DECAL_TRANSLUCENT.apply(tex);
	}
}
