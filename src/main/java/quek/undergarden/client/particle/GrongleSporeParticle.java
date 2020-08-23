package quek.undergarden.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class GrongleSporeParticle extends SpriteTexturedParticle {

    private GrongleSporeParticle(ClientWorld world, double p_i232437_2_, double p_i232437_4_, double p_i232437_6_) {
        super(world, p_i232437_2_, p_i232437_4_ - 0.125D, p_i232437_6_);
        this.particleRed = 0.4F;
        this.particleGreen = 0.4F;
        this.particleBlue = 0.7F;
        this.setSize(0.01F, 0.01F);
        this.particleScale *= this.rand.nextFloat() * 0.6F + 0.2F;
        this.maxAge = (int)(16.0D / (Math.random() * 0.8D + 0.2D));
        this.canCollide = false;
    }

    private GrongleSporeParticle(ClientWorld world, double p_i232438_2_, double p_i232438_4_, double p_i232438_6_, double p_i232438_8_, double p_i232438_10_, double p_i232438_12_) {
        super(world, p_i232438_2_, p_i232438_4_ - 0.125D, p_i232438_6_, p_i232438_8_, p_i232438_10_, p_i232438_12_);
        this.setSize(0.01F, 0.01F);
        this.particleScale *= this.rand.nextFloat() * 0.6F + 0.6F;
        this.maxAge = (int)(16.0D / (Math.random() * 0.8D + 0.2D));
        this.canCollide = false;
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void tick() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if (this.maxAge-- <= 0) {
            this.setExpired();
        } else {
            this.move(this.motionX, this.motionY, this.motionZ);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite field_239201_a_;

        public Factory(IAnimatedSprite p_i232443_1_) {
            this.field_239201_a_ = p_i232443_1_;
        }

        public Particle makeParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            double d0 = (double)worldIn.rand.nextFloat() * -1.9D * (double)worldIn.rand.nextFloat() * 0.1D;
            GrongleSporeParticle spore = new GrongleSporeParticle(worldIn, x, y, z, 0.0D, d0, 0.0D);
            spore.selectSpriteRandomly(this.field_239201_a_);
            return spore;
        }
    }
}
