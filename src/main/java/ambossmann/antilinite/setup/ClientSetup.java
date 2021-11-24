package ambossmann.antilinite.setup;

import ambossmann.antilinite.AttackParticle;
import ambossmann.antilinite.materials.MaterialParts;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(bus = Bus.MOD)
public class ClientSetup {

	private ClientSetup() {
	}

	@SuppressWarnings("resource")
	@SubscribeEvent
	public static void onParticleRegistry(ParticleFactoryRegisterEvent event) {
		Minecraft.getInstance().particleEngine.register(
				(SimpleParticleType) Registration.ANTILINITE.getPart(MaterialParts.ATTACK_PARTICLE).get(),
				AttackParticle.Provider::new);
		Minecraft.getInstance().particleEngine.register(
				(SimpleParticleType) Registration.ANTERIUM.getPart(MaterialParts.ATTACK_PARTICLE).get(),
				AttackParticle.Provider::new);
	}

}
