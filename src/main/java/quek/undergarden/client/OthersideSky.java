package quek.undergarden.client;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import net.minecraft.client.Camera;
import net.minecraft.client.CloudStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import quek.undergarden.Undergarden;

public class OthersideSky {
	private static final ResourceLocation VORTEX_LOCATION = new ResourceLocation(Undergarden.MODID, "textures/environment/otherside_vortex.png");
	private static final ResourceLocation CLOUDS_LOCATION = new ResourceLocation("textures/environment/clouds.png");
	private static final Minecraft minecraft = Minecraft.getInstance();
	private static final LevelRenderer levelRenderer = minecraft.levelRenderer;
	public static void renderSky(ClientLevel level, float partialTick, PoseStack poseStack, Camera camera, Matrix4f projectionMatrix, Runnable setupFog) {
		setupFog.run();

		Vec3 vec3 = level.getSkyColor(camera.getPosition(), partialTick);
		float f = (float) vec3.x;
		float f1 = (float) vec3.y;
		float f2 = (float) vec3.z;
		FogRenderer.levelFogColor();
		BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
		RenderSystem.depthMask(false);
		RenderSystem.setShaderColor(f, f1, f2, 1.0F);
		ShaderInstance shaderinstance = RenderSystem.getShader();
		levelRenderer.skyBuffer.bind();
		levelRenderer.skyBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, shaderinstance);
		VertexBuffer.unbind();
		RenderSystem.enableBlend();
				/*float[] afloat = level.effects().getSunriseColor(level.getTimeOfDay(partialTick), partialTick);
				if (afloat != null) {
					RenderSystem.setShader(GameRenderer::getPositionColorShader);
					RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
					poseStack.pushPose();
					poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
					float f3 = Mth.sin(level.getSunAngle(partialTick)) < 0.0F ? 180.0F : 0.0F;
					poseStack.mulPose(Axis.ZP.rotationDegrees(f3));
					poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
					float f4 = afloat[0];
					float f5 = afloat[1];
					float f6 = afloat[2];
					Matrix4f matrix4f = poseStack.last().pose();
					bufferbuilder.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
					bufferbuilder.vertex(matrix4f, 0.0F, 100.0F, 0.0F).color(f4, f5, f6, afloat[3]).endVertex();

					for (int j = 0; j <= 16; ++j) {
						float f7 = (float) j * ((float) Math.PI * 2F) / 16.0F;
						float f8 = Mth.sin(f7);
						float f9 = Mth.cos(f7);
						bufferbuilder.vertex(matrix4f, f8 * 120.0F, f9 * 120.0F, -f9 * 40.0F * afloat[3]).color(afloat[0], afloat[1], afloat[2], 0.0F).endVertex();
					}

					BufferUploader.drawWithShader(bufferbuilder.end());
					poseStack.popPose();
				}*/

		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		poseStack.pushPose();
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		poseStack.mulPose(Axis.XN.rotationDegrees(90.0F));
		//poseStack.mulPose(Axis.XP.rotationDegrees(level.getTimeOfDay(partialTick) * 360.0F));

		Matrix4f matrix4f1 = poseStack.last().pose();
		float size = 200.0F;
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderTexture(0, VORTEX_LOCATION);
		bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
		bufferbuilder.vertex(matrix4f1, -size, 100.0F, -size).uv(0.0F, 0.0F).endVertex();
		bufferbuilder.vertex(matrix4f1, size, 100.0F, -size).uv(1.0F, 0.0F).endVertex();
		bufferbuilder.vertex(matrix4f1, size, 100.0F, size).uv(1.0F, 1.0F).endVertex();
		bufferbuilder.vertex(matrix4f1, -size, 100.0F, size).uv(0.0F, 1.0F).endVertex();
		BufferUploader.drawWithShader(bufferbuilder.end());
				/*size = 20.0F;
				RenderSystem.setShaderTexture(0, MOON_LOCATION);
				int k = level.getMoonPhase();
				int l = k % 4;
				int i1 = k / 4 % 2;
				float f13 = (float) (l + 0) / 4.0F;
				float f14 = (float) (i1 + 0) / 2.0F;
				float f15 = (float) (l + 1) / 4.0F;
				float f16 = (float) (i1 + 1) / 2.0F;
				bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
				bufferbuilder.vertex(matrix4f1, -size, -100.0F, size).uv(f15, f16).endVertex();
				bufferbuilder.vertex(matrix4f1, size, -100.0F, size).uv(f13, f16).endVertex();
				bufferbuilder.vertex(matrix4f1, size, -100.0F, -size).uv(f13, f14).endVertex();
				bufferbuilder.vertex(matrix4f1, -size, -100.0F, -size).uv(f15, f14).endVertex();
				BufferUploader.drawWithShader(bufferbuilder.end());*/

				/*float f10 = level.getStarBrightness(partialTick) * f11;
				if (f10 > 0.0F) {
					RenderSystem.setShaderColor(f10, f10, f10, f10);
					FogRenderer.setupNoFog();
					levelRenderer.starBuffer.bind();
					levelRenderer.starBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, GameRenderer.getPositionShader());
					VertexBuffer.unbind();
					setupFog.run();
				}*/

		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.disableBlend();
		RenderSystem.defaultBlendFunc();
		poseStack.popPose();
		RenderSystem.setShaderColor(0.0F, 0.0F, 0.0F, 1.0F);
		double d0 = camera.getEntity().getEyePosition(partialTick).y/* - level.getLevelData().getHorizonHeight(level)*/;
		if (d0 < 0.0D) {
			poseStack.pushPose();
			poseStack.translate(0.0F, 12.0F, 0.0F);
			levelRenderer.darkBuffer.bind();
			levelRenderer.darkBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, shaderinstance);
			VertexBuffer.unbind();
			poseStack.popPose();
		}

		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.depthMask(true);
	}

	public static void renderClouds(int cloudY, int ticks, float partialTick, PoseStack poseStack, double camX, double camY, double camZ, Matrix4f projectionMatrix) {
		RenderSystem.disableCull();
		RenderSystem.enableBlend();
		RenderSystem.enableDepthTest();
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		RenderSystem.depthMask(true);
		double d1 = (((float)ticks + partialTick) * 0.09F);
		double d2 = camX / 12.0D;
		double d3 = (cloudY - (float)camY + 0.33F);
		double d4 = (camZ + d1) / 12.0D + (double)0.33F;
		d2 -= (Mth.floor(d2 / 2048.0D) * 2048);
		d4 -= (Mth.floor(d4 / 2048.0D) * 2048);
		float f3 = (float)(d2 - (double)Mth.floor(d2));
		float f4 = (float)(d3 / 4.0D - (double)Mth.floor(d3 / 4.0D)) * 4.0F;
		float f5 = (float)(d4 - (double)Mth.floor(d4));
		Vec3 cloudColor = new Vec3(0.69D, 0.59, 0.58D);
		int i = (int)Math.floor(d2);
		int j = (int)Math.floor(d3 / 4.0D);
		int k = (int)Math.floor(d4);
		if (i != levelRenderer.prevCloudX || j != levelRenderer.prevCloudY || k != levelRenderer.prevCloudZ || Minecraft.getInstance().options.getCloudsType() != levelRenderer.prevCloudsType || levelRenderer.prevCloudColor.distanceToSqr(cloudColor) > 2.0E-4D) {
			levelRenderer.prevCloudX = i;
			levelRenderer.prevCloudY = j;
			levelRenderer.prevCloudZ = k;
			levelRenderer.prevCloudColor = cloudColor;
			levelRenderer.prevCloudsType = minecraft.options.getCloudsType();
			levelRenderer.generateClouds = true;
		}

		if (levelRenderer.generateClouds) {
			levelRenderer.generateClouds = false;
			BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
			if (levelRenderer.cloudBuffer != null) {
				levelRenderer.cloudBuffer.close();
			}

			levelRenderer.cloudBuffer = new VertexBuffer(VertexBuffer.Usage.STATIC);
			BufferBuilder.RenderedBuffer buildClouds = levelRenderer.buildClouds(bufferbuilder, d2, d3, d4, cloudColor);
			levelRenderer.cloudBuffer.bind();
			levelRenderer.cloudBuffer.upload(buildClouds);
			VertexBuffer.unbind();
		}

		RenderSystem.setShader(GameRenderer::getPositionTexColorNormalShader);
		RenderSystem.setShaderTexture(0, CLOUDS_LOCATION);
		FogRenderer.levelFogColor();
		poseStack.pushPose();
		poseStack.scale(12.0F, 1.0F, 12.0F);
		poseStack.translate(-f3, f4, -f5);
		if (levelRenderer.cloudBuffer != null) {
			levelRenderer.cloudBuffer.bind();
			int l = levelRenderer.prevCloudsType == CloudStatus.FANCY ? 0 : 1;

			for(int i1 = l; i1 < 2; ++i1) {
				if (i1 == 0) {
					RenderSystem.colorMask(false, false, false, false);
				} else {
					RenderSystem.colorMask(true, true, true, true);
				}

				ShaderInstance shaderinstance = RenderSystem.getShader();
				levelRenderer.cloudBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, shaderinstance);
			}

			VertexBuffer.unbind();
		}

		poseStack.popPose();
		RenderSystem.enableCull();
		RenderSystem.disableBlend();
		RenderSystem.defaultBlendFunc();
	}
}
