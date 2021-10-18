package ambossmann.antilinite.setup;

import ambossmann.antilinite.AttackParticle;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(bus=Bus.MOD)
public class ClientSetup {

	private ClientSetup() {
	}
	
	@SuppressWarnings("resource")
	@SubscribeEvent
	public static void onParticleRegistry(ParticleFactoryRegisterEvent event) {
		Minecraft.getInstance().particleEngine.register(Registration.ANTILINITE_ATTACK.get(),
		AttackParticle.Provider::new);
	}

}
