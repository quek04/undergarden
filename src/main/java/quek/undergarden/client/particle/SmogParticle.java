package quek.undergarden.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

public class SmogParticle extends TextureSheetParticle {

    private SmogParticle(ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
        super(pLevel, pX, pY, pZ);
        this.scale(3.0F);
        this.setSize(0.25F, 0.25F);
        this.lifetime = this.random.nextInt(50) + 80;
        this.gravity = 3.0E-6F;
        this.xd = pXSpeed;
        this.yd = pYSpeed + (double)(this.random.nextFloat() / 500.0F);
        this.zd = pZSpeed;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ < this.lifetime && !(this.alpha <= 0.0F)) {
            this.xd += (this.random.nextFloat() / 5000.0F * (float)(this.random.nextBoolean() ? 1 : -1));
            this.zd += (this.random.nextFloat() / 5000.0F * (float)(this.random.nextBoolean() ? 1 : -1));
            this.yd -= this.gravity;
            this.move(this.xd, this.yd, this.zd);
            if (this.age >= this.lifetime - 60 && this.alpha > 0.01F) {
                this.alpha -= 0.015F;
            }

        } else {
            this.remove();
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {

        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            SmogParticle smogParticle = new SmogParticle(level, x, y, z, xSpeed, ySpeed, zSpeed);
            smogParticle.pickSprite(this.sprites);
            smogParticle.setColor(0.2F, 0.2F, 0.2F);
            smogParticle.setAlpha(0.9F);
            return smogParticle;
        }
    }
}
