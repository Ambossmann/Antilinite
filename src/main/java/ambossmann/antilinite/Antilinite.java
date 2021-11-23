package ambossmann.antilinite;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ambossmann.antilinite.setup.Registration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("antilinite")
public class Antilinite {
	// Directly reference a log4j logger.
	public static final Logger LOGGER = LogManager.getLogger();

	public static final String MOD_ID = "antilinite";
	
	public static final IEventBus MOD_EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();

	public Antilinite() {
		
		Registration.init();
		
		MOD_EVENT_BUS.addListener(this::setup);

		MinecraftForge.EVENT_BUS.register(this);
		
		MinecraftForge.EVENT_BUS.register(EventHandler.class);
	}

	private void setup(final FMLCommonSetupEvent event) {
	}
}
