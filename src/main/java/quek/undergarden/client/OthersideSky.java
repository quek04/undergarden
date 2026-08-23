package quek.undergarden.client;

import com.mojang.blaze3d.Blaze3D;
import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.state.level.LevelRenderState;
import net.minecraft.client.renderer.state.level.SkyRenderState;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.data.AtlasIds;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.neoforged.neoforge.client.CustomSkyboxRenderer;
import org.joml.*;
import quek.undergarden.Undergarden;

import java.lang.Math;
import java.util.OptionalDouble;
import java.util.OptionalInt;

public class OthersideSky implements CustomSkyboxRenderer {
	private static final Identifier VORTEX_SPRITE = Undergarden.prefix("environment/celestial/otherside_vortex");
	private final RenderSystem.AutoStorageIndexBuffer quadIndices = RenderSystem.getSequentialBuffer(VertexFormat.Mode.QUADS);
	private final TextureAtlas celestialsAtlas;
	private GpuBuffer vortexBuffer;
	private final GpuBuffer topSkyBuffer;

	public OthersideSky() {
		this.celestialsAtlas = Minecraft.getInstance().getAtlasManager().getAtlasOrThrow(AtlasIds.CELESTIALS);
//		this.vortexBuffer = buildVortexQuad(this.celestialsAtlas);

		try (ByteBufferBuilder builder = ByteBufferBuilder.exactlySized(10 * DefaultVertexFormat.POSITION.getVertexSize())) {
			BufferBuilder bufferBuilder = new BufferBuilder(builder, VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION);
			this.buildSkyDisc(bufferBuilder, 16.0F);

			try (MeshData meshData = bufferBuilder.buildOrThrow()) {
				this.topSkyBuffer = RenderSystem.getDevice().createBuffer(() -> "Top sky vertex buffer", 32, meshData.vertexBuffer());
			}
		}
	}

	@Override
	public boolean renderSky(LevelRenderState levelRenderState, SkyRenderState skyRenderState, Matrix4fc modelViewMatrix, Runnable setupFog) {
		setupFog.run();

		this.renderSkyDisc(levelRenderState.skyRenderState.skyColor);

		if (this.vortexBuffer == null) {
			this.vortexBuffer = buildVortexQuad(this.celestialsAtlas);
		}
		PoseStack poseStack = new PoseStack();
		poseStack.pushPose();
		poseStack.mulPose(Axis.XN.rotationDegrees(90.0F));
		poseStack.pushPose();
		poseStack.mulPose(Axis.YN.rotationDegrees((float) Blaze3D.getTime()));
		this.renderVortex(poseStack);

		return true;
	}

	private void renderVortex(PoseStack poseStack) {
		Matrix4fStack modelViewStack = RenderSystem.getModelViewStack();
		modelViewStack.pushMatrix();
		modelViewStack.mul(poseStack.last().pose());
		modelViewStack.translate(0.0F, 100.0F, 0.0F);
		modelViewStack.scale(200.0F, 1.0F, 200.0F);
		GpuBufferSlice dynamicTransforms = RenderSystem.getDynamicUniforms()
			.writeTransform(modelViewStack, new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), new Vector3f(), new Matrix4f());
		GpuTextureView color = Minecraft.getInstance().getMainRenderTarget().getColorTextureView();
		GpuTextureView depth = Minecraft.getInstance().getMainRenderTarget().getDepthTextureView();
		GpuBuffer indexBuffer = this.quadIndices.getBuffer(6);

		try (RenderPass renderPass = RenderSystem.getDevice()
			.createCommandEncoder()
			.createRenderPass(() -> "Sky vortex", color, OptionalInt.empty(), depth, OptionalDouble.empty())) {
			renderPass.setPipeline(UGRenderTypes.OTHERSIDE_VORTEX);
			RenderSystem.bindDefaultUniforms(renderPass);
			renderPass.setUniform("DynamicTransforms", dynamicTransforms);
			renderPass.bindTexture("Sampler0", this.celestialsAtlas.getTextureView(), this.celestialsAtlas.getSampler());
			renderPass.setVertexBuffer(0, this.vortexBuffer);
			renderPass.setIndexBuffer(indexBuffer, this.quadIndices.type());
			renderPass.drawIndexed(0, 0, 6, 1);
		}

		modelViewStack.popMatrix();
	}

	private static GpuBuffer buildVortexQuad(TextureAtlas atlas) {
		return buildCelestialQuad("Vortex quad", atlas.getSprite(VORTEX_SPRITE));
	}

	private static GpuBuffer buildCelestialQuad(String name, TextureAtlasSprite sprite) {
		VertexFormat format = DefaultVertexFormat.POSITION_TEX;

		GpuBuffer var6;
		try (ByteBufferBuilder byteBufferBuilder = ByteBufferBuilder.exactlySized(4 * format.getVertexSize())) {
			BufferBuilder bufferBuilder = new BufferBuilder(byteBufferBuilder, VertexFormat.Mode.QUADS, format);
			bufferBuilder.addVertex(-1.0F, 0.0F, -1.0F).setUv(sprite.getU0(), sprite.getV0());
			bufferBuilder.addVertex(1.0F, 0.0F, -1.0F).setUv(sprite.getU1(), sprite.getV0());
			bufferBuilder.addVertex(1.0F, 0.0F, 1.0F).setUv(sprite.getU1(), sprite.getV1());
			bufferBuilder.addVertex(-1.0F, 0.0F, 1.0F).setUv(sprite.getU0(), sprite.getV1());

			try (MeshData mesh = bufferBuilder.buildOrThrow()) {
				var6 = RenderSystem.getDevice().createBuffer(() -> name, 32, mesh.vertexBuffer());
			}
		}

		return var6;
	}

	public void renderSkyDisc(int skyColor) {
		GpuBufferSlice dynamicTransforms = RenderSystem.getDynamicUniforms()
			.writeTransform(RenderSystem.getModelViewMatrix(), ARGB.vector4fFromARGB32(skyColor), new Vector3f(), new Matrix4f());
		GpuTextureView colorTexture = Minecraft.getInstance().getMainRenderTarget().getColorTextureView();
		GpuTextureView depthTexture = Minecraft.getInstance().getMainRenderTarget().getDepthTextureView();

		try (RenderPass renderPass = RenderSystem.getDevice()
			.createCommandEncoder()
			.createRenderPass(() -> "Sky disc", colorTexture, OptionalInt.empty(), depthTexture, OptionalDouble.empty())) {
			renderPass.setPipeline(RenderPipelines.SKY);
			RenderSystem.bindDefaultUniforms(renderPass);
			renderPass.setUniform("DynamicTransforms", dynamicTransforms);
			renderPass.setVertexBuffer(0, this.topSkyBuffer);
			renderPass.draw(0, 10);
		}
	}

	private void buildSkyDisc(VertexConsumer builder, float yy) {
		float x = Math.signum(yy) * 512.0F;
		builder.addVertex(0.0F, yy, 0.0F);

		for (int i = -180; i <= 180; i += 45) {
			builder.addVertex(x * Mth.cos(i * (float) (Math.PI / 180.0)), yy, 512.0F * Mth.sin(i * (float) (Math.PI / 180.0)));
		}
	}
}