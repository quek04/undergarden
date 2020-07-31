package quek.undergarden.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ShardParticle extends DeceleratingParticle {

    protected ShardParticle(ClientWorld clientWorld, double p_i232421_2_, double p_i232421_4_, double p_i232421_6_, double p_i232421_8_, double p_i232421_10_, double p_i232421_12_) {
        super(clientWorld, p_i232421_2_, p_i232421_4_, p_i232421_6_, p_i232421_8_, p_i232421_10_, p_i232421_12_);
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public void move(double p_187110_1_, double p_187110_3_, double p_187110_5_) {
        this.setBoundingBox(this.getBoundingBox().offset(p_187110_1_, p_187110_3_, p_187110_5_));
        this.resetPositionToBB();
    }

    public float getScale(float p_217561_1_) {
        float lvt_2_1_ = ((float)this.age + p_217561_1_) / (float)this.maxAge;
        return this.particleScale * (1.0F - lvt_2_1_ * lvt_2_1_ * 0.5F);
    }

    public int getBrightnessForRender(float p_189214_1_) {
        float lvt_2_1_ = ((float)this.age + p_189214_1_) / (float)this.maxAge;
        lvt_2_1_ = MathHelper.clamp(lvt_2_1_, 0.0F, 1.0F);
        int lvt_3_1_ = super.getBrightnessForRender(p_189214_1_);
        int lvt_4_1_ = lvt_3_1_ & 255;
        int lvt_5_1_ = lvt_3_1_ >> 16 & 255;
        lvt_4_1_ += (int)(lvt_2_1_ * 15.0F * 16.0F);
        if (lvt_4_1_ > 240) {
            lvt_4_1_ = 240;
        }

        return lvt_4_1_ | lvt_5_1_ << 16;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite p_i50823_1_) {
            this.spriteSet = p_i50823_1_;
        }

        public Particle makeParticle(BasicParticleType p_199234_1_, ClientWorld p_199234_2_, double p_199234_3_, double p_199234_5_, double p_199234_7_, double p_199234_9_, double p_199234_11_, double p_199234_13_) {
            ShardParticle lvt_15_1_ = new ShardParticle(p_199234_2_, p_199234_3_, p_199234_5_, p_199234_7_, p_199234_9_, p_199234_11_, p_199234_13_);
            lvt_15_1_.selectSpriteRandomly(this.spriteSet);
            return lvt_15_1_;
        }
    }
}
