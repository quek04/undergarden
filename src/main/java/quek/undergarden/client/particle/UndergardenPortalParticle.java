package quek.undergarden.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class UndergardenPortalParticle extends PortalParticle {

    protected UndergardenPortalParticle(ClientLevel world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(world, x, y, z, motionX, motionY, motionZ);
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Factory(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Override
        public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            UndergardenPortalParticle portalParticle = new UndergardenPortalParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            portalParticle.pickSprite(this.spriteSet);
            portalParticle.setColor(0.0F, 0.25F, 0.05F);
            return portalParticle;
        }
    }
}