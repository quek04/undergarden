package quek.undergarden.client;

import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.ColorTargetState;
import com.mojang.blaze3d.pipeline.DepthStencilState;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.CompareOp;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.rendertype.RenderSetup;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;
import quek.undergarden.Undergarden;

import java.util.function.Function;

public class UGRenderTypes {

	public static final RenderPipeline EYES_NO_CULL_PIPELINE = RenderPipeline.builder(RenderPipelines.MATRICES_FOG_SNIPPET)
		.withLocation(Undergarden.prefix("pipeline/eyes_no_cull"))
		.withVertexShader("core/entity")
		.withFragmentShader("core/entity")
		.withShaderDefine("EMISSIVE")
		.withShaderDefine("NO_OVERLAY")
		.withShaderDefine("NO_CARDINAL_LIGHTING")
		.withSampler("Sampler0")
		.withCull(false)
		.withColorTargetState(new ColorTargetState(BlendFunction.TRANSLUCENT))
		.withVertexFormat(DefaultVertexFormat.ENTITY, VertexFormat.Mode.QUADS)
		.withDepthStencilState(new DepthStencilState(CompareOp.LESS_THAN_OR_EQUAL, false))
		.build();

	public static final RenderPipeline DECAL_PIPELINE = RenderPipeline.builder(RenderPipelines.ENTITY_SNIPPET)
		.withLocation(Undergarden.prefix("pipeline/entity_decal_translucent"))
		.withShaderDefine("ALPHA_CUTOUT", 0.1F)
		.withShaderDefine("NO_OVERLAY")
		.withColorTargetState(new ColorTargetState(BlendFunction.TRANSLUCENT))
		.withCull(false)
		.withDepthStencilState(new DepthStencilState(CompareOp.EQUAL, false))
		.build();

	//TODO keep tweaking this until im happy
	public static final RenderPipeline GUI_INFECTION = RenderPipeline.builder(RenderPipelines.GUI_TEXTURED_SNIPPET)
		.withLocation(Undergarden.prefix("pipeline/gui_infection"))
		.withDepthStencilState(new DepthStencilState(CompareOp.ALWAYS_PASS, false))
		.withColorTargetState(new ColorTargetState(BlendFunction.OVERLAY))
		.build();

	private static final Function<Identifier, RenderType> EYES_NO_CULL = Util.memoize(
		texture -> RenderType.create("eyes", RenderSetup.builder(EYES_NO_CULL_PIPELINE).withTexture("Sampler0", texture).sortOnUpload().createRenderSetup())
	);

	private static final Function<Identifier, RenderType> ENTITY_DECAL_TRANSLUCENT = Util.memoize(
		texture -> RenderType.create("entity_decal_translucent", RenderSetup.builder(DECAL_PIPELINE)
			.withTexture("Sampler0", texture)
			.useLightmap()
			.useOverlay()
			.sortOnUpload()
			.createRenderSetup())
	);

	public static RenderType eyesNoCull(Identifier texture) {
		return EYES_NO_CULL.apply(texture);
	}

	public static RenderType entityDecalTranslucent(Identifier texture) {
		return ENTITY_DECAL_TRANSLUCENT.apply(texture);
	}
}
