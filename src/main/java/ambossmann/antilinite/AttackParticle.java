package ambossmann.antilinite;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;

public class AttackParticle extends TextureSheetParticle {

	AttackParticle(ClientLevel level, double xo, double yo, double zo) {
		super(level, xo, yo, zo);
		this.speedUpWhenYMotionIsBlocked = true;
		this.friction = 0.86F;
		this.xd *= (double) 0.01F;
		this.yd *= (double) 0.01F;
		this.zd *= (double) 0.01F;
		this.yd += 0.1D;
		this.quadSize *= 1.5F;
		this.lifetime = 16;
		this.hasPhysics = false;
	}
	
	@Override
	public void tick() {
		this.alpha = 1.0F - ((float) this.age / (float) this.lifetime);
		super.tick();
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
	}

	@Override
	public float getQuadSize(float p_106860_) {
		return this.quadSize * Mth.clamp(((float) this.age + p_106860_) / (float) this.lifetime * 32.0F, 0.0F, 1.0F);
	}
	
	

	public static class Provider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet sprite;

		public Provider(SpriteSet p_106884_) {
			this.sprite = p_106884_;
		}

		public Particle createParticle(SimpleParticleType type, ClientLevel level, double xo,
				double yo, double zo, double p_106900_, double p_106901_, double p_106902_) {
			AttackParticle attackParticle = new AttackParticle(level, xo, yo, zo);
			attackParticle.pickSprite(this.sprite);
			return attackParticle;
		}
	}

}
