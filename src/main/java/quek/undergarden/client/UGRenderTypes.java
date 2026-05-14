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

	private static final Function<Identifier, RenderType> EYES_NO_CULL = Util.memoize(
		texture -> RenderType.create("eyes", RenderSetup.builder(EYES_NO_CULL_PIPELINE).withTexture("Sampler0", texture).sortOnUpload().createRenderSetup())
	);

	public static RenderType eyesNoCull(Identifier texture) {
		return EYES_NO_CULL.apply(texture);
	}
}
