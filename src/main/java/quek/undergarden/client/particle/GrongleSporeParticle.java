package quek.undergarden.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GrongleSporeParticle extends SpriteTexturedParticle {

    private GrongleSporeParticle(ClientWorld world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(world, x, y - 0.125D, z, motionX, motionY, motionZ);
        this.setSize(0.01F, 0.01F);
        this.quadSize *= this.random.nextFloat() * 0.6F + 0.6F;
        this.lifetime = (int)(16.0D / (Math.random() * 0.8D + 0.2D));
        this.hasPhysics = false;
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.lifetime-- <= 0) {
            this.remove();
        } else {
            this.move(this.xd, this.yd, this.zd);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            double d0 = (double)worldIn.random.nextFloat() * -1.9D * (double)worldIn.random.nextFloat() * 0.1D;
            GrongleSporeParticle spore = new GrongleSporeParticle(worldIn, x, y, z, 0.0D, d0, 0.0D);
            spore.pickSprite(this.spriteSet);
            return spore;
        }
    }
}
