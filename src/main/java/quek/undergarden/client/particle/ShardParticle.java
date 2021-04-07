package quek.undergarden.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ShardParticle extends DeceleratingParticle {

    protected ShardParticle(ClientWorld clientWorld, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(clientWorld, x, y, z, motionX, motionY, motionZ);
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public void move(double x, double y, double z) {
        this.setBoundingBox(this.getBoundingBox().move(x, y, z));
        this.setLocationFromBoundingbox();
    }

    public float getQuadSize(float scaleFactor) {
        float lvt_2_1_ = ((float)this.age + scaleFactor) / (float)this.lifetime;
        return this.quadSize * (1.0F - lvt_2_1_ * lvt_2_1_ * 0.5F);
    }

    public int getLightColor(float partialTick) {
        float lvt_2_1_ = ((float)this.age + partialTick) / (float)this.lifetime;
        lvt_2_1_ = MathHelper.clamp(lvt_2_1_, 0.0F, 1.0F);
        int lvt_3_1_ = super.getLightColor(partialTick);
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

        public Factory(IAnimatedSprite spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            ShardParticle lvt_15_1_ = new ShardParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            lvt_15_1_.pickSprite(this.spriteSet);
            return lvt_15_1_;
        }
    }
}
